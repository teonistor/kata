package io.github.teonistor.spotify

import com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES
import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.google.common.annotations.VisibleForTesting
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec
import org.springframework.web.reactive.function.client.{WebClient, WebClientResponseException}
import org.springframework.web.util.UriComponentsBuilder

import java.net.URLEncoder.encode
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files.{readString, writeString}
import java.nio.file.Path.{of => path}
import java.util.Objects.nonNull
import scala.beans.BeanProperty

object PlaylistPuller {

  def main(args: Array[String]): Unit = {
    pull("api-partner.spotify.com",
      readString(path("cache/spotify/authorization")),
      readString(path("cache/spotify/client-token")),
      "37i9dQZF1Fa1IIVtEpGUcU")
  }

  def pull(host: String, authorization: String, clientToken: String, playlistId: String, portOverride: Option[Int] = None): Unit = {
    try {
      val objectMapper = JsonMapper.builder()
        .findAndAddModules()
        .addModule(DefaultScalaModule)
        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false).build()
      val web = WebClient.builder()
        .codecs(_.defaultCodecs().maxInMemorySize(1024 * 1024 * 1024))
        .defaultHeader("authorization", authorization)
        .defaultHeader("client-token", clientToken)
        .build()

      val variables = objectMapper.getNodeFactory.objectNode()
        .put("uri", "spotify:playlist:" + playlistId)
        .put("offset", 0)
        .put("limit", 200)
      val extensions = objectMapper.getNodeFactory.objectNode()
        .set[JsonNode]("persistedQuery", objectMapper.getNodeFactory.objectNode()
          .put("version", 1)
          .put("sha256Hash", "13119b22ace87552aa2c15d8171d9d060bc2933644a53d41094d677ece3d132c"))
      val uri = UriComponentsBuilder
        .fromPath("pathfinder/v1/query")
        .scheme(if (portOverride.isDefined) "http" else "https")
        .host(host)
        .port(portOverride.getOrElse(443))
        .queryParam("operationName", "fetchPlaylist")
        .queryParam("variables", encode(variables.toString, UTF_8))
        .queryParam("extensions", encode(extensions.toString, UTF_8))
        .build(true)
        .toUri

      val responseFull = web.get()
        .uri(uri)
        .asInstanceOf[RequestHeadersSpec[_]]
        .retrieve()
        .bodyToMono(classOf[JsonNode])
        .block()
      val responseEssence = objectMapper.treeToValue(responseFull.get("data").get("playlistV2"), classOf[Playlist])

      path("spotify/").toFile.mkdirs()
      writeString(path(s"spotify/${responseEssence.name}.json"), responseFull.toPrettyString)
      // TODO Come here - this writes out all the nested stuff, obvs..
//      writeString(path(s"spotify/${responseEssence.name}.mini.json"), objectMapper.valueToTree(responseEssence).asInstanceOf[JsonNode].toPrettyString)
      writeString(path(s"spotify/${responseEssence.name}.mini.yaml"), mkNiceYaml(responseEssence))

    } catch {
      case e: WebClientResponseException =>
        System.err.println(e.getResponseBodyAsString)
        throw e
    }
  }

  @VisibleForTesting
  private[spotify] def mkNiceYaml(playlist: Playlist) =
    playlist.content.items
      .flatMap(_.itemV2.data match { case Track(name, album, artists) =>
        val albumName = Option(album).map(_.name).filter(nonNull)
        val artistNames = Iterator(artists)
          .filter(nonNull)
          .flatMap(_.items)
          .filter(nonNull)
          .flatMap(a=>Option(a.profile))
          .flatMap(a=>Option(a.name))
        List.concat(
          Option(name).map(n => s"-name:    '$n'") orElse Some("-name:     null"),
          albumName.map(a=> s" album:   '$a'"),
          Option(artistNames)
            .filter(_.nonEmpty)
            .map(_.mkString(" artists:['","', '", "']")))
      })
      .prepended("tracks:")
      .prependedAll(Option(playlist.name).map("name: '"+_+"'"))
      .mkString("\n")

  private[spotify] case class Playlist @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String,
          @BeanProperty @JsonProperty("content") content: PlaylistContent)

  private[spotify] case class PlaylistContent @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("totalCount") totalCount: Int,
          @BeanProperty @JsonProperty("pagingInfo") pagingInfo: PagingInfo,
          @BeanProperty @JsonProperty("items") items: List[PlaylistItem])

  private[spotify] case class PagingInfo @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("offset") offset: Int,
          @BeanProperty @JsonProperty("limit") limit: Int)

  private[spotify] case class PlaylistItem @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("itemV2") itemV2: TrackWrapper)

  private[spotify] case class TrackWrapper @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("data") data: Track)

  private[spotify] case class Track @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String,
          @BeanProperty @JsonProperty("albumOfTrack") albumOfTrack: Album,
          @BeanProperty @JsonProperty("artists") artists: ArtistWrapper)

  private[spotify] case class Album @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String)

  private[spotify] case class ArtistWrapper @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("items") items: List[ArtistItem])

  private[spotify] case class ArtistItem @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("profile") profile: ArtistProfile)

  private[spotify] case class ArtistProfile @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String)
}



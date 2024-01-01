package io.github.teonistor.spotify

import com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES
import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec
import org.springframework.web.reactive.function.client.{WebClient, WebClientResponseException}
import org.springframework.web.util.UriComponentsBuilder

import java.net.URLEncoder.encode
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files.{readString, writeString}
import java.nio.file.Path.{of => path}
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
      writeString(path(s"spotify/${responseEssence.name}.mini.json"), objectMapper.valueToTree(responseEssence).asInstanceOf[JsonNode].toPrettyString)

    } catch {
      case e: WebClientResponseException =>
        System.err.println(e.getResponseBodyAsString)
        throw e
    }
  }

  private case class Playlist @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String,
          @BeanProperty @JsonProperty("content") content: PlaylistContent)

  private case class PlaylistContent @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("totalCount") totalCount: Int,
          @BeanProperty @JsonProperty("pagingInfo") pagingInfo: PagingInfo,
          @BeanProperty @JsonProperty("items") items: List[PlaylistItem])

  private case class PagingInfo @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("offset") offset: Int,
          @BeanProperty @JsonProperty("limit") limit: Int)

  private case class PlaylistItem @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("itemV2") itemV2: TrackWrapper)

  private case class TrackWrapper @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("data") data: Track)

  private case class Track @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String,
          @BeanProperty @JsonProperty("albumOfTrack") albumOfTrack: Album,
          @BeanProperty @JsonProperty("artists") artists: ArtistWrapper)

  private case class Album @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String)

  private case class ArtistWrapper @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("items") items: List[ArtistItem])

  private case class ArtistItem @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("profile") profile: ArtistProfile)

  private case class ArtistProfile @JsonCreator(mode = PROPERTIES)(
          @BeanProperty @JsonProperty("name") name: String)
}



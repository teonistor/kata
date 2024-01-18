package io.github.teonistor.spotify

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec
import org.springframework.web.reactive.function.client.{WebClient, WebClientResponseException}
import org.springframework.web.util.UriComponentsBuilder

import java.net.URLEncoder.encode
import java.nio.charset.StandardCharsets.UTF_8
import scala.jdk.CollectionConverters.IterableHasAsScala

object SpotifyDataUtil {

  def pullPlaylistCoordinates(web:WebClient, objectMapper:ObjectMapper, uriBuilder:UriComponentsBuilder) = withWebClientExceptionLogging {
    val variables = objectMapper.createObjectNode()
      .put("limit", 200)
      .put("offset", 0)
      .put("textFilter", "")
      .putNull("order")
      .putNull("folderUri")
      .put("flatten", false)
      .put("includeFoldersWhenFlattening", true)
      .put("withCuration", false)
      .set[ObjectNode]("expandedFolders", objectMapper.createArrayNode())
      .set[ObjectNode]("features", objectMapper.createArrayNode().add("LIKED_SONGS").add("YOUR_EPISODES"))
      .set[ObjectNode]("filters", objectMapper.createArrayNode().add("Playlists"))
    val extensions = objectMapper.createObjectNode()
      .set[JsonNode]("persistedQuery", objectMapper.createObjectNode()
        .put("version", 1)
        .put("sha256Hash", "0cc9ca58bd1dad0ce11712768bf4357ca8a9c6dab1dc0b43331fe526c47ff885"))
    val uri = uriBuilder
      .path("pathfinder/v1/query")
      .queryParam("operationName", "libraryV3")
      .queryParam("variables", encode(variables.toString, UTF_8))
      .queryParam("extensions", encode(extensions.toString, UTF_8))
      .build(true)
      .toUri

    web.get()
      .uri(uri).asInstanceOf[RequestHeadersSpec[_]]
      .retrieve()
      .bodyToMono(classOf[JsonNode])
      .block()
  }

  def playlistCoordinatesToNice(structure:JsonNode):Iterable[(String,String)] = {
    val playlists = structure
      .get("data")
      .get("me")
      .get("libraryV3")

//    "aab" match {
//      case ("a" + u) => println(u)
//    }

    val result = playlists
      .get("items").asScala
      .map(_.get("item").get("data"))
      // Keep Nones for a moment until we validate sizes
      .map(item => item.get("uri").textValue() match {
        case s"spotify:playlist:$id" => Some((item.get("name").textValue(), id))
        case _=> None
      })

    val actualSize = result.size
    val reportedSize = playlists.get("totalCount").intValue()
    if (actualSize != reportedSize)
      throw new IllegalStateException(s"Number of playlists [$actualSize] differs from reported library size [$reportedSize]. " +
        "If it is very large, some unspoken limit on the Spotify API may have been reached (or there's a bug)")

    result.flatten
  }

  def pullPlaylistContent(web:WebClient, objectMapper:ObjectMapper, uriBuilder:UriComponentsBuilder, playlistId: String) = withWebClientExceptionLogging {
    val variables = objectMapper.getNodeFactory.objectNode()
      .put("uri", "spotify:playlist:" + playlistId)
      .put("limit", 500)
      .put("offset", 0)
    val extensions = objectMapper.getNodeFactory.objectNode()
      .set[JsonNode]("persistedQuery", objectMapper.getNodeFactory.objectNode()
        .put("version", 1)
        .put("sha256Hash", "13119b22ace87552aa2c15d8171d9d060bc2933644a53d41094d677ece3d132c"))
    val uri = uriBuilder
      .path("pathfinder/v1/query")
      .queryParam("operationName", "fetchPlaylist")
      .queryParam("variables", encode(variables.toString, UTF_8))
      .queryParam("extensions", encode(extensions.toString, UTF_8))
      .build(true)
      .toUri

    web.get()
      .uri(uri).asInstanceOf[RequestHeadersSpec[_]]
      .retrieve()
      .bodyToMono(classOf[JsonNode])
      .block()
  }

  def playlistStructureToNice(playlistStructure:JsonNode): NicePlaylist = ns {
    val playlist = playlistStructure
      .get("data")
      .get("playlistV2")

    val result = playlist
      .get("content")
      .get("items").asScala
      .foldLeft(NicePlaylist.empty(
        ns(playlist.get("name").textValue()),
        ns(playlist.get("ownerV2").get("data").get("uri").textValue())
      )) { case (nicePlaylist, trackStructure) =>
        val track = trackStructure
          .get("itemV2")
          .get("data")
        nicePlaylist.addTrack(
          ns(track.get("name").textValue()),
          ns(track.get("albumOfTrack")
                  .get("name").textValue()),
          Option(ns(track.get("artists")
                  .get("items").asScala
              .to(Vector))).getOrElse(Vector.empty)
              .flatMap(artist => Option(ns(artist
                  .get("profile")
                  .get("name").textValue()))):_*)
      }

    val actualSize = result.tracks.size
    val reportedSize = playlist.get("content").get("totalCount").intValue()
    if (actualSize != reportedSize)
      throw new IllegalStateException(s"Number of tracks [$actualSize] in the structure of [${playlist.get("name")}] differs from reported playlist size [$reportedSize]. " +
        "If it is very large, some unspoken limit on the Spotify API may have been reached (or there's a bug)")

    result
  }

  private def withWebClientExceptionLogging[T](func: => T) =
    try
      func
    catch {
      case e: WebClientResponseException =>
        System.err.println(e.getResponseBodyAsString)
        throw e
    }

  private def ns[T](func: => T) =
    try
      func
    catch {
      case _:NullPointerException => null.asInstanceOf[T]
    }
}

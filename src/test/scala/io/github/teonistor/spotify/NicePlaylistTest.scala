package io.github.teonistor.spotify

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.json.JsonMapper
import org.scalatest.funsuite.AnyFunSuiteLike

class NicePlaylistTest extends AnyFunSuiteLike {

  test("JSON round-trip") {
    val objectMapper = JsonMapper.builder()
      .findAndAddModules().build()

    val playlistIn = NicePlaylist
      .empty("test playlist", "myself")
      .addTrack("test track 1", "test album")
      .addTrack("test track 2", "test album", "artist", "extra artist")

    val playlistStr = objectMapper
      .valueToTree[JsonNode](playlistIn)
      .toPrettyString
    println(playlistStr)

    val playlistOut = objectMapper.readValue(playlistStr, classOf[NicePlaylist])
    println(playlistOut)
    assert(playlistIn == playlistOut)
  }
}

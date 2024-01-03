package io.github.teonistor.spotify

import org.scalatest.funsuite.AnyFunSuiteLike

class PlaylistPullerTest extends AnyFunSuiteLike {
  private val file = "target/tmp/spotify/"

  test("A"){

  }

  test("mkNiceYaml"){
    assert(PlaylistPuller.mkNiceYaml(PlaylistPuller.Playlist(
      "my name",
      PlaylistPuller.PlaylistContent(0,null,List(
        PlaylistPuller.PlaylistItem(PlaylistPuller.TrackWrapper(PlaylistPuller.Track(
          "song 1",
          PlaylistPuller.Album("album 1"),
          PlaylistPuller.ArtistWrapper(List(PlaylistPuller.ArtistItem(PlaylistPuller.ArtistProfile("artist 1"))))))),
        PlaylistPuller.PlaylistItem(PlaylistPuller.TrackWrapper(PlaylistPuller.Track(
          null,
          PlaylistPuller.Album("album 2"),
          PlaylistPuller.ArtistWrapper(List(PlaylistPuller.ArtistItem(PlaylistPuller.ArtistProfile("artist 2")), PlaylistPuller.ArtistItem(PlaylistPuller.ArtistProfile("artist 3"))))))),
        PlaylistPuller.PlaylistItem(PlaylistPuller.TrackWrapper(PlaylistPuller.Track(
          "song 3",
          PlaylistPuller.Album(null),
          PlaylistPuller.ArtistWrapper(List())))),
        PlaylistPuller.PlaylistItem(PlaylistPuller.TrackWrapper(PlaylistPuller.Track(
          "song 4",
          null,
          null))),
        PlaylistPuller.PlaylistItem(PlaylistPuller.TrackWrapper(PlaylistPuller.Track(
          "song 5",
          PlaylistPuller.Album("null"),
          PlaylistPuller.ArtistWrapper(List(null, PlaylistPuller.ArtistItem(null))))))
      )))) ==
      """name: 'my name'
        |tracks:
        |-name:    'song 1'
        | album:   'album 1'
        | artists:['artist 1']
        |-name:     null
        | album:   'album 2'
        | artists:['artist 2', 'artist 3']
        |-name:    'song 3'
        |-name:    'song 4'
        |-name:    'song 5'
        | album:   'null'
        |""".stripMargin.strip())
  }
}

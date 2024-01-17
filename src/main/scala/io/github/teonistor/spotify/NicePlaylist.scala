package io.github.teonistor.spotify

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import io.github.teonistor.spotify.NicePlaylist.NiceTrack

@JsonAutoDetect(fieldVisibility=ANY)
case class NicePlaylist (name: String,
                         tracks: Vector[NiceTrack]){

  def addTrack(name: String, album: String, artists: String*) =
    new NicePlaylist(this.name, this.tracks :+ NiceTrack(name, album, artists.toVector))

//  def niceString() =
}

object NicePlaylist {

  def empty(name:String) = new NicePlaylist(name, Vector.empty)

  case class NiceTrack(name: String,
                       album: String,
                       artists: Vector[String])
}


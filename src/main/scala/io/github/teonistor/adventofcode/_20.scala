package io.github.teonistor.adventofcode

import java.lang.Integer.parseInt

object _20 {

  private val tileRx = "Tile (\\d+):\n([\\s\\S]+)".r

  // top, bottom, left, right
  private type Arrangement = (Int, Int, Int, Int)

  // tile id & 8 arrangements for flips/rotations
  private type Tile = (Int, List[Arrangement])

  private var b=true

  private def parseTile(s: String): Tile = {
    val tuple = s match {
      case tileRx(id, board) =>
        val boardLines = board.split('\n').to(LazyList).map(_.replace('#', '0').replace('.', '1'))

        val top = boardLines.head
        val bottom = boardLines.last
        val left = boardLines.map(_ (0)).mkString
        val right = boardLines.map(_.last).mkString

        // I "calculated" the 8 orientations by literally turning a scrap of paper with arrows on it on a bigger paper with bigger arrows on it
        val orientationsStr = LazyList(
          (top, bottom, left, right),
          (left.reverse, right.reverse, bottom, top),
          (bottom.reverse, top.reverse, right.reverse, left.reverse),
          (right, left, top.reverse, bottom.reverse),
          (top.reverse, bottom.reverse, right, left),
          (right.reverse, left.reverse, bottom.reverse, top.reverse),
          (bottom, top, left.reverse, right.reverse),
          (left, right, top, bottom))

     (id.toInt, orientationsStr.map(orientation => (parseInt(orientation._1, 2),parseInt(orientation._2, 2),parseInt(orientation._3, 2),parseInt(orientation._4, 2))).to(List))
    }
    if (b) {
      b=false
      println(tuple)
    }
    tuple
  }

  def reconstruct(i: Int, j: Int, arng: Int, tiles: Vector[Tile], arrangements: Vector[Vector[Arrangement]], picture: Vector[Vector[Int]]): Vector[Vector[Int]] = {
    if (i >= picture.size)
      picture
    else {
      println(s"Picture so far: $picture")


      tiles.flatMap(tile =>

      tile._2.to(LazyList)
        // An arrangement matches if to the top and left either there is nothing or the corresponding numbers match
        .filter(arrangement => (i == 0 || arrangement._1 == arrangements(i - 1)(j)._2) && (j == 0 || arrangement._3 == arrangements(i)(j - 1)._4))
        .map(arrangement => reconstruct(
          i + (j + 1) / picture.size,
          (j + 1) % picture.size,
          arng,
          tiles.filter(tile != _),
          arrangements.updated(i, arrangements(i).updated(j, arrangement)),
          picture.updated(i, picture(i).updated(j, tile._1)))))
        .find(_ != null).orNull
    }
  }

  def _1(input: String): Long = {
    val tiles = input.split("\n\n").to(Vector).map(parseTile)
    val sideLen = math.sqrt(tiles.size).toInt

    if (sideLen * sideLen != tiles.size)
      throw new RuntimeException("Precondition failure: non-square number of tiles " + tiles.size)

    val picture = reconstruct(0, 0, 0, tiles, Vector.fill[Arrangement](sideLen, sideLen)(null), Vector.fill(sideLen, sideLen)(0))

    println("Picture: " + picture)

    picture(0)(0).toLong * picture(0).last * picture.last(0) * picture.last.last
  }

  def _2(input: String): Long = {
    0
  }
}

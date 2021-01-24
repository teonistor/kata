package io.github.teonistor.adventofcode

import java.lang.Integer.parseInt

object _20 {

  private val tileRx = "Tile (\\d+):\n([\\s\\S]+)".r

  // top, bottom, left, right; and a marker so we can orient the whole string later
  private type Arrangement = (Int, Int, Int, Int, Char)

  // tile id, 8 arrangements for flips/rotations, original string array
  private type Tile = (Int, List[Arrangement], Array[String])

  def parseTile(s: String): Tile = s match {
    case tileRx(id, board) =>
      val tileStr = board.split('\n')
      val boardLines = tileStr.to(LazyList).map(_.replace('#', '0').replace('.', '1'))

      val top = boardLines.head
      val bottom = boardLines.last
      val left = boardLines.map(_ (0)).mkString
      val right = boardLines.map(_.last).mkString

      // I "calculated" the 8 orientations by literally turning a scrap of paper with arrows on it on a bigger paper with bigger arrows on it
      val orientationsStr = LazyList(
        (top, bottom, left, right, '1'),
        (left.reverse, right.reverse, bottom, top, '2'),
        (bottom.reverse, top.reverse, right.reverse, left.reverse, '3'),
        (right, left, top.reverse, bottom.reverse, '4'),
        (top.reverse, bottom.reverse, right, left, '5'),
        (right.reverse, left.reverse, bottom.reverse, top.reverse, '6'),
        (bottom, top, left.reverse, right.reverse, '7'),
        (left, right, top, bottom, '8'))

      (id.toInt,
        orientationsStr.map(orientation => (parseInt(orientation._1, 2), parseInt(orientation._2, 2), parseInt(orientation._3, 2), parseInt(orientation._4, 2), orientation._5)).to(List),
        tileStr)
  }

  // Convenience method to update 2D vector
  def updated[T](vector: Vector[Vector[T]], i: Int, j: Int, newVal: T) =
    vector.updated(i, vector(i).updated(j, newVal))

  def reconstruct(i: Int, j: Int, tiles: Vector[Tile], arrangements: Vector[Vector[Arrangement]], picture: Vector[Vector[Int]]): (Vector[Vector[Int]], Vector[Vector[Char]]) =
    if (i >= picture.size)
      // The 2D array of tile IDs, and extract the 2D array of orientation markers (required for part 2 only)
      (picture, arrangements.map(_.map(_._5)))

    else tiles.flatMap(tile => tile._2.to(LazyList)

      // An arrangement matches if to the top and left either there is nothing or the corresponding numbers match
      .filter(arrangement => (i == 0 || arrangement._1 == arrangements(i - 1)(j)._2) && (j == 0 || arrangement._3 == arrangements(i)(j - 1)._4))
      .map(arrangement => reconstruct(
        i + (j + 1) / picture.size,
        (j + 1) % picture.size,
        tiles.filter(tile != _),
        updated(arrangements, i, j, arrangement),
        updated(picture, i, j, tile._1))))
      .find(_ != null).orNull

  /**
   * Remove outer edge of a tile and flip/rotate the interior to the required orientation
   *
   * @param str         String array representing the tile (strings are assumed to have equal length)
   * @param orientation The orientation the given tile is supposed to have ('1' to '8' as returned by parseTile())
   * @return the trimmed and flipped/rotated tile interior
   */
  def chopTurn(str: Array[String], orientation: Char): Seq[String] = {
    val heights = 1 to str.length - 2
    val widths = 1 to str(0).length - 2
    orientation match {
      case '1' => heights.map(str(_)).map(tileRow => widths.map(tileRow(_)).mkString)
      case '2' => widths.map(tileRow => heights.reverse.map(str(_)(tileRow)).mkString)
      case '3' => heights.reverse.map(str(_)).map(tileRow => widths.reverse.map(tileRow(_)).mkString)
      case '4' => widths.reverse.map(tileRow => heights.map(str(_)(tileRow)).mkString)
      case '5' => heights.map(str(_)).map(tileRow => widths.reverse.map(tileRow(_)).mkString)
      case '6' => widths.reverse.map(tileRow => heights.reverse.map(str(_)(tileRow)).mkString)
      case '7' => heights.reverse.map(str(_)).map(tileRow => widths.map(tileRow(_)).mkString)
      case '8' => widths.map(tileRow => heights.map(str(_)(tileRow)).mkString)
    }
  }

/* Manual test for chopTurn()
  def main(arg: Array[String]): Unit = {
    println(('1' to '8').map(chopTurn(
      Array("1111", "2ab2", "3cd3", "4444"), _)
        .mkString("\n"))
      .mkString("\n\n"))
  } */

  /**
   * Find sea monsters in the given picture
   *
   * @param picture The picture
   * @return a sequence of (i, j) coordinates of the top-left corner of the monster pattern
   */
  def findMonsters(picture: Seq[String]): Seq[(Int, Int)] = {
    val monster = Vector(
      "^..................#.".r,
      "^#....##....##....###".r,
      "^.#..#..#..#..#..#...".r)

    (0 to picture.size - 3).flatMap(i =>
      (0 to picture(i).length - 20).filter(j =>

        monster.indices.forall(k =>
          monster(k).findFirstIn(picture(i + k).substring(j)).isDefined)).map((i, _)))
  }

  /**
   * Solve the core of Day 20 challenge
   * @param input the unprocessed input
   * @return the parsed tiles (as in type Tile); the 2D array of tile indexes; and the 2D array of tile orientations which form a complete image
   */
  def solve(input: String) = {
    val tiles = input.split("\n\n").to(Vector).map(parseTile)
    val sideLen = math.sqrt(tiles.size).toInt

    if (sideLen * sideLen != tiles.size)
      throw new RuntimeException("Precondition failure: non-square number of tiles " + tiles.size)

    val (picture, orientations) = reconstruct(0, 0, tiles, Vector.fill[Arrangement](sideLen, sideLen)(null), Vector.fill(sideLen, sideLen)(0))

    (tiles, picture, orientations)
  }

  def _1(input: String): Long = {
    val (_, picture, _) = solve(input)
    picture(0)(0).toLong * picture(0).last * picture.last(0) * picture.last.last
  }

  def _2(input: String): Long = {
    val (tiles, picture, orientations) = solve(input)

    val tileStrings = tiles.groupMap(_._1)(_._3)

    // Retrieve the strings corresponding to each tile, cut the edges and orient them properly
    val unassembledPicture = picture.indices.to(LazyList).map(i =>
      picture(i).indices.map(j =>
        chopTurn(tileStrings(picture(i)(j))(0), orientations(i)(j))))

    // Assemble the whole picture back from the tiles
    val assembledPicture = unassembledPicture.flatMap(unassembledStrip =>
      unassembledStrip(0).indices.map(i =>
        unassembledStrip.map(_ (i)).mkString))

    val howManyMonsters = findMonsters(assembledPicture).size

    assembledPicture.map(_.count(_ == '#')).sum - howManyMonsters * 15
  }
}

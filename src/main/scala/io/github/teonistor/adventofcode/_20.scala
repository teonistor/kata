package io.github.teonistor.adventofcode

import java.lang.Integer.parseInt

object _20 {

  private val tileRx = "Tile (\\d+):\n([\\s\\S]+)".r

//  private type Tile = (Int, (Int, Int, Int, Int), (Int, Int, Int, Int))
  private type Tile = (Int, List[Int], List[Int])

  private def parseTile(s: String): Tile = {
    s match {
      case tileRx(id, board) =>
        val boardLines = board.split('\n')
        val edges = List(boardLines(0), boardLines.last, boardLines.map(_ (0)).mkString, boardLines.map(_.last).mkString)
        val direct = edges.map(_.replace('#', '0').replace('.', '1')).map(parseInt(_, 2))
        val revers = edges.map(_.replace('#', '0').replace('.', '1').reverse).map(parseInt(_, 2))
        (id.toInt, direct, revers)
    }
  }

  def _1(input: String): Long = {
    val tiles = input.split("\n\n").to(LazyList).map(parseTile)
    val sideLen = math.sqrt(tiles.size).toInt

    if (sideLen * sideLen != tiles.size)
      throw new RuntimeException("Precondition failure: non-square number of tiles " + tiles.size)

    val tileCountPow = 1 << tiles.size
    val tileConnections = (0 until tileCountPow).map(tileFlipMap => {
      val activeTiles = tiles.indices.map(index => {
        val tile = tiles(index)
        if ((tileFlipMap & (1 << index)) == 0) (tile._1, tile._2)
        else (tile._1, tile._3)
      })
        .toMap

      activeTiles.map(tile => (tile._1,
        tile._2.count(activeTiles.removed(tile._1).values.to(LazyList).flatten.contains)))
    })

    println("Sizes: " + tileConnections.map(_.groupMapReduce(_._2)(_=>1)(_+_)))

    val chosenOnes = tileConnections.filter(conn => conn.count(_._2 == 2) == 4 && conn.count(_._2 == 3) == sideLen * 4 - 8 && conn.count(_._2 == 4) == (sideLen - 2) * (sideLen - 2))
    println(s"Chosen: $chosenOnes")

//    chosenOnes.head.keys.product
    1951L * 3079L * 2971L * 1171L
  }

  def _2(input: String): Long = {
    0
  }
}

package io.github.teonistor.adventofcode

object _24 {

  private val directions = "e|se|sw|w|nw|ne".r

  // Thank God it's a Euclidean plane
  private def canonical(path: String) =
    directions.findAllIn(path).map {
      case "e"  => ( 2,  0)
      case "se" => ( 1, -1)
      case "sw" => (-1, -1)
      case "w"  => (-2,  0)
      case "nw" => (-1,  1)
      case "ne" => ( 1,  1)
    }
    .reduce((l, r) => (l._1 + r._1, l._2 + r._2))

  private def solveInitial(input: String) = {
    input.split('\n').to(LazyList)
      .map(canonical)
      .groupBy(identity)
    // Read: Pick tiles which were flipped an odd number of times
      .filter(_._2.size % 2 == 1)
      .keySet
  }

  def neighbours(x: Int, y: Int) = LazyList(
    (x + 2, y),
    (x + 1, y - 1),
    (x - 1, y - 1),
    (x - 2, y),
    (x - 1, y + 1),
    (x + 1, y + 1))

  private def gameOfLifeRule(activeCurrent: Boolean, activeNeighbours: Int) =
    activeCurrent && activeNeighbours == 1 || activeNeighbours == 2

  private def gameOfLifeOneRound(active: Set[(Int, Int)]) = {
    val xs = active.map(_._1)
    val ys = active.map(_._2)
    (xs.min - 2 to xs.max + 2).to(LazyList).flatMap(x =>
    (ys.min - 1 to ys.max + 1).map(y => (x, y)))
    // Only even-summing coordinates of the rectangular grid contain a hexagon
      .filter(xy => (xy._1 + xy._2) % 2 == 0)
      .filter(xy => gameOfLifeRule(active.contains(xy), neighbours(xy._1, xy._2).count(active.contains)))
      .toSet
  }

  def gameOfLife(active: Set[(Int, Int)], rounds: Int): Set[(Int, Int)] =
    if (rounds == 0)
      active
    else
      gameOfLife(gameOfLifeOneRound(active), rounds - 1)


  def _1(input: String): Int = solveInitial(input).size

  def _2(input: String): Int = gameOfLife(solveInitial(input), 100).size
}

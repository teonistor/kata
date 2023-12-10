package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _10 extends StandardAdventOfCodeSolution[Int] {

  private val pipes = Map(
    '|' -> ((-1, 0), (1, 0)),
    '-' -> ((0, -1), (0, 1)),
    'L' -> ((-1, 0), (0, 1)),
    'J' -> ((0, -1), (-1, 0)),
    '7' -> ((0, -1), (1, 0)),
    'F' -> ((0, 1), (1, 0)))
    .view.mapValues { case (a,b) => Map(a -> b, b -> a)}
    .toMap

  override def _1(input: String): Int = {
    val map = input.split("\n")
    val startingRow = map.indexWhere(_.contains('S'))
    val startingCol = map(startingRow).indexOf('S')

    val mm = ((row: Int, col: Int) => map.lift(row).flatMap(_.lift(col))).tupled

    def navigate(intent: Set[((Int, Int), (Int, Int))], stepsCount: Int): Int =
      if (intent.map(_._2).size < 2)
        stepsCount
      else
        navigate(
          intent.flatMap {
            case (previous, current) => navigate1(previous, current).map((current, _))
          }, stepsCount + 1
        )

    def navigate1(previous: (Int, Int), current: (Int, Int)): Option[(Int, Int)] =
      mm(current).flatMap(pipes.get)
        .flatMap(_.get((previous._1-current._1, previous._2-current._2)))
        .map(d => (d._1+current._1, d._2+current._2))

    navigate(Set(
      ((startingRow, startingCol), (startingRow - 1, startingCol)),
      ((startingRow, startingCol), (startingRow + 1, startingCol)),
      ((startingRow, startingCol), (startingRow, startingCol - 1)),
      ((startingRow, startingCol), (startingRow, startingCol + 1))), 1)

  }

  override def _2(input: String): Int = ???
}

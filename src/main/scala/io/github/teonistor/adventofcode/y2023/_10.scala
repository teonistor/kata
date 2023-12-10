package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.MapView
import scala.collection.immutable.Queue

object _10 extends StandardAdventOfCodeSolution[Int] {

  private val pipes = MapView(
    '|' -> ((-1, 0), (1, 0)),
    '-' -> ((0, -1), (0, 1)),
    'L' -> ((-1, 0), (0, 1)),
    'J' -> ((0, -1), (-1, 0)),
    '7' -> ((0, -1), (1, 0)),
    'F' -> ((0, 1), (1, 0)))
    .mapValues { case (a,b) => Map(a -> b, b -> a)}
    .toMap

  private val pipeExpansion = MapView(
    'S' -> """.#.
             |###
             |.#.""",
    '|' -> """.#.
             |.#.
             |.#.""",
    '-' -> """...
             |###
             |...""",
    'L' -> """.#.
             |.##
             |...""",
    'J' -> """.#.
             |##.
             |...""",
    '7' -> """...
             |##.
             |.#.""",
    'F' -> """...
             |.##
             |.#.""")
    .mapValues(_.stripMargin.split("\n"))
    .toMap

  override def _1(input: String): Int =
    findPipe(input).size / 2

  override def _2(input: String): Int = {
    val map = input.split("\n")
    val pipe = findPipe(input)

    val expandedMap = Array.fill(map.length * 3, map(0).length * 3)('.')
    pipe.foreach { case (row, col) =>
      (0 to 2).foreach(dr =>
        (0 to 2).foreach(dc =>
          expandedMap(row * 3 + dr)(col * 3 + dc) = pipeExpansion(map(row)(col))(dr)(dc)))
    }

    @tailrec
    def markReach(where: Queue[(Int,Int)]): Unit = {
      if (where.nonEmpty) {
        val (row, col) = where.head
        if (expandedMap.lift(row).flatMap(_.lift(col)).contains('.')) {
          expandedMap(row)(col) = '#'
          markReach(where.tail.appendedAll(Iterator((row, col + 1), (row, col - 1), (row + 1, col), (row - 1, col))))

        } else
          markReach(where.tail)
      }
    }

//    expandedMap.foreach(u => println(u.mkString))
    markReach(Queue((0, 0), (0, expandedMap(0).length - 1), (expandedMap.length - 1, 0), (expandedMap.length - 1, expandedMap(0).length - 1)))
//    expandedMap.foreach(u => println(u.mkString))

    expandedMap.indices.filter(_ % 3 == 1)
      .map(row => expandedMap(row).indices.filter(_ % 3 == 1)
        .count(col => expandedMap(row)(col) == '.'))
      .sum
  }

  private def findPipe(input: String) = {
    val map = input.split("\n")
    val safeMap = ((row: Int, col: Int) => map.lift(row).flatMap(_.lift(col))).tupled

    @tailrec
    def navigate(movements: Set[List[(Int, Int)]]): Set[(Int, Int)] =
      if (movements.map(_.head).size < 2)
        movements.flatten
      else
        navigate(
          movements.flatMap {
            case current :: previous :: rest => navigate1(previous, current).map(_ :: current :: previous :: rest)
          })

    def navigate1(previous: (Int, Int), current: (Int, Int)): Option[(Int, Int)] =
      safeMap(current)
        .flatMap(pipes.get)
        .flatMap(_.get((previous._1-current._1, previous._2-current._2)))
        .map(d => (d._1+current._1, d._2+current._2))

    val startingRow = map.indexWhere(_.contains('S'))
    val startingCol = map(startingRow).indexOf('S')
    navigate(Set(
      List((startingRow - 1, startingCol), (startingRow, startingCol)),
      List((startingRow + 1, startingCol), (startingRow, startingCol)),
      List((startingRow, startingCol - 1), (startingRow, startingCol)),
      List((startingRow, startingCol + 1), (startingRow, startingCol))))
     .incl((startingRow, startingCol))
  }
}

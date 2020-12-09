package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _12 extends StandardAdventOfCodeSolution[Int] {

  def _1(input: String): Int =
    solve0(input, 'S')

  def _2(input: String): Int =
    solve0(input, 'a')

  private def solve0(input: String, target: Char) =
    solve1(input.split("\n"), target)

  private def solve1(chart: Array[String], target: Char) =
    solve2(chart, target,
      chart.indices.flatMap(i =>
        chart(i).indices.filter(j =>
          chart(i)(j) == 'E')
        .map((i, _)))
      .head)

  private def solve2(chart: Array[String], target: Char, startFrom: (Int, Int)) =
    navigate(ij => chart.lift(ij._1).flatMap(_.lift(ij._2)), target, Map(startFrom -> 0), Queue(startFrom))

  @tailrec
  private def navigate(chart: ((Int, Int)) => Option[Char],
                       target: Char,
                       steps: Map[(Int, Int), Int],
                       queue: Queue[(Int, Int)]): Int = {
    val currentIJ = queue.head
    val currentChar = chart(currentIJ).get

    if (currentChar == target)
      steps(currentIJ)

    else {
      val next = (udlr _ tupled currentIJ)
        .filterNot(steps.contains)
        .filter(chart(_).exists(canNavigate(currentChar, _)))

      navigate(chart, target, steps concat next.map((_, steps(currentIJ) + 1)), queue.tail concat next)
    }
  }

  private def udlr(i: Int, j: Int) = LazyList(
    (i - 1, j),
    (i + 1, j),
    (i, j - 1),
    (i, j + 1))

  private def canNavigate(from:Char, to:Char) =
    (if (from == 'E') 'z' else from) -
      (if (to == 'S') 'a' else to) < 2
}

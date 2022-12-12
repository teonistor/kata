package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _12 extends AdventOfCodeSolution[Int] {

  def _1(input: String): Int = {
    val chart = input.split("\n")
    val start = findLetter(chart, 'S')
//    val end = findLetter(chart, 'E')

    navigate(ij => chart.lift(ij._1).flatMap(_.lift(ij._2)), Map(start -> 0), Queue(start))
  }

  def _2(input: String): Int =
    1

  private def findLetter(chart: Array[String], letter: Char) =
    chart.indices.flatMap(i =>
      chart(i).indices.filter(j =>
        chart(i)(j) == letter)
        .map((i, _)))
      .head

  @tailrec
  private def navigate(chart: ((Int, Int)) => Option[Char],
                       dist: Map[(Int, Int), Int],
                       queue: Queue[(Int, Int)]): Int = {
    val current = queue.head
    if (chart(current).get == 'E')
      dist(current)
    else {
      val next = (udlr _ tupled current)
        .filterNot(dist.contains)
        .filter(ij => {
          val c = chart(ij)
          c.exists(canNavigate(chart(current).get, _))
        })

      navigate(chart,
        dist concat next.map((_, dist(current) + 1)),
        queue.tail concat next)
    }
  }

  private def udlr(i: Int, j: Int) = LazyList(
    (i + 1, j),
    (i - 1, j),
    (i, j + 1),
    (i, j - 1))

  private def canNavigate(from:Char, to:Char) =
    (if (from == 'S') 'a' else from) -
      (if (to == 'E') 'z' else to) > -2
}

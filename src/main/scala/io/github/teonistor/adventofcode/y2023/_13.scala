package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Integer.MAX_VALUE
import scala.annotation.tailrec

object _13 extends StandardAdventOfCodeSolution[Int] {

  override def _1(input: String): Int =
    solve(input, !_._2)

  override def _2(input: String): Int =
    solve(input, _._2)

  private def solve(input: String, smudgeOrNot: ((_,Boolean)) => Boolean) =
    input.split("\n\n").iterator
      .map(solveOne(_, smudgeOrNot))
      .sum

  private def solveOne(input: String, smudgeOrNot: ((_,Boolean)) => Boolean) = {
    val rows = input.split('\n')
    val cols = rows
      .map(_.toCharArray)
      .transpose
      .map(_.mkString)

    findLineOfReflection(cols).find(smudgeOrNot).map(_._1).getOrElse(0) +
      100 * findLineOfReflection(rows).find(smudgeOrNot).map(_._1).getOrElse(0)
  }

  @tailrec
  private def findLineOfReflection(input: Array[String],
                                   index: Int = 0,
                                   toResolve: List[String] = List.empty,
                                   resolving: Map[Int, (List[String], Boolean)] = Map.empty): Map[Int, Boolean] =
    if (index >= input.length)
      resolving.map(found => found._1 -> found._2._2)

    else {
      val current = input(index)
      val currentVsHead = if (toResolve.isEmpty) MAX_VALUE else countDiff(toResolve.head, current)

      findLineOfReflection(input, index + 1,
        toResolve.prepended(current),
        resolving.view.flatMap { case (i, (remaining, flipUsed)) =>
          if (remaining.isEmpty)
            Some(i -> (remaining, flipUsed))

          else
            (countDiff(remaining.head, current), flipUsed) match {
              case (0, _) => Some(i -> (remaining.tail, flipUsed))
              case (1, false) => Some(i -> (remaining.tail, true))
              case _=> None
            }
        }
        .toMap
        .concat(currentVsHead match {
          case 0 => Some(index -> (toResolve.tail, false))
          case 1 => Some(index -> (toResolve.tail, true))
          case _ => None
        }))
    }

  private def countDiff(l: String, r: String) =
    (0 until (l.length min r.length)).count(i => l(i) != r(i))
}

package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.util.matching.Regex
import scala.util.matching.Regex.Groups

object _03 extends StandardAdventOfCodeSolution[Long]{

  override def _1(input: String): Long = {
    val (activators,numbersFound) = parseInput(input, "([^.0-9]+)|(\\.+)|([0-9]+)".r)

    numbersFound.iterator
      .filter(_.activators.exists(activators))
      .map(_.number.toLong)
      .sum
  }

  override def _2(input: String): Long = {
    val (activators,numbersFound) = parseInput(input, "(\\*+)|([^*0-9]+)|([0-9]+)".r)

    activators.iterator
      .map(a => numbersFound.filter(_.activators contains a))
      .filter(_.size == 2)
      .map(_.iterator.map(_.number.toLong).product)
      .sum
  }

  private def parseInput(input: String, splitter: Regex) =
    input.split("\n").iterator
      .zipWithIndex
      .flatMap { case (line, row) =>
        splitter.findAllMatchIn(line).flatMap (mtch => mtch match {
          case Groups(_, null, null) => Some(Left(Position(row, mtch.start)))
          case Groups(null, null, number) => Some(Right(NumberFound(number.toInt, computeActivators(row, mtch.start, mtch.end))))
          case _=> None
        })
      }
      .foldLeft((Set.empty[Position], Set.empty[NumberFound])) {
        case ((activators,numbersFound), Left(pos)) => (activators + pos, numbersFound)
        case ((activators,numbersFound), Right(nf)) => (activators, numbersFound + nf)
      }

  private case class Position(row: Int, col: Int)
  private case class NumberFound(number: Int, activators: Set[Position])

  private def computeActivators(row: Int, startColInclusive: Int, endColExclusive: Int) =
    (startColInclusive - 1 to endColExclusive)
      .flatMap(c => List(Position(row - 1, c), Position(row + 1, c)))
      .toSet
      .incl(Position(row, startColInclusive - 1))
      .incl(Position(row, endColExclusive))
}

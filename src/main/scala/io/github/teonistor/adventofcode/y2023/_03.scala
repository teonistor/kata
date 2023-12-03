package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _03 extends StandardAdventOfCodeSolution[Long]{

  override def _1(input: String): Long = {
    val splitter = "([^.0-9]+)|(\\.+)|([0-9]+)".r

    val glob = input.split("\n").iterator
      .zipWithIndex
      .flatMap { case (line, row) =>
        splitter.findAllMatchIn(line).flatMap { mtch =>
          if (mtch.group(1) != null)
            Some(Left(Position(row, mtch.start)))
          else if (mtch.group(2) != null)
            None
          else
            Some(Right(NumberFound(mtch.group(3).toInt, computeActivators(row, mtch.start, mtch.end))))
        }

      }
      .toList

    val activators = glob.iterator.flatMap {
      case Left(pos) => Some(pos)
      case _=> None
    }.toSet
    val numbersFound = glob.iterator.flatMap {
      case Right(nf) => Some(nf)
      case _=> None
    }.toSet

    numbersFound.iterator
      .filter(_.activators.exists(activators))
      .map(_.number.toLong)
      .sum
  }

  override def _2(input: String): Long = {
    val splitter = "([^*0-9]+)|(\\*+)|([0-9]+)".r

    val glob = input.split("\n").iterator
      .zipWithIndex
      .flatMap { case (line, row) =>
        splitter.findAllMatchIn(line).flatMap { mtch =>
          if (mtch.group(1) != null)
            None
          else if (mtch.group(2) != null)
            Some(Left(Position(row, mtch.start)))
          else
            Some(Right(NumberFound(mtch.group(3).toInt, computeActivators(row, mtch.start, mtch.end))))
        }

      }
      .toList

    val activators = glob.iterator.flatMap {
      case Left(pos) => Some(pos)
      case _ => None
    }.toSet
    val numbersFound = glob.iterator.flatMap {
      case Right(nf) => Some(nf)
      case _ => None
    }.toSet

    activators.iterator
      .map(a => numbersFound.filter(_.activators contains a))
      .filter(_.size == 2)
      .map(_.iterator.map(_.number.toLong).product)
      .sum
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

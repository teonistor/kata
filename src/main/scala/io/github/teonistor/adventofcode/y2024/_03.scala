package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _03 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long = {
    val finder = "mul\\((\\d+),(\\d+)\\)".r
    finder.findAllMatchIn(input)
      .map(m => List(m.group(1), m.group(2))
      .map(_.toLong)
      .product)
      .sum
  }

  override def _2(input: String): Long = {
    val finder = "mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))".r
    finder.findAllMatchIn(input)
      .map(m =>
        if (m.group(3) != null) Left(true)
        else if (m.group(4) != null) {
          println("bong")
          Left(false)
        }
        else Right(List(m.group(1), m.group(2))
          .map(_.toLong)
          .product) )
      .foldLeft((true, 0L)){
        case ((_,sum),Left(enabled)) => (enabled,sum)
        case ((true,sum), Right(next)) =>( true,sum + next)
        case (otherwise, _) => otherwise
      }
      ._2
  }
}

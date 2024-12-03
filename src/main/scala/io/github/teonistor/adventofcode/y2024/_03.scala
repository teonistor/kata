package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

// noinspection NameBooleanParameters
object _03 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long =
    solve(input, true)

  override def _2(input: String): Long =
    solve(input, false)

  private def solve(input: String, ignoreOnOff: Boolean) =
    "mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))".r
      .findAllMatchIn(input)
      .map(m =>
        if (m.group(3) != null) Left(true)
        else if (m.group(4) != null) Left(ignoreOnOff)
        else Right(List(1, 2)
          .map(m.group(_).toLong)
          .product))
      .foldLeft((true, 0L)) {
        case ((_, sum), Left(enabled)) => (enabled, sum)
        case ((true, sum), Right(next)) => (true, sum + next)
        case (otherwise, _) => otherwise
      }
      ._2
}

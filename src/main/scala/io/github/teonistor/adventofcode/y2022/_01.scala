package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _01 extends StandardAdventOfCodeSolution[Int]{

  def _1(input: String): Int =
    solve(input, 1)

  def _2(input: String): Int =
    solve(input, 3)

  private def solve(input:String, howMany:Int) =
    input.split("\n\n").to(LazyList)
      .map(_.split("\n")
        .map(_.toInt)
        .sum)
      .sortBy(-_)
      .take(howMany)
      .sum
}

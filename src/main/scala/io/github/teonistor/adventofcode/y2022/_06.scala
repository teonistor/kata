package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

object _06 extends AdventOfCodeSolution[Int] {

  def _1(input: String): Int =
    solve(input, 4)

  def _2(input: String): Int =
    solve(input, 14)

  private def solve(input: String, magicSize: Int) = {
    (0 to input.length - 5)
      .filter(i => input.slice(i, i + magicSize).toSet.size == magicSize)
      .head + magicSize
  }
}

package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

object _04 extends AdventOfCodeSolution[Int] {

  def _1(input: String): Int =
    solve(input, a => a(0) <= a(2) && a(1) >= a(3) || a(0) >= a(2) && a(1) <= a(3))

  def _2(input: String): Int =
    solve(input, a => a(1) >= a(2) && a(0) <= a(3))

  private def solve(input: String, condition: Array[Int] => Boolean) =
    input.split("\n").to(LazyList)
      .map(_.split(",")
        .flatMap(_.split("-"))
        .map(_.toInt))
      .count(condition)
}

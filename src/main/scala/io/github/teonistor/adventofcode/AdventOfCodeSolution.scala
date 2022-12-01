package io.github.teonistor.adventofcode

abstract class AdventOfCodeSolution[T](val day: Int) {
  def _1(input: String): T
  def _2(input: String): T
}

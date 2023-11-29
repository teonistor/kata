package io.github.teonistor.adventofcode

trait AdventOfCodeSolution[T] {
  def year: Int
  def day: Int
  def _1(input: String): T
  def _2(input: String): T
}

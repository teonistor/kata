package io.github.teonistor.adventofcode

object _06 {

  def _1(input: String): Int = input
    .split("\n\n").map(_.toSet.count(_ != '\n')).sum

  def _2(input: String): Int = input
    .split("\n\n")
    .map(_.split("\n")
      .map(_.toSet))
    .map(_.reduce(_ & _))
    .map(_.size)
    .sum
}

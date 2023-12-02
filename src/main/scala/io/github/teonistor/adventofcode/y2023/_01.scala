package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _01 extends StandardAdventOfCodeSolution[Long] {

  private val numbers = Array("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    .zipWithIndex
    .map { case (s, i) => (s, (i + 1).toLong) }
    .toMap
    .concat((0 to 9)
      .map(i => (i.toString, i.toLong)))

  override def _1(input: String): Long =
    input.split("\n").map { line =>
      val first = line.find(c => '0' <= c && c <= '9')
      val last = line.findLast(c => '0' <= c && c <= '9')
      (first.get - '0') * 10 + last.get - '0'
    }.sum

  override def _2(input: String): Long =
    input.split("\n").map { line =>
      val first = numbers.keys.minBy(s => Some(line.indexOf(s)).filter(_ >= 0).getOrElse(Int.MaxValue))
      val last = numbers.keys.maxBy(line.lastIndexOf)

      numbers(first) * 10 + numbers(last)
    }.sum
}

package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

object _03 extends AdventOfCodeSolution[Int](3) {

  def _1(input: String): Int = input
    .split("\n").to(LazyList)
    .map(sack => sack.splitAt(sack.length / 2))
    .map(compartments => compartments._1.toSet & compartments._2.toSet)
    .map(_.head)
    .map(c => if (c >= 'a') c - 'a' + 1 else c - 'A' + 27)
    .sum

  def _2(input: String): Int = input
    .split("\n").to(LazyList)
    .grouped(3)
    .map(_
      .map(_.toSet)
      .reduce(_ & _)
      .head)
    .map(c => if (c >= 'a') c - 'a' + 1 else c - 'A' + 27)
    .sum
}

package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution
import io.github.teonistor.func.Utils.HasLet

object _03 extends AdventOfCodeSolution[Int] {
  private type PartFunc = Seq[String] => Iterable[Iterable[String]]

  def _1(input: String): Int =
    solve(input, part1SpecialLogic)

  def _2(input: String): Int =
    solve(input, part2SpecialLogic)

  private def solve(input: String, partSpecialLogic: PartFunc): Int = input
    .split("\n").to(LazyList)
    .let(partSpecialLogic)
    .map(_.map(_.toSet)
      .reduce(_ & _)
      .head)
    .map(c => if (c >= 'a') c - 'a' + 1 else c - 'A' + 27)
    .sum

  private def part1SpecialLogic(sacks: Seq[String]) = sacks
    .map(sack => sack.splitAt(sack.length / 2))
    .map(compartments => List(compartments._1, compartments._2))

  private def part2SpecialLogic(sacks: Seq[String]) = sacks
    .grouped(3)
    .to(LazyList)
}

package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.collection.mutable

object _14 extends StandardAdventOfCodeSolution[Long] {

  private val insertionRule = "([A-Z]{2}) -> ([A-Z])".r

  def _1(input: String): Long = {
    solve(input, 10)
  }

  def _2(input: String): Long = {
    solve(input, 40)
  }

  private def solve(input: String, steps: Int): Long = {
    val templateAndRules = input.split("\n\n")

    val rules = templateAndRules(1).split("\n").to(LazyList)
      .map { case insertionRule(k, v) => (k, v(0)) }
      .toMap
    val memo = mutable.Map.empty[(Char, Char, Int), Map[Char, Long]]

    val end = templateAndRules(0).length - 2
    val counts = (0 to end).to(LazyList)
      .map(i => solveAnotherWay(templateAndRules(0)(i), templateAndRules(0)(i+1), steps, rules, memo))
      .prepended(Map((templateAndRules(0).last, 1L)))
      .reduce(addMaps[Char])
      .values
    counts.max - counts.min
  }

  private def solveAnotherWay(left: Char, right: Char, steps: Int, rules: Map[String, Char], memo: mutable.Map[(Char, Char, Int), Map[Char, Long]]): Map[Char, Long] = {
    if (memo.contains((left, right, steps)))
      memo((left, right, steps))

    else {
      val result = if (steps < 1)
        Map((left, 1L))

      else {
        val middle = rules("" + left + right)
        addMaps(
          solveAnotherWay(left, middle, steps - 1, rules, memo),
          solveAnotherWay(middle, right, steps - 1, rules, memo))
      }

      memo.put((left, right, steps), result)
      result
    }
  }

  private def addMaps[K](left: Map[K, Long], right: Map[K, Long]): Map[K, Long] =
    LazyList(left, right).flatten.groupMapReduce(_._1)(_._2)(_ + _)
}

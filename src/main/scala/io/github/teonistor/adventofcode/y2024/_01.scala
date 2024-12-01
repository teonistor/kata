package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.math.abs

object _01 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long = {
    val (l, r) = readImput(input)

    (l.sorted zip r.sorted)
      .map { case (a, b) => abs(a - b) }
      .sum
  }

  override def _2(input: String): Long = {
    val (l, r) = readImput(input)
    val lg = l.groupMapReduce(identity)(_=> 1L)(_ + _)
    val rg = r.groupMapReduce(identity)(_=> 1L)(_ + _)

    lg.keysIterator
      .map(k => k * rg.getOrElse(k, 0L) * lg(k))
      .sum
  }

  private def readImput(input: String) =
    input.split("\n")
      .map(line => line.split("\\s+")
        .map(_.toLong))
      .map(a => (a(0), a(1)))
      .unzip
}

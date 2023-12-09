package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _09 extends StandardAdventOfCodeSolution [Long] {
  override def _1(input: String): Long =
    solve(input, _.last+_)

  override def _2(input: String): Long =
    solve(input, _.head-_)

  private def solve(input: String, op: (Seq[Long], Long) => Long) =
    input.split("\n").iterator
      .map(_.split(" +").toList.map(_.toLong))
      .map(prediction(_, op))
      .sum

  private def prediction(seq: Seq[Long], op: (Seq[Long], Long) => Long): Long =
    if (seq.forall(_ == 0L))
      0
    else
      op(seq, prediction((seq.tail zip seq).map { case (b, a) => b - a }, op))
}

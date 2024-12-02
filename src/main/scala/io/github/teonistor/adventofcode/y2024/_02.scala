package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.math.abs

object _02 extends StandardAdventOfCodeSolution[Int] {

  override def _1(input: String): Int =
    solve(input, Iterator(_))

  override def _2(input: String): Int =
    solve(input, vec => Iterator(Some(vec),
      vec.indices.iterator
        .map(gap => vec.take(gap) ++ vec.drop(gap + 1)))
      .flatten)

  private def solve(input: String, expander: Vector[Long] => Iterator[Vector[Long]]) =
    input.split("\n").iterator
      .map(_.split(" +").to(Vector)
        .map(_.toLong))
      .map(expander)
      .count(_
        .map(arr => arr.zip(arr.drop(1))
          .map {
            case (l, r) =>
              if (l == r || abs(l - r) > 3) 0
              else if (l < r) 1
              else -1
          }.toSet)
        .exists(set => set.size == 1 && !set.contains(0)))
}

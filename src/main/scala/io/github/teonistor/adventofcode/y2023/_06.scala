package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.{ceil, floor, sqrt}

object _06 extends StandardAdventOfCodeSolution[Long]{

  override def _1(input: String): Long =
    solve(input, _.replaceAll("(Time|Distance):", "").strip().split("\\s+"))

  override def _2(input: String): Long =
    solve(input, s => Some(s.replaceAll("[^0-9]", "")))

  private def solve(input:String, parseMode:String=>Iterable[String]) = {
    val times :: distances :: Nil = input.split("\n").iterator
      .map(parseMode.andThen(_.iterator
        .map(_.toLong)))
      .toList

    (times zip distances).map { case (distance, time) =>
      val delta = distance * distance - 4 * time
      if (delta < 0)
        throw new IllegalStateException(s"No real roots for $distance, $time")

      val sqrtdd = sqrt(delta) / 2
      val nd = distance.toDouble / 2

      ceil(nd + sqrtdd).toLong - floor(nd - sqrtdd).toLong - 1
    }
      .product
  }
}

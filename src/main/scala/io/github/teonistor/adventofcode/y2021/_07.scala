package io.github.teonistor.adventofcode.y2021

import scala.math.abs

object _07 {

  def _1(input: String): Long = all(input, identity)

  def _2(input: String): Long = all(input, n => n * (n + 1) / 2)

  private def all(input: String, func: Int => Int) = {
    val numbers = input.split(",").toList.map(_.toInt)
    val min = numbers.min
    val max = numbers.max
    (min to max).map(candidate => numbers.map(_- candidate).map(abs).map(func).sum).min
  }
}

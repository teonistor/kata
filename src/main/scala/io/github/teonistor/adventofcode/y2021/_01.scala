package io.github.teonistor.adventofcode.y2021

import scala.annotation.tailrec

object _01 {

  def _1(input: String): Int = {
    val numbers = input.split("\n").toList.map(_.toInt)
    recursivelyCount(numbers.head, numbers.tail)
  }

  def _2(input: String): Int = {
    val numbers = input.split("\n").map(_.toInt)
    val sums = (2 until numbers.length).map(i => numbers(i - 2) + numbers(i - 1) + numbers(i))
    recursivelyCount(sums.head, sums.tail)
  }

  @tailrec
  private def recursivelyCount(number: Int, moreNumbers: Seq[Int], counter: Int = 0): Int = {
    if (moreNumbers.isEmpty)
      counter
    else
      recursivelyCount(moreNumbers.head, moreNumbers.tail, if (number < moreNumbers.head) counter + 1 else counter)
  }
}

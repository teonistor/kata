package io.github.teonistor.adventofcode

import scala.collection.immutable.SortedSet
import scala.collection.mutable

object _10 {

  def _1(input: String): Int = {
    val sorted = input.split('\n').to(LazyList).map(_.toInt).sorted
    val countDiffs = sorted.prepended(0).zip(sorted.appended(sorted.last + 3))
      .map(pair => pair._2 - pair._1)
      .groupMapReduce(identity)(_ => 1)(_ + _)
    countDiffs(1) * countDiffs(3)
  }

  def _2(input: String): Long = {
    val sorted = input.split('\n').map(_.toInt).to(SortedSet).incl(0)

    val memo = new mutable.HashMap[Int, Long](sorted.size, 1.0)
    memo.put(sorted.max, 1)

    def calculate(key: Int): Long = {
      if (memo contains key)
        memo(key)

      else if (sorted contains key) {
        val value = calculate(key + 1) + calculate(key + 2) + calculate(key + 3)
        memo.put(key, value)
        value
      } else {
        0
      }
    }

    calculate(0)
  }
}

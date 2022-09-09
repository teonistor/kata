package io.github.teonistor.kata

import scala.collection.mutable

class Euler351(size: Int) {

  /* TODO Explain theory
   *
   */

  def hiddenPoints(): Long = {
    val line = size-1
    val restOfIt = restOfPoints(2)
    (line + restOfIt) * 6
  }

  def restOfPoints(level: Int):Long = {
    if (level >= size - 2)
      return 0
    val line = size / level - 1
    // My initial mind said `level to level * 2 - 2` but that overshot
    // With `until` 5 and 10 are fine, but 1000 overshoots
    // There's a more systemic overcounting problem
    val linesInBetween = (level until level * 2 - 2).map(size / _).sum
    val restOfIt = restOfPoints(level * 2)
    line + 2 * restOfIt + linesInBetween
  }

  private def toInclude(a: Int, b: Int) =
    a != 1 && !coprimes(a, b)

  private[kata] final def generatePoints() =
    (0 until size).flatMap(b =>
      (1 to size - b).map(a =>
        (a, b)))

  private[kata] final def coprimes(a: Int, b: Int) =
    hcd(a, b) == 1

  private val hcdMemo = mutable.HashMap.empty[(Int, Int), Int]
  private[kata] final def hcd(a: Int, b: Int): Int =
    hcdMemo.getOrElseUpdate((a, b),
      hcdMemo.getOrElseUpdate((b, a),
        if (a <= 0)
          b
        else if (b <= 0)
          a
        else if (a < b)
          hcd(a, b-a)
        else
          hcd(a-b, a)))
}

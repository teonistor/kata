package io.github.teonistor.kata

import scala.collection.mutable

class Euler351(size: Int) {

  def hiddenPoints(): Long =
    generatePoints()
//      .to(LazyList)
//      .count((coprimes _).tupled)
      .filter((toInclude _).tupled)
      .map(_=> 1L)
      .sum * 6

 /* def hiddenPoints(): Long =
    generatePoints()
      //      .to(LazyList)
      //      .count((coprimes _).tupled)
      .map(t => {
        //        val b = coprimes _ tupled t
        val k = t._1 == 1
        val b = coprimes(t._1, t._2)
        val s = if (k) "veto" else if (b) "yes" else "no"
        println(s"$t -> $s")
        if (!b && !k) 1L else 0L
      })
      .sum * 6 */

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

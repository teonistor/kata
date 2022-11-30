package io.github.teonistor.adventofcode.y2021

import java.lang.Math.{max, min}
import scala.annotation.tailrec

object _22 {
  private val instruction = "(on|off) x=([-0-9]+)..([-0-9]+),y=([-0-9]+)..([-0-9]+),z=([-0-9]+)..([-0-9]+)".r

  def _1(input: String): Long = {
    input.split("\n").to(LazyList).map {
      case instruction(oo, x1, x2, y1, y2, z1, z2) => (oo, adjustRangeS(x1, x2).flatMap(x =>
        adjustRangeS(y1, y2).flatMap(y =>
          adjustRangeS(z1, z2).map(z =>
//            x + y * 51L + z * 51L * 51L))))
            (x, y, z)))))
    } .map {
      case ("on",  set) => (_: Set[(Int,Int,Int)]) ++ set
      case ("off", set) => (_: Set[(Int,Int,Int)]) -- set
    } .foldLeft(Set.empty[(Int,Int,Int)])((set, op) => op(set))
      .size
  }

  def _2(input: String): Long = {
    println("Unimplemented")
    0
  }

  private def adjustRangeS(l: String, r: String) =
    adjustRange(l.toInt, r.toInt)

  @tailrec
  private def adjustRange(l: Int, r: Int): LazyList[Int] =
    if (l > r)
      adjustRange(r, l)
    else if (r < -50 || l > 50)
      LazyList.empty
    else
      (max(l, -50) to min(r, 50)).to(LazyList)
}

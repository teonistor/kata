package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.{max, min}
import scala.annotation.tailrec

object _22 extends StandardAdventOfCodeSolution[Long] {
  private val instruction = "(on|off) x=([-0-9]+)..([-0-9]+),y=([-0-9]+)..([-0-9]+),z=([-0-9]+)..([-0-9]+)".r

  def _1(input: String): Long = {
    solve(input, adjustRangeS)
  }

  def _2(input: String): Long = {
    solve(input, unadjustedRangeS)
  }

  private def solve(input: String, ranger:(String,String)=>LazyList[Int]): Long = {
    input.split("\n").to(LazyList).map {
      case instruction(oo, x1, x2, y1, y2, z1, z2) => (oo, ranger(x1, x2).flatMap(x =>
        ranger(y1, y2).flatMap(y =>
          ranger(z1, z2).map(z =>
            x + y * 101L + z * 101L * 101L))))
//            (x, y, z)))))
    } .map {
      case ("on",  set) => (_: Set[Long]) ++ set
      case ("off", set) => (_: Set[Long]) -- set
    } .foldLeft(Set.empty[Long])((set, op) => op(set))
      .size
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

  private def unadjustedRangeS(l: String, r: String) =
    unadjustedRange(l.toInt, r.toInt)

  @tailrec
  private def unadjustedRange(l: Int, r: Int): LazyList[Int] =
    if (l > r)
      unadjustedRange(r, l)
    else
      (l to r).to(LazyList)
}

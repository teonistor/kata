package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.math.abs

object _02 extends StandardAdventOfCodeSolution[Int] {

  override def _1(input: String): Int = {
    input.split("\n").iterator
      .map(_.split(" +").to(Vector)
        .map(_.toLong))
      .map(arr => (arr zip arr.drop(1) ).map {
        case (l,r) =>
          if (l == r || abs(l - r) > 3) 0
          else if (l < r) 1
          else -1
      }
      .toSet)
      .count(set => set.size == 1 && !set.contains(0))
  }

  def orr[T](t:Iterator[T]):Iterator[T] = {
    val a = t.to(Vector)
    println(a)
    a.iterator
  }

  override def _2(input: String): Int = {
    input.split("\n").iterator
      .map(_.split(" +").to(Vector)
        .map(_.toLong))
      .count(vec => Iterator(Some(vec),
          vec.indices.iterator
          .map(gap => vec.take(gap) ++ vec.drop(gap + 1)))
        .flatten
        .map(arr => arr zip arr.drop(1) )
        .map(_.map {
            case (l,r) =>
              if (l == r || abs(l - r) > 3) 0
              else if (l < r) 1
              else -1
        }.toSet)
        .exists(set => set.size == 1 && !set.contains(0)))


//        .foldLeft((2, 2)) {
//          case ((up, down),(l,r)) =>
//            val (du, dd) =
//              if (l == r || abs(l - r) > 3) (1, 1)
//              else if (l < r) (0, 1)
//              else (1, 0)
//            (up - du, down - dd)
//        })


//        .foldLeft((true, true, true, true)) {
//          // dampenerUp, dampenerDown, canGoUp, canGoDown
//
//          case((false, false, false, false),(_,_)) => (false, false, false, false)
//
//          case ((dampenerUp, dampenerDown, canGoUp, canGoDown),(l,r)) =>
//            val dir = if (l == r || abs(l - r) > 3) 0
//              else if (l < r) 1
//              else -1
//            val (nextDampenerUp,nextCanGoUp) = if (!canGoUp) (false, false)
//              else if (dir == 1) (dampenerUp, true)
//              else (false, dampenerUp)
//            val (nextDampenerDown,nextCanGoDown) = if (!canGoDown) (false, false)
//              else if (dir == -1) (dampenerDown, true)
//              else (false, dampenerDown)
//
//            (nextDampenerUp,nextDampenerDown, nextCanGoUp, nextCanGoDown)
//        })

//      .count {
//        case (up, down) =>
//          println((up, down))
//          up > 0 || down > 0
//      }
  }
}

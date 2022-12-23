package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec

object _23 extends AdventOfCodeSolution[Int] {

  private type MoveSet = (((Int, Int)) => (Int, Int), ((Int, Int)) => Set[(Int, Int)])

  def _1(input: String): Int = {

    val occupied = input.split("\n").to(LazyList)
      .zipWithIndex
      .flatMap(si => si._1.zipWithIndex
        .filter(cj => cj._1 == '#')
        .map(cj => (si._2, cj._2)))
      .toSet

    val moves = LazyList[MoveSet](
      (p => (p._1 - 1, p._2), p => Set((p._1 - 1, p._2 - 1), (p._1 - 1, p._2), (p._1 - 1, p._2 + 1))),
      (p => (p._1 + 1, p._2), p => Set((p._1 + 1, p._2 - 1), (p._1 + 1, p._2), (p._1 + 1, p._2 + 1))),
      (p => (p._1, p._2 - 1), p => Set((p._1 - 1, p._2 - 1), (p._1, p._2 - 1), (p._1 + 1, p._2 - 1))),
      (p => (p._1, p._2 + 1), p => Set((p._1 - 1, p._2 + 1), (p._1, p._2 + 1), (p._1 + 1, p._2 + 1))))
    val m= LazyList.continually(moves).flatten
    val finalPlacement = move(m, occupied, Some(10))._2

    println(s"Started with ${occupied.size}, ended with ${finalPlacement.size}")

    val minI = finalPlacement.map(_._1).min
    val maxI = finalPlacement.map(_._1).max
    val minJ = finalPlacement.map(_._2).min
    val maxJ = finalPlacement.map(_._2).max

    (maxI - minI + 1) * (maxJ - minJ + 1) - finalPlacement.size
  }

  def _2(input: String): Int = {

    val occupied = input.split("\n").to(LazyList)
      .zipWithIndex
      .flatMap(si => si._1.zipWithIndex
        .filter(cj => cj._1 == '#')
        .map(cj => (si._2, cj._2)))
      .toSet

    val moves = LazyList[MoveSet](
      (p => (p._1 - 1, p._2), p => Set((p._1 - 1, p._2 - 1), (p._1 - 1, p._2), (p._1 - 1, p._2 + 1))),
      (p => (p._1 + 1, p._2), p => Set((p._1 + 1, p._2 - 1), (p._1 + 1, p._2), (p._1 + 1, p._2 + 1))),
      (p => (p._1, p._2 - 1), p => Set((p._1 - 1, p._2 - 1), (p._1, p._2 - 1), (p._1 + 1, p._2 - 1))),
      (p => (p._1, p._2 + 1), p => Set((p._1 - 1, p._2 + 1), (p._1, p._2 + 1), (p._1 + 1, p._2 + 1))))
    val m = LazyList.continually(moves).flatten
    val finalPlacement = move(m, occupied)._1
    finalPlacement

//    println(s"Started with ${occupied.size}, ended with ${finalPlacement.size}")
//
//    val minI = finalPlacement.map(_._1).min
//    val maxI = finalPlacement.map(_._1).max
//    val minJ = finalPlacement.map(_._2).min
//    val maxJ = finalPlacement.map(_._2).max
//
//    (maxI - minI + 1) * (maxJ - minJ + 1) - finalPlacement.size
  }

  @tailrec
  private def move(moves: LazyList[MoveSet], occupied: Set[(Int, Int)], roundLimit: Option[Int] = None, rounds: Int = 1): (Int,Set[(Int, Int)]) = {
//    println(occupied)
    if (roundLimit.exists(_ < rounds))
      return (rounds,occupied)

    val occupiedByNeedToMove = occupied.groupBy(p =>
      (surroundings _).tupled(p).exists(occupied.contains))

    if (occupiedByNeedToMove.contains(true)) {
//      moves.take(4).map(_._2((1,1))).foreach(println)
//      println()

      val needsToMoveByWhere = occupiedByNeedToMove(true).groupBy(p => moves.take(4)
        .find(!_._2(p).exists(occupied.contains))
        .map(_._1(p)))

//      println(s"NtMbW:$needsToMoveByWhere")

      val havingMoved = needsToMoveByWhere.to(LazyList).flatMap {
        case (None, unmoved) => unmoved
        case (_, conflicting) if conflicting.size > 1 => conflicting
        case (unique, _) => unique
      }

      move(moves.tail, occupiedByNeedToMove.getOrElse(false, Set.empty) ++ havingMoved, roundLimit, rounds + 1)

    } else
      (rounds,occupied)
  }

  private def surroundings(i: Int, j: Int) = Set(
    (i - 1, j - 1),
    (i - 1, j),
    (i - 1, j + 1),
    (i, j - 1),
    (i, j + 1),
    (i + 1, j - 1),
    (i + 1, j),
    (i + 1, j + 1))
}

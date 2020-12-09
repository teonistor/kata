package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _23 extends StandardAdventOfCodeSolution[Int] {

  private type MoveSet = (((Int, Int)) => (Int, Int), ((Int, Int)) => Set[(Int, Int)])

  private val startingMoves = {
    // Don't inline, because we don't want to recompute all these lambda call sites all the time
    val movesOnce = List[MoveSet](
      (p => (p._1 - 1, p._2), p => Set((p._1 - 1, p._2 - 1), (p._1 - 1, p._2), (p._1 - 1, p._2 + 1))),
      (p => (p._1 + 1, p._2), p => Set((p._1 + 1, p._2 - 1), (p._1 + 1, p._2), (p._1 + 1, p._2 + 1))),
      (p => (p._1, p._2 - 1), p => Set((p._1 - 1, p._2 - 1), (p._1, p._2 - 1), (p._1 + 1, p._2 - 1))),
      (p => (p._1, p._2 + 1), p => Set((p._1 - 1, p._2 + 1), (p._1, p._2 + 1), (p._1 + 1, p._2 + 1))))
    LazyList.continually(movesOnce).flatten
  }

  def _1(input: String): Int = {
    val finalPlacement = solve(input, Some(10))._2

    val minI = finalPlacement.map(_._1).min
    val maxI = finalPlacement.map(_._1).max
    val minJ = finalPlacement.map(_._2).min
    val maxJ = finalPlacement.map(_._2).max

    (maxI - minI + 1) * (maxJ - minJ + 1) - finalPlacement.size
  }

  def _2(input: String): Int =
    solve(input)._1

  /**
   * @return The count of rounds played and the set of occupied positions at the end
   */
  private def solve(input: String, roundLimit: Option[Int] = None) = {
    val occupied = input.split("\n").to(LazyList)
      .zipWithIndex
      .flatMap(si => si._1.zipWithIndex
        .filter(cj => cj._1 == '#')
        .map(cj => (si._2, cj._2)))
      .toSet

    move(startingMoves, occupied, roundLimit)
  }

  @tailrec
  private def move(moves: LazyList[MoveSet], occupied: Set[(Int, Int)], roundLimit: Option[Int], rounds: Int = 1): (Int, Set[(Int, Int)]) = {
    if (roundLimit.exists(_ < rounds))
      return (rounds, occupied)

    val occupiedByNeedToMove = occupied.groupBy((surroundings _).tupled(_).exists(occupied.contains))
    if (occupiedByNeedToMove contains true) {

      val havingMoved = occupiedByNeedToMove(true)
        .groupBy(p => moves.take(4)
          .find(!_._2(p).exists(occupied.contains))
          .map(_._1(p)))
        .to(LazyList).flatMap {
          case (None, unmoved) => unmoved
          case (_, conflicting) if conflicting.size > 1 => conflicting
          case (unique, _) => unique
        }

      move(moves.tail, occupiedByNeedToMove.getOrElse(false, Set.empty) ++ havingMoved, roundLimit, rounds + 1)

    } else
      (rounds, occupied)
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

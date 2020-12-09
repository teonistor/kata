package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.abs
import scala.annotation.tailrec

object _09 extends StandardAdventOfCodeSolution[Int] {
  private type Point = (Int, Int)

  private case class State(rope: List[Point],
                           trail: Set[Point])

  private object State {
    private[y2022] def start(n: Int) =
      State(List.fill(n)((0,0)), Set((0,0)))
  }

  def _1(input: String): Int =
    solve(input, 2)

  def _2(input: String): Int =
    solve(input, 10)

  private def solve(input: String, size: Int) =
    input.split("\n").toList
      .map(_.split(" "))
      .foldLeft(State.start(size))((state, step) => solveOneLine(state, step(0)(0), step(1).toInt))
      .trail.size

  private def sign(d: Int) =
    if (d > 0) 1
    else if (d < 0) -1
    else 0

  private[y2022] def moveTowards(leader: Point, follower: Point) =
    if (abs(leader._1 - follower._1) > 1 || abs(leader._2 - follower._2) > 1)
      ( follower._1 + sign(leader._1 - follower._1),
        follower._2 + sign(leader._2 - follower._2) )
    else
      follower

  @tailrec
  private def oneMoveReversed(current: Point,
                              remaining: List[Point],
                              done: List[Point] = List.empty): List[Point] =
    if (remaining.isEmpty)
      done.prepended(current)
    else
      oneMoveReversed(moveTowards(current, remaining.head), remaining.tail, done.prepended(current))

  @tailrec
  private def solveOneLine(state: State, direction: Char, steps: Int): State =
    if (steps < 1)
      state
    else {
      val head = state.rope.head
      val newHead = direction match {
        case 'R' => (head._1 + 1, head._2)
        case 'L' => (head._1 - 1, head._2)
        case 'U' => (head._1, head._2 - 1)
        case 'D' => (head._1, head._2 + 1)
      }
      val reversedRope = oneMoveReversed(newHead, state.rope.tail)

      solveOneLine(State(reversedRope.reverse, state.trail.incl(reversedRope.head)), direction, steps - 1)
    }
}

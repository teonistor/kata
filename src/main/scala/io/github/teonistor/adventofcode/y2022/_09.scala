package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.lang.Math.abs
import scala.annotation.tailrec

object _09 extends AdventOfCodeSolution[Int] {
  private type Point = (Int, Int)

  private case class State(rope: List[Point],
                           trail: Set[Point]) {
    @deprecated
    def head = rope.head
    @deprecated
    def tail = rope.reverse.head
  }

  private object State {
    private[y2022] def start(n: Int) =
      State(List.fill(n)((0,0)), Set((0,0)))
  }

  def _1(input: String): Int =
    solve0(input, 2)

  def _2(input: String): Int =
    solve0(input, 10)

  private def solve0(input: String, size: Int) =
    input.split("\n").toList
      .foldLeft(State.start(size))((state, steps) => {
        val split = steps.split(" ")
        solve(state, split(0)(0), split(1).toInt)
      }).trail.size

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
  private def solve(state: State, direction: Char, steps: Int): State =
    if (steps < 1)
      state
    else
      direction match {
        case 'R' =>
          val newHead = (state.head._1 + 1, state.head._2)
          val newTail = if (newHead._1 > state.tail._1 + 1)
              state.head
            else
              state.tail
          solve(State(List(newHead, newTail), state.trail.incl(newTail)), direction, steps - 1)
        case 'L' =>
          val newHead = (state.head._1 - 1, state.head._2)
          val newTail = if (newHead._1 < state.tail._1 - 1)
              state.head
            else
              state.tail
          solve(State(List(newHead, newTail), state.trail.incl(newTail)), direction, steps - 1)
        case 'U' =>
          val newHead = (state.head._1, state.head._2 - 1)
          val newTail = if (newHead._2 < state.tail._2 - 1)
              state.head
            else
              state.tail
          solve(State(List(newHead, newTail), state.trail.incl(newTail)), direction, steps - 1)
        case 'D' =>
          val newHead = (state.head._1, state.head._2 + 1)
          val newTail = if (newHead._2 > state.tail._2 + 1)
              state.head
            else
              state.tail
          solve(State(List(newHead, newTail), state.trail.incl(newTail)), direction, steps - 1)
      }
}

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
  private def oneMove(current: Point,
                      remaining: List[Point],
                      done: List[Point] = List.empty): List[Point] =
    if (remaining.isEmpty)
      done.prepended(current)
    else
      oneMove(moveTowards(current, remaining.head), remaining.tail, done.prepended(current))

  @tailrec
  private def solve(state: State, direction: Char, steps: Int): State =
    if (steps < 1)
      state
    else {
      val newHead = direction match {
        case 'R' =>(state.head._1 + 1, state.head._2)
        case 'L' =>(state.head._1 - 1, state.head._2)
        case 'U' => (state.head._1, state.head._2 - 1)
        case 'D' => (state.head._1, state.head._2 + 1)
      }

      val rev = oneMove(newHead, state.rope.tail)
      solve(State(rev.reverse, state.trail.incl(rev.head)), direction, steps - 1)
    }
}

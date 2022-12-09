package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec

object _09 extends AdventOfCodeSolution[Int] {

  private case class State(head: (Int,Int),
                           tail: (Int,Int),
                           trail: Set[(Int,Int)]) {
  }

  private object State {
    val nil = State((0,0), (0,0), Set((0,0)))
  }

  def _1(input: String): Int =
    input.split("\n").toList
      .foldLeft(State.nil)((state, steps) => {
        val split = steps.split(" ")
        solve(state, split(0)(0), split(1).toInt)
      }).trail.size

  def _2(input: String): Int =
    1

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
          solve(State(newHead, newTail, state.trail.incl(newTail)), direction, steps - 1)
        case 'L' =>
          val newHead = (state.head._1 - 1, state.head._2)
          val newTail = if (newHead._1 < state.tail._1 - 1)
              state.head
            else
              state.tail
          solve(State(newHead, newTail, state.trail.incl(newTail)), direction, steps - 1)
        case 'U' =>
          val newHead = (state.head._1, state.head._2 - 1)
          val newTail = if (newHead._2 < state.tail._2 - 1)
              state.head
            else
              state.tail
          solve(State(newHead, newTail, state.trail.incl(newTail)), direction, steps - 1)
        case 'D' =>
          val newHead = (state.head._1, state.head._2 + 1)
          val newTail = if (newHead._2 > state.tail._2 + 1)
              state.head
            else
              state.tail
          solve(State(newHead, newTail, state.trail.incl(newTail)), direction, steps - 1)
      }
}

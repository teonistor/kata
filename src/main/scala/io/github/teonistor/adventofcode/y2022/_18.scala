package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

object _18 extends AdventOfCodeSolution[Int] {

  private case class Point(x:Int, y:Int, z:Int) {
    def up = copy(y=y+1)
    def down = copy(y=y-1)
    def left = copy(x=x-1)
    def right = copy(x=x+1)
    def forth = copy(z=z+1)
    def back = copy(z=z-1)

    def neighbours = LazyList(up, down, left, right, forth, back)
  }

  def _1(input: String): Int = {
    input.split("\n").to(LazyList)
      .map(_.split(",").map(_.toInt))
      .map(arr => Point(arr(0), arr(1), arr(2)))

      .foldLeft((0, Set.empty[Point]))((existing, point) => {
        (existing._1 + 6 - 2 * point.neighbours.count(existing._2.contains),
          existing._2 + point)
      })
      ._1
  }



  def _2(input: String): Int =
    1
}

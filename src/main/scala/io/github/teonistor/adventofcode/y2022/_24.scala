package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _24 extends AdventOfCodeSolution[Int] {

  private case class Bliz (i: Int, j: Int, moveI: Int => Int, moveJ: Int => Int) {

    private[y2022] def move() = copy(i = moveI(i), j = moveJ(j))
  }

  // 11 s
  def _1(input: String): Int =
    solve(input)((start, finish, solver) =>
      solver(0, start, finish))

  // 40 s
  def _2(input: String): Int =
    solve(input)((start, finish, solver) =>
      solver(solver(solver(0, start, finish), finish, start), start, finish))

  private def solve[A](input: String)(continuation: ((Int, Int), (Int, Int), (Int, (Int, Int), (Int, Int)) => Int) => A) = {
    val chart = input.split("\n")
    val start = (0, chart(0).indices.find(chart(0)(_) == '.').get)
    val finish = (chart.length - 1, chart.last.indices.find(chart.last(_) == '.').get)

    val startingBlizz = (1 to chart.length - 2).flatMap(i =>
      (1 to chart(i).length - 2).flatMap(j =>
        chart(i)(j) match {
          case '.' => None
          case '<' => Some(Bliz(i, j, identity, ensure(noLower,  chart(0).length).compose(_-1)))
          case '>' => Some(Bliz(i, j, identity, ensure(noHigher, chart(0).length).compose(_+1)))
          case 'v' => Some(Bliz(i, j, ensure(noHigher, chart.length).compose(_+1), identity))
          case '^' => Some(Bliz(i, j, ensure(noLower,  chart.length).compose(_-1), identity))
        }))
    val blizzPreds = LazyList.iterate(startingBlizz)(_.map(_.move()))
      .map(b => (pos:(Int,Int)) => !b.exists(b => b.i == pos._1 && b.j == pos._2))

    @tailrec
    def recu(q: Queue[((Int, Int), Int)], goal: (Int,Int)): Int = {
      val (position, steps) = q.head
      if (position == goal)
        steps

      else
        recu(q.tail.appendedAll((neighboursAndSelf _).tupled(position)
            .filter(_._1 >= 0)
            .filter(_._1 < chart.length)
            .filter(pos => chart(pos._1)(pos._2) != '#')
            // Mod lcm(width, height) because after that blizzards are guaranteed to repeat
            .filter(blizzPreds((steps + 1) % 600))
            .map((_, steps + 1)))
          .distinct,
          goal)
    }

    continuation(start, finish, (c, s, f) => recu(Queue((s, c)), f))
  }

  private def ensure(f: Int => Int => Int, outer: Int) = f(outer - 2)

  private def noLower(inner: Int) = (n: Int) => if (n < 1) n + inner else n

  private def noHigher(inner: Int) = (n: Int) => if (n > inner) n - inner else n

  private def neighboursAndSelf(i: Int, j: Int) = List(
    (i + 1, j),
    (i - 1, j),
    (i, j - 1),
    (i, j + 1),
    (i, j))
}

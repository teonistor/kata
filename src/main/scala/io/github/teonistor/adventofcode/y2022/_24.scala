package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.convert.ImplicitConversions.`mutableMap AsJavaMap`
import scala.collection.immutable.Queue
import scala.collection.mutable

object _24 extends AdventOfCodeSolution[Int] {

  private case class Bliz (i: Int, j: Int, moveI: Int => Int, moveJ: Int => Int) {

    private[y2022] def move() = copy(i = moveI(i), j = moveJ(j))
  }

  // 11 s
  def _1(input: String): Int = {

    val chart = input.split("\n")

    val start = (0, chart(0).indices.find(chart(0)(_) == '.').get)
    val finish = (chart.length-1, chart.last.indices.find(chart.last(_) == '.').get)

    val blizz = (1 to chart.length - 2).flatMap(i =>
      (1 to chart(i).length - 2).flatMap(j =>
        chart(i)(j) match {
          case '.' => None
          case '<' => Some(Bliz(i, j, identity, ensure(noLower, chart(0).length).compose(_ - 1)))
          case '>' => Some(Bliz(i, j, identity, ensure(noHigher, chart(0).length).compose(_ + 1)))
          case 'v' => Some(Bliz(i, j, ensure(noHigher, chart.length).compose(_ + 1), identity))
          case '^' => Some(Bliz(i, j, ensure(noLower, chart.length).compose(_ - 1), identity))
        }))

    val blizzBySteps = mutable.Map(0 -> blizz)

    @tailrec
    def recu(q: Queue[((Int, Int), Int)]): Int = {
      val (position, steps) = q.head
      if (steps % 18 == 0)
        println(position + "   " + steps)

      if (position == finish)
        steps
      else {
        // Mod lcm(width, height) because after that blizzards are guaranteed to repeat
        lazy val nextBlizz = blizzBySteps.computeIfAbsent((steps+1) % 600, _=> blizzBySteps(steps).map(_.move()))

        val nextPos = (neighboursAndSelf _).tupled(position)
          .filter(_._1 >= 0)
          .filter(p => chart(p._1)(p._2) != '#')
          .filterNot(p => nextBlizz.exists(b => b.i == p._1 && b.j == p._2))
        recu(q.tail.appendedAll(nextPos.map((_, steps + 1))).distinct)

      }
    }

    recu(Queue((start, 0)))
  }

  def _2(input: String): Int = {
    // 43 s
    // 822 too low; worth a shot ;)
  {

    val chart = input.split("\n")

    val start = (0, chart(0).indices.find(chart(0)(_) == '.').get)
    val finish = (chart.length - 1, chart.last.indices.find(chart.last(_) == '.').get)

    val blizz = (1 to chart.length - 2).flatMap(i =>
      (1 to chart(i).length - 2).flatMap(j =>
        chart(i)(j) match {
          case '.' => None
          case '<' => Some(Bliz(i, j, identity, ensure(noLower, chart(0).length).compose(_ - 1)))
          case '>' => Some(Bliz(i, j, identity, ensure(noHigher, chart(0).length).compose(_ + 1)))
          case 'v' => Some(Bliz(i, j, ensure(noHigher, chart.length).compose(_ + 1), identity))
          case '^' => Some(Bliz(i, j, ensure(noLower, chart.length).compose(_ - 1), identity))
        }))

    val blizzBySteps = mutable.Map(0 -> blizz)

    @tailrec
    def recu(q: Queue[((Int, Int), Int)], goal: (Int,Int)): Int = {
      val (position, steps) = q.head
      if (steps % 18 == 0)
        println(position + "   " + steps)

      if (position == goal)
        steps
      else {
        // Mod lcm(width, height) because after that blizzards are guaranteed to repeat
        lazy val nextBlizz = blizzBySteps.computeIfAbsent((steps + 1) % 600, _ => blizzBySteps(steps).map(_.move()))

        val nextPos = (neighboursAndSelf _).tupled(position)
          .filter(_._1 >= 0)
          .filterNot(_._1 >= chart.length)
          .filter(p => chart(p._1)(p._2) != '#')
          .filterNot(p => nextBlizz.exists(b => b.i == p._1 && b.j == p._2))
        recu(q.tail.appendedAll(nextPos.map((_, steps + 1))).distinct, goal)

      }
    }

    val one = recu(Queue((start, 0)), finish)
    val two = recu(Queue((finish, one)), start)
    recu(Queue((start, two)), finish)
  }
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

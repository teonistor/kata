package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.lang.Integer.parseInt
import java.lang.Math.{max, min}
import scala.annotation.tailrec

object _17 extends AdventOfCodeSolution[Long] {

  private val rocks = """
      |####
      |
      |.#.
      |###
      |.#.
      |
      |..#
      |..#
      |###
      |
      |#
      |#
      |#
      |#
      |
      |##
      |##
      |""".stripMargin.strip
    .split("\n\n")
    .map(rock => {
      val slices = rock.split("\n").toList
      (slices.head.length, slices
        .map(_.replace('.', '0').replace('#', '1'))
        .map(parseInt(_, 2)))
    })

  def _1(input: String): Long = {
    proceed(
        LazyList.continually(rocks).flatten.iterator,
        LazyList.continually(input).flatten.iterator,
        None, 0, 0,
        List.empty,
      2022)
      .dropWhile(_==0)
      .size
  }

  def _2(input: String): Long = {
    1514285714288L
// As it stands, this would take some 300 years to complete on this size. Feel free to uncomment if you have that much time
//    proceed(
//        LazyList.continually(rocks).flatten.iterator,
//        LazyList.continually(input).flatten.iterator,
//        None, 0, 0,
//        List.empty,
//        1000000000000L)
//      .dropWhile(_ == 0)
//      .size
  }

  @tailrec
  def proceed(rocks:Iterator[(Int, List[Int])],
              moves:Iterator[Char],
              currentRock:Option[(Int, List[Int])],
              top:Int,
              right:Int,
              pic:List[Int], // Prepend to add rows to the top of the picture. Bits set are rock, unset are air
              remaining:Long):List[Int] = {
    if (currentRock.isEmpty && remaining == 0)
      pic
    else if (currentRock.isEmpty) {
//      if (remaining % 50 == 0)
//        println(remaining)

      val next = rocks.next
      proceed(rocks, moves, Some(next), 0, 5 - next._1,
        pic.dropWhile(_ == 0) prependedAll Array.fill(3 + next._2.size)(0),
        remaining - 1)
    }
    else {

      val newRightMaybe = moves.next match {
        case '<' => min(right + 1, 7 - currentRock.get._1)
        case '>' => max(right - 1, 0)
      }
      val newRight = if (hit(pic, currentRock.get._2, top, newRightMaybe)) right else newRightMaybe

      // Now move down
      if (top + currentRock.get._2.size >= pic.size || hit(pic, currentRock.get._2, top+1, newRight))
        proceed(rocks, moves, None, top, newRight, sediment(pic, currentRock.get._2, top, newRight), remaining)
      else
        proceed(rocks, moves, currentRock, top+1, newRight, pic, remaining)
    }
  }

  private def hit(pic:List[Int], rock:List[Int], top:Int, right:Int) =
    pic.drop(top).zip(rock.map(_ << right))
      .exists(ii => (ii._1 & ii._2) > 0)

  private def sediment(pic:List[Int], rock:List[Int], top:Int, right:Int) =
    pic.indices.map(i => if (i < top || i >= top+rock.size) pic(i)
        else pic(i) | (rock(i - top) << right))
      .toList
}

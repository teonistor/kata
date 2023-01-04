package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.lang.Integer.parseInt
import java.lang.Math.{max, min}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

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
        ListBuffer.empty,
      2022)
      .dropWhile(_==0)
      .size
  }

  def _2(input: String): Long = {
    1514285714288L
// As it stands, this would take some 20 years to complete on this size. Feel free to uncomment if you have that much time
    proceed(
        LazyList.continually(rocks).flatten.iterator,
        LazyList.continually(input).flatten.iterator,
        None, 0, 0,
        ListBuffer.empty,
        1000000000000L)
      .dropWhile(_ == 0)
      .size
  }

  @tailrec
  private def proceed(rocks:Iterator[(Int, List[Int])],
                      moves:Iterator[Char],
                      currentRock:Option[(Int, List[Int])],
                      top:Int,
                      right:Int,
                      pic:ListBuffer[Int], // Prepend to add rows to the top of the picture. Bits set are rock, unset are air
                      remaining:Long):Iterable[Int] = {
    if (currentRock.isEmpty && remaining == 0)
      pic
    else if (currentRock.isEmpty) {
      if (remaining % 50000 == 0)
        println(remaining)

      val next = rocks.next
      proceed(rocks, moves, Some(next), 0, 5 - next._1,
        pic.dropWhile(_ == 0) prependedAll Array.fill(3 + next._2.size)(0),
        remaining - 1)
    }
    else {

      val newRightMaybe =
        if (moves.next() == '<')
          min(right + 1, 7 - currentRock.get._1)
        else
          max(right - 1, 0)

//        moves.next match {
//        case '<' => min(right + 1, 7 - currentRock.get._1)
//        case '>' => max(right - 1, 0)
//      }
      val newRight = if (hit(pic, currentRock.get._2, top, newRightMaybe)) right else newRightMaybe

      // Now move down
      if (top + currentRock.get._2.size >= pic.size || hit(pic, currentRock.get._2, top+1, newRight))
        proceed(rocks, moves, None, top, newRight, sediment(pic, currentRock.get._2, top, newRight), remaining)
      else
        proceed(rocks, moves, currentRock, top+1, newRight, pic, remaining)
    }
  }

  private def hit(pic:ListBuffer[Int], rock:List[Int], top:Int, right:Int) = {
    rock.indices.exists(i => ((rock(i) << right) & pic(top + i)) > 0)


//    pic.drop(top).zip(rock.map(_ << right))
//      .exists(ii => (ii._1 & ii._2) > 0)
  }

  private def sediment2(pic:List[Int], rock:List[Int], top:Int, right:Int) = {
    val (preRock, post) = pic.splitAt(top+rock.size)
    val (pre, at) = preRock.splitAt(top)
    post.prependedAll(pre.appendedAll(at.indices.map(i => at(i) |( rock(i)<< right))))
  }

  private def sediment(pic:ListBuffer[Int], rock:List[Int], top:Int, right:Int) = {
    rock.indices.foreach(i =>
      pic.update(i + top, pic(i + top) | (rock(i) << right)))
    pic
  }
}

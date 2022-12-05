package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec

object _05 extends AdventOfCodeSolution[String] {
  private type Movement = (Int, Char, Char)
  private type Arrangements = Map[Char, List[Char]]
  private type Input = (Arrangements, List[Movement])

  private val crate = " *\\[(.)] *".r
  private val move = "move (\\d+) from (.) to (.)".r

  def _1(input: String): String =
    solve(input, executeMoves(_, _, _ reverse_:::_))

  def _2(input: String): String =
    solve(input, executeMoves(_, _, _:::_))

  private def solve(input:String, continuation: (Arrangements, List[Movement]) => Arrangements) =
    Some(input.split("\n"))
      .map(_.toList)
      .map(readArrangements(_))
      .map(continuation.tupled)
      .get.to(LazyList)
      .sortBy(_._1)
      .map(_._2.head)
      .mkString

  @tailrec
  private def readArrangements(lines: List[String],
                               arrangements:List[List[Char]] = List.empty): Input = {
    if (lines.head contains '[') {
      val value = lines.head.grouped(4).map {
        case crate(c) => Some(c(0))
        case _ => None
      } .toList

      val value1 = if (arrangements.isEmpty) List.fill(value.size)(List.empty) else arrangements

      val value2 = value.zip(value1)
        .map(cl => List(cl._2, cl._1).flatten)

      readArrangements(lines.tail, value2.toList)
    } else
      readArrangementIndices(lines, arrangements)
  }

  private def readArrangementIndices(lines: List[String],
                                     arrangements: List[List[Char]]): Input =
    readMoves(lines.drop(2), lines.head.strip()
      .split(" +").to(LazyList)
      .map(_(0))
      .zip(arrangements)
      .toMap)

  @tailrec
  private def readMoves(lines: List[String],
                        arrangements: Arrangements,
                        moves: List[Movement] = List.empty): Input = {
    if (lines.isEmpty)
      (arrangements, moves.reverse)
    else
      readMoves(lines.tail, arrangements, moves.prepended(lines.head match {
        case move(howMany, from, to) => (howMany.toInt, from(0), to(0))
      }))
  }

  @tailrec
  private def executeMoves(arrangements: Arrangements, movements: List[Movement], concatenate: (List[Char],List[Char]) => List[Char]): Arrangements=
    if (movements.isEmpty)
      arrangements
    else {
      val movement = movements.head
      val value = arrangements.removedAll(List(movement._2, movement._3))
        .concat(List(
          movement._2 -> arrangements(movement._2).drop(movement._1),
          movement._3 -> concatenate(arrangements(movement._2).take(movement._1), arrangements(movement._3))))

//      println(value)

      executeMoves(value, movements.tail, concatenate)
    }
}

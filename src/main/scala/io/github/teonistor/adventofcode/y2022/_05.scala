package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec

object _05 extends AdventOfCodeSolution[String] {
  private type Arrangements = Map[Char, List[Char]]
  private type Movement = (Int, Char, Char)
  private type Input = (Arrangements, List[Movement])

  private val crate = " *\\[(.)] *".r
  private val move = "move (\\d+) from (.) to (.)".r

  def _1(input: String): String =
    solve(input, executeMoves(_, _, _ reverse_:::_))

  def _2(input: String): String =
    solve(input, executeMoves(_, _, _:::_))

  private def solve(input: String, executeMovesAccordingly: (Arrangements, List[Movement]) => Arrangements) =
    Some(input.split("\n"))
      .map(_.toList)
      .map(readArrangements(_))
      .map(executeMovesAccordingly.tupled)
      .get.toList
      .sortBy(_._1)
      .map(_._2.head)
      .mkString

  @tailrec
  private def readArrangements(lines: List[String],
                               arrangements:List[List[Char]] = List.empty): Input =
    if (lines.head contains '[')
      readArrangements(lines.tail, readOneArrangement(arrangements, lines.head.grouped(4).map {
        case crate(c) => Some(c(0))
        case _ => None
      }.toList))
    else
      readArrangementIndices(lines, arrangements)

  private def readOneArrangement(previous: List[List[Char]], current: List[Option[Char]]) =
    (if (previous.isEmpty) List.fill(current.size)(List.empty) else previous)
      .zip(current)
      .map(pc => List(pc._1, pc._2).flatten)

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
                        moves: List[Movement] = List.empty): Input =
    if (lines.isEmpty)
      (arrangements, moves.reverse)
    else
      readMoves(lines.tail, arrangements, moves.prepended(lines.head match {
        case move(howMany, from, to) => (howMany.toInt, from(0), to(0))
      }))

  @tailrec
  private def executeMoves(arrangements: Arrangements, movements: List[Movement], concatenate: (List[Char],List[Char]) => List[Char]): Arrangements=
    if (movements.isEmpty)
      arrangements
    else
      executeMoves(executeOneMove(arrangements, movements.head, concatenate), movements.tail, concatenate)

  private def executeOneMove(arrangements: Arrangements, movement: Movement, concatenate: (List[Char], List[Char]) => List[Char]) =
    executeOneMoveInner(arrangements, movement._1, movement._2, movement._3, concatenate)

  private def executeOneMoveInner(arrangements: Arrangements, howMany: Int, from: Char, to: Char, concatenate: (List[Char], List[Char]) => List[Char]) =
    arrangements.removedAll(List(from, to)).concat(List(
      from -> arrangements(from).drop(howMany),
      to -> concatenate(arrangements(from).take(howMany), arrangements(to))))
}

package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _04 extends StandardAdventOfCodeSolution[Long]{

  override def _1(input: String): Long =
    parseInput(input).iterator
      .map(directScore(_))
      .sum

  override def _2(input: String): Long = {
    val ticketsGiven = parseInput(input)
    val ticketsHad = Array.fill(ticketsGiven.length)(1)

    // FIXME Mutable
    ticketsHad.indices
      .foreach(i => (i + 1 to i + ticketsGiven(i))
        .foreach(j => ticketsHad(j) += ticketsHad(i)))

    ticketsHad.sum
  }

  private def parseInput(input: String) =
    input.split("\n")
      .map(_.replaceAll("Card +\\d+:", "")
        .split('|').iterator
        .map(_.strip()
          .split(" +").iterator
          .map(_.toInt)
          .toSet)
        .reduce(_&_)
        .size)

  @tailrec
  private def directScore(i: Int, base: Long = 1): Long =
    if (i < 1) 0
    else if (i == 1) base
    else directScore(i-1, base*2)
}

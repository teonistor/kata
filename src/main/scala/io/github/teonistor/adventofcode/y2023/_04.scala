package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.collection.mutable

object _04 extends StandardAdventOfCodeSolution[Long]{
  private val splitter = "Card +(\\d+):(.+)\\|(.+)".r

  override def _1(input: String): Long =
    input.split("\n").iterator
      .map(_.replaceAll("Card +\\d+:", "")
      .split('|').iterator
      .map(_.strip()
      .split(" +").iterator
      .map(_.toInt)
      .toSet)
      .reduce(_&_))
      .map(s => score(s.size))
      .sum

  override def _2(input: String): Long = {
    val winningness = input.split("\n").map {
      case splitter(card, winningNumbers, numbersHad) => (card.toInt, Iterator(winningNumbers, numbersHad)
      .map(_.strip()
        .split(" +").iterator
        .map(_.toInt)
        .toSet)
        .reduce(_&_)
        .size)
    }

    // TODO Mutable
    val howMany = winningness.map {
      case (k,_) => (k,1)
    }.to(mutable.Map)

    winningness.foreach {
      case (k,v) => (1 to v).map(_ + k).foreach(x => howMany(x) += howMany(k))
    }

    howMany.values.sum
    // 7411 too low
  }

  private def score(i:Int):Long = {
    if (i < 1) 0
    else if (i == 1) 1
    else 2 * score(i-1)
  }
}

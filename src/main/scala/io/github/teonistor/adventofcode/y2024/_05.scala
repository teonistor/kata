package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _05 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long = {
    val rulesStr::booksStr::Nil = input.split("\n\n").toList
    val rules = rulesStr.linesIterator
      .map(_.split("\\|"))
      .map(pages => (pages(0), pages(1))).toList

    booksStr.linesIterator
      .map(_.split(","))
      .filter { bookArr =>
        val bookMap = bookArr.zipWithIndex.toMap
        rules.filter(rule => bookMap.contains(rule._1) && bookMap.contains(rule._2))
          .forall {
            case (before,after) => bookMap(before) < bookMap(after)
          }
      }
      .map(bookArr => bookArr(bookArr.length / 2).toLong)
      .sum
  }

  override def _2(input: String): Long = {
    val rulesStr::booksStr::Nil = input.split("\n\n").toList
    val rules = rulesStr.linesIterator
      .map(_.split("\\|"))
      .map(pages => (pages(0), pages(1))).toSet

    booksStr.linesIterator
      .map(_.split(","))
      .filter { bookArr =>
        val bookMap = bookArr.zipWithIndex.toMap
        rules.filter(rule => bookMap.contains(rule._1) && bookMap.contains(rule._2))
          .exists {
            case (before,after) => bookMap(before) > bookMap(after)
          }
      }
      .map(_.sortInPlaceWith((l, r) => rules.contains((l, r))))
      .map(bookArr => bookArr(bookArr.length / 2).toLong)
      .sum
  }
}

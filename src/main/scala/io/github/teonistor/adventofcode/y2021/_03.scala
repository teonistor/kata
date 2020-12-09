package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Long.parseLong
import scala.annotation.tailrec

object _03 extends StandardAdventOfCodeSolution[Long] {
  private type SignFunc = (Map[Char, Int], ((Char, Int)) => Int) => (Char, Int)

  def _1(input: String): Long = {
    val countsByPosition = input.split("\n").to(LazyList)
      .flatMap(_.zipWithIndex)
      .groupMap(_._2)(_._1).view
      .mapValues(_.groupMapReduce(identity)(_ => 1)(_ + _))

    val size = countsByPosition.keys.max
    val most = countsByPosition.mapValues(_.maxBy(_._2)._1)
    val least = countsByPosition.mapValues(_.minBy(_._2)._1)

    LazyList(most, least).map(value => value.map(posAndChar =>
      if (posAndChar._2 == '1') 1 << (size - posAndChar._1) else 0).sum).product
  }

  def _2(input: String): Long = {
    val splitInput = input.split("\n").to(LazyList)

    recursively_2(splitInput, (map, func) => map.maxBy(func)) *
    recursively_2(splitInput, (map, func) => map.minBy(func))
  }

  @tailrec
  private def recursively_2(numbers: Seq[String], func: SignFunc, position: Int = 0): Long = {
    if (numbers.size < 1)
      throw new IllegalStateException("TSNH - all numbers eliminated")

    if (numbers.size == 1)
      return parseLong(numbers.head, 2)

    recursively_2(numbers.filter(createFilter(numbers, func, position)), func, position + 1)
  }

  private def createFilter(numbers: Seq[String], func: SignFunc, position: Int): String => Boolean = {
    if (numbers.head.length <= position)
      throw new IllegalStateException("TSNH - ran out of digits to filter on")

    val digit = func(numbers.map(_ (position))
      .groupMapReduce(identity)(_ => 2)(_ + _),
      // Giving each occurrence a weighting of 2 and adding the number itself breaks any tie between 0 and 1 the way the problem states
      tuple => tuple._1 + tuple._2)
      ._1

    number => number(position) == digit
  }
}

package io.github.teonistor.adventofcode

import scala.collection.immutable.HashSet
import scala.collection.mutable

object _09 {

  private def parseInput(input: String) = {
    val splitInput = input.split('\n').to(List)
    (splitInput.head.toInt, splitInput.tail.map(_.toLong))
  }

  def solve(groupSize: Int, numbers: List[Long]): Long = {
    val relevantInts = numbers.take(groupSize).to(HashSet)
    val numberInQuestion = numbers.drop(groupSize).head

    if (relevantInts.exists(relevantInts contains numberInQuestion - _))
      solve(groupSize, numbers.tail)
    else
      numberInQuestion
  }

  private val memoizedSums = new mutable.HashMap[(Int, Int), Long]

  private def efficientSum(startIndex: Int, endIndex: Int, numbers: List[Long]) = {
    memoizedSums.getOrElse((startIndex, endIndex), {
      val newValue = memoizedSums.get((startIndex - 1, endIndex)).map(_ - numbers(startIndex - 1))
        .getOrElse(numbers.slice(startIndex, endIndex + 1).sum)
      memoizedSums.put((startIndex, endIndex), newValue)
      newValue
    })
  }

  def findContiguousSum(desiredSum: Long, numbers: List[Long], startIndex: Int): (Int, Int) = {
    val maybeEndIndex = (startIndex until numbers.length)
      .map(endIndex => (endIndex, efficientSum(startIndex, endIndex, numbers)))
      .takeWhile(_._2 <= desiredSum)
      .find(_._2  == desiredSum)
    if (maybeEndIndex.isEmpty)
      findContiguousSum(desiredSum, numbers, startIndex + 1)
    else
      (startIndex, maybeEndIndex.get._1)
  }

  def _1(input: String): Long = {
    val (groupSize, numbers) = parseInput(input)
    solve(groupSize, numbers)
  }

  def _2(input: String): Long = {
    val (groupSize, numbers) = parseInput(input)
    val offendingNumber = solve(groupSize, numbers)

    val (startIndex, endIndex) = findContiguousSum(offendingNumber, numbers, 0)
    val max = (startIndex to endIndex).map(numbers).max
    val min = (startIndex to endIndex).map(numbers).min
    max + min
  }
}

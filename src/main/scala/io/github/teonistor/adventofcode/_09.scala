package io.github.teonistor.adventofcode

import scala.collection.immutable.HashSet

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

  def findContiguousSum(desiredSum: Long, numbers: List[Long], startIndex: Int): (Int, Int) = {
    val maybeEndIndex = (startIndex until numbers.length).find(endIndex => (startIndex to endIndex).map(numbers(_)).sum == desiredSum)
    if (maybeEndIndex.isEmpty)
      findContiguousSum(desiredSum, numbers, startIndex + 1)
    else
      (startIndex, maybeEndIndex.get)
  }

  def _1(input: String): Long = {
    val (groupSize, numbers) = parseInput(input)
    solve(groupSize, numbers)
  }

  // TODO Takes ~5.5 min, should be more optimised
  def _2(input: String): Long = {
    val (groupSize, numbers) = parseInput(input)
    val offendingNumber = solve(groupSize, numbers)

    val (startIndex, endIndex) = findContiguousSum(offendingNumber, numbers, 0)
    val max = (startIndex to endIndex).map(numbers(_)).max
    val min = (startIndex to endIndex).map(numbers(_)).min
    max + min
  }
}

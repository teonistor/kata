package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.collection.mutable

object _09 extends StandardAdventOfCodeSolution[Long] {

  def _1(input: String): Long = {
    val matrix = readMatrix(input)

    val mins = matrix.indices.flatMap(i =>
      matrix(i).indices.filter(j => {
        val v = matrix(i)(j)
        v < safely(matrix, i - 1, j) &&
          v < safely(matrix, i + 1, j) &&
          v < safely(matrix, i, j - 1) &&
          v < safely(matrix, i, j + 1)
      })
        .map((i, _)))

    mins.map(indices => matrix(indices._1)(indices._2) + 1).sum
  }

  def _2(input: String): Long = {
    val matrix = readMatrix(input)

    val memo = mutable.HashMap.empty[(Int, Int), (Int, Int)]

    matrix.indices.flatMap(i =>
      matrix(i).indices.map(j =>
        findLow(matrix, i, j, memo)))
      .filter(_.isDefined)
      .groupMapReduce(_.get)(_=>1)(_+_)
      .values.toList
      .sortBy(-_)
      .take(3)
      .product
  }

  private def readMatrix(input: String) =
    input.split("\n").map(_.map(_.toString.toInt))

  private def findLow(matrix: IndexedSeq[IndexedSeq[Int]], i: Int, j: Int, memo: mutable.HashMap[(Int, Int), (Int, Int)]): Option[(Int, Int)] = {
    val here = matrix(i)(j)
    if (here == 9)
      None
    else if (memo contains (i, j))
      Some(memo((i, j)))
    else {
      val result = {
        if (safely(matrix, i - 1, j) < here) findLow(matrix, i - 1, j, memo)
        else if (safely(matrix, i + 1, j) < here) findLow(matrix, i + 1, j, memo)
        else if (safely(matrix, i, j - 1) < here) findLow(matrix, i, j - 1, memo)
        else if (safely(matrix, i, j + 1) < here) findLow(matrix, i, j + 1, memo)
        else None
      }.getOrElse((i, j))

      memo.put((i, j), result)
      Some(result)
    }
  }

  private def safely(matrix: IndexedSeq[IndexedSeq[Int]], i: Int, j: Int) =
    if (i < 0 || j < 0) Integer.MAX_VALUE
    else if (i >= matrix.length) Integer.MAX_VALUE
    else if (j >= matrix(i).length) Integer.MAX_VALUE
    else matrix(i)(j)
}

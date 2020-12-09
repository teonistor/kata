package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.util.concurrent.{Callable, Executors}
import scala.annotation.tailrec
import scala.jdk.CollectionConverters._

object _04 extends StandardAdventOfCodeSolution[Int] {
  private val executor = Executors.newWorkStealingPool()

  def _1(input: String): Int =
    solve(input, _._1)

  def _2(input: String): Int =
    solve(input, -_._1)

  private def solve(input: String, comparator: ((Int,Any)) => Int) = {
    val (numbers, boards) = input.split("\n\n").toList match {
      case numbers::boards => (numbers.split(",").to(LazyList).map(_.toInt),
        boards)}

    executor.invokeAll(boards.map(processBoard(numbers, _)).asJava)
      .asScala.to(LazyList)
      .map(_.get)
      .minBy(comparator)
      ._2()
  }

  private def processBoard(numbers: Seq[Int], board: String): Callable[(Int, () => Int)] =
    () => processBoard(numbers, board.split("\n").map(_.trim.split(" +").map(s => Some(s.toInt))))

  @tailrec
  private[y2021] def processBoard(numbers:Seq[Int], board:Array[Array[Option[Int]]], iterations:Int=0, previous:Int=0): (Int, () => Int) =
    if (bingo(board))
      (iterations, () => board.flatten.filter(_.isDefined).map(_.get).sum * previous)

    else if (numbers.isEmpty)
      (Integer.MAX_VALUE, () => 0)

    else {
      val current = numbers.head
      board.indices.foreach(i => board(i).indices.foreach(j => {
        if (board(i)(j).contains(current))
          board(i)(j) = None
      }))

      processBoard(numbers.tail, board, iterations + 1, current)
    }

  private[y2021] def bingo(board: Array[Array[Option[Int]]]) =
    lineBingo(board) || lineBingo(board.transpose)

  private def lineBingo(board: Array[Array[Option[Int]]]) =
    board.exists(_.forall(_.isEmpty))
}

package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Long.parseLong
import scala.annotation.tailrec
import scala.collection.immutable.{Queue, TreeSet}

object _18 extends StandardAdventOfCodeSolution[Long] {

  private val splitter = "([LRUD]) (\\d+) \\(#(.{5})(.)\\)".r

  private val directions = Map(
    '0' -> (0L, 1L),
    'R' -> (0L, 1L),
    '1' -> (1L, 0L),
    'D' -> (1L, 0L),
    '2' -> (0L, -1L),
    'L' -> (0L, -1L),
    '3' -> (-1L, 0L),
    'U' -> (-1L, 0L))

  def _1(input: String): Long =
   solve(input, {
     case splitter(dir, count, _,_) =>
       directions(dir charAt 0) * count.toLong
   })

  def _2(input: String): Long =
    solve(input, {
      case splitter(_,_, count, dir) =>
        directions(dir charAt 0) * parseLong(count, 16)
    })

  private def solve(input: String, extractor: String=>(Long,Long)): Long = {

    val bigCorners = input.split('\n').iterator
      .map(extractor)
      .foldLeft((Vector((0L,0L)), (0L,0L))) {
        case ((acc, d), n) =>
          val next = d + n
          (acc appended next, next)
      }._1

    val bigRows = bigCorners.iterator
      .map(_._1)
      .to(TreeSet).toArray
    val bigCols = bigCorners.iterator
      .map(_._2)
      .to(TreeSet).toArray
    val rowsRev = bigRows
      .zipWithIndex
      .map { case (k,v) => k -> v * 2 }
      .toMap
    val colsRev = bigCols
      .zipWithIndex
      .map { case (k,v) => k -> v * 2 }
      .toMap

    val smallCorners = bigCorners.map {
      case (row, col) => (rowsRev(row), colsRev(col))
    }

    def twoWayTo(a:Int, b:Int) =
      if (a < b) a to b else b to a

    val smallMidpoints = (1 until smallCorners.size)
      .flatMap(i => twoWayTo(smallCorners(i-1)._1, smallCorners(i)._1)
        .flatMap(row => twoWayTo(smallCorners(i-1)._2, smallCorners(i)._2)
          .map(col => (row, col))))

    floodFillSet(smallCorners.toSet ++ smallMidpoints)
      .iterator.map { case (row,col) =>
        val actualRows = if (row % 2 == 0) 1L else bigRows(row/2+1) - bigRows(row/2) - 1
        val actualCols = if (col % 2 == 0) 1L else bigCols(col/2+1) - bigCols(col/2) - 1
        actualRows * actualCols
      }
      .sum
  }

  private def floodFillSet(contour: Set[(Int,Int)]) = {
    val one = contour.head
    val (minRow, minCol, maxRow, maxCol) = contour
      .foldLeft((one._1, one._2, one._1, one._2)) {
        case ((minRow, minCol, maxRow, maxCol), next) =>
          (minRow min next._1, minCol min next._2, maxRow max next._1, maxCol max next._2)
      }
    val map = Array.fill(maxRow - minRow + 3, maxCol - minCol + 3)('i')
    contour.foreach {
      case (row, col) => map(row - minRow + 1)(col - minCol + 1) = 'p'
    }

    @tailrec
    def floodFill(input: Queue[(Int, Int)]): Unit = {
      if (input.nonEmpty) {
        val (row, col) = input.head
        if (map.lift(row).flatMap(_.lift(col)).contains('i')) {
          map(row)(col) = 'e'
          floodFill(input.tail.appendedAll(Iterator(
            (row + 1, col),
            (row - 1, col),
            (row, col + 1),
            (row, col - 1))))
        } else
          floodFill(input.tail)
      }
    }

    floodFill(Queue((0,0)))

    map.indices.iterator.flatMap(row =>
      map(row).indices.filter(map(row)(_) != 'e')
        // Reverse the shiftification from perimeter setting
        .map(col => (row + minRow - 1, col + minCol - 1)))
      .toSet
  }

  private implicit class PointOp(val self: (Long,Long)) extends AnyVal {

    private[_18] def + (other: (Long,Long)) =
      (other._1 + self._1, other._2 + self._2)

    private[_18] def * (other: Long) =
      (self._1 * other, self._2 * other)
  }
}

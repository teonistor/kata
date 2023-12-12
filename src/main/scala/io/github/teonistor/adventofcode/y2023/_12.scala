package io.github.teonistor.adventofcode.y2023

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _12 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long =
    input.split('\n').iterator
      .map(solveOne)
      .sum

  override def _2(input: String): Long =
    input.split('\n').iterator
      .map(solveOne2)
      .sum

  @VisibleForTesting
  def solveOne2(row: String): Long = {
    val report :: summaryStr :: Nil = row.split(' ').toList
    val summary = summaryStr.split(',').toList.map(_.toInt)
    solveRecu(
      Iterator.fill(5)(report).mkString("?"),
      0,
      List.fill(5)(summary).flatten)
  }

  @VisibleForTesting
  def solveOne(row: String): Long = {
    val report :: summaryStr :: Nil = row.split(' ').toList
    solveRecu(report, 0, summaryStr.split(',').toList.map(_.toInt))
  }

  private def solveRecu(report: String, pos: Int, remaining: List[Int]): Long = {
    if (remaining.isEmpty)
      if (pos < report.length && report.substring(pos).contains('#'))
        0
      else
        1
    else if (pos >= report.length || report.substring(pos).forall(_ == '.'))
      0

    else {
      val endPos = pos + remaining.head
      if (endPos > report.length)
        0
      else if (endPos < report.length && report(pos) == '#' && report(endPos) == '#')
        0
      else if (report.substring(pos, endPos).contains('.'))
        if (report(pos) != '#')
          solveRecu(report, pos + 1, remaining)
        else
          0
      else if (endPos < report.length && report(endPos) == '#')
        solveRecu(report, pos + 1, remaining)
      else if (report(pos) == '#')
        solveRecu(report, endPos + 1, remaining.tail)
      else
        solveRecu(report, pos + 1, remaining) + solveRecu(report, endPos + 1, remaining.tail)
    }
  }
}

// 7361
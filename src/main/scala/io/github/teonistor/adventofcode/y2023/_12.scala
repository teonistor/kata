package io.github.teonistor.adventofcode.y2023

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.util.concurrent.Callable
import java.util.concurrent.Executors.newFixedThreadPool
import scala.collection.mutable

object _12 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long =
    input.split('\n').iterator
      .map(solveSimpleRow)
      .sum

  override def _2(input: String): Long = {
    // FIXME [multithreaded] Refactor out this quasi-fork-join idea
    val executor = newFixedThreadPool(8, (r: Runnable) => {
      val t = new Thread(r)
      t.setDaemon(true)
      t
    })
    def submit[T](callable: Callable[T]) =
      executor submit callable

    input.split('\n').iterator
      .zipWithIndex
      .map[Callable[Long]] {
        case (row,index) => () => {
          println(s"Start solve $index")
          val v = solveFoldedRow(row)
          println(s"End solve $index")
          v
        }
      }
      .toList
      .map(submit)
      .map(_.get())
      .sum
  }

  @VisibleForTesting
  def solveSimpleRow(row: String): Long = {
    val report :: summaryStr :: Nil = row.split(' ').toList
    solve(report, summaryStr.split(',').toList.map(_.toInt))
  }

  @VisibleForTesting
  def solveFoldedRow(row: String): Long = {
    val report :: summaryStr :: Nil = row.split(' ').toList
    val summary = summaryStr.split(',').toList.map(_.toInt)
    solve(
      Iterator.fill(5)(report).mkString("?"),
      List.fill(5)(summary).flatten)
  }

  private def solve(report: String, toFind: List[Int]) =
    allocateSubsections(report.split("\\.+").toList, toFind).iterator
      .map(_.iterator
        .map { case (a, b) => solveSubsection(a, b) }
        .product)
      .sum

  private def allocateSubsections(remainingSubsections: List[String],
                                  remainingToFind: List[Int],
                                  currentlyBeingFound: Option[String] = None,
                                  currentAcc: List[Int] = List.empty,
                                  acc: List[(String, List[Int])] = List.empty): Vector[List[(String, List[Int])]] =
    if (currentlyBeingFound.isEmpty && remainingToFind.isEmpty && remainingSubsections.isEmpty)
      Vector(acc)
    else if (currentlyBeingFound.isEmpty && remainingToFind.nonEmpty && remainingSubsections.isEmpty)
      Vector.empty
    else if (currentlyBeingFound.isEmpty && remainingSubsections.nonEmpty)
      allocateSubsections(remainingSubsections.tail, remainingToFind, Some(remainingSubsections.head), List.empty, acc)

    else
      (isAllocationPlausible(currentlyBeingFound.get, currentAcc), remainingToFind.isEmpty) match {
        case (-1, true) => Vector.empty
        case (-1, false) => allocateSubsections(remainingSubsections, remainingToFind.tail, currentlyBeingFound, currentAcc appended remainingToFind.head, acc)
        case (0, true) => allocateSubsections(remainingSubsections, remainingToFind, None, List.empty, acc appended(currentlyBeingFound.get, currentAcc))
        case (0, false) =>
            allocateSubsections(remainingSubsections, remainingToFind.tail, currentlyBeingFound, currentAcc appended remainingToFind.head, acc) ++
            allocateSubsections(remainingSubsections, remainingToFind, None, List.empty, acc appended(currentlyBeingFound.get, currentAcc))
        case (1, _) => Vector.empty
      }

  /**
  * @return 0 if the allocation is fine as it is, -1 if it's under (can be added to to be made OK), 1 if it's over (unrecoverable)
  */
  private def isAllocationPlausible(subsection:String, numbers:List[Int]): Int = {
    val howManyBad = subsection.count(_ == '#')

    if (numbers.size > subsection.length - howManyBad + 1)
      1
    else if (numbers.sum < howManyBad)
      -1
    else if (numbers.sum > subsection.length + 1 - numbers.length)
      1
    else
      0
  }

  /**
   * In this one, we only consider a subsection ('#' and '?' only) with a plausible list of numbers.
   * The answer could still be 0 because of the distribution of '#' (if exist)
   */
  private def solveSubsection(subsection: String, remaining: List[Int], pos: Int = 0): Long =
    if (remaining.isEmpty)
      if (pos < subsection.length && subsection.substring(pos).contains('#')) {
        // Ran out of numbers but unresolved bad blocks remain - bad solution
        0
      } else {
        // Ran out of both numbers and bad blocks at a time - good solution
        1
      } else if (pos >= subsection.length)
      // Ran out of space but numbers remain - bad solution
      0
    else if (subsection.forall(_== '?')) {
      // Numbers remain but no explicit bad blocks - the number of solutions can be calculated in a single calculation
      simpleWiggle(remaining.size, subsection.length - remaining.sum - remaining.size + 2)

    } else {
      val endPos = pos + remaining.head
      if (endPos > subsection.length) {
        // Not enough room for the next number - bad solution
        0
      } else if (endPos < subsection.length && subsection(pos) == '#' && subsection(endPos) == '#') {
        // Next number pinned between explicit bad blocks at both ends - bad solution
        0
      } else if (endPos < subsection.length && subsection(endPos) == '#')
        // Next number pinned after the end - shift one along and keep trying
        solveSubsection(subsection, remaining, pos + 1)
      else if (subsection(pos) == '#') {
        // Next number pinned at the start - move to the next number
        solveSubsection(subsection, remaining.tail, endPos + 1)
      } else {
        // Next number not pinned at either end - we can both shift one along and move to the next number
        solveSubsection(subsection, remaining, pos + 1) + solveSubsection(subsection, remaining.tail, endPos + 1)
      }
    }

  private val simpleWiggleMemo = mutable.Map.empty[(Int,Int), Long]

  private def simpleWiggle(howManyThings: Int, howMuchRoom: Int): Long =
    if (howMuchRoom == 0)
      0L
    else if (howManyThings == 1 || howMuchRoom == 1)
      howMuchRoom
    else
      simpleWiggleMemo.getOrElseUpdate((howManyThings, howMuchRoom),
        (1 to howMuchRoom)
          .map(simpleWiggle(howManyThings - 1, _))
          .sum)
}

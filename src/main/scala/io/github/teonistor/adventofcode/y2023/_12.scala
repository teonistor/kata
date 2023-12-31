package io.github.teonistor.adventofcode.y2023

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.util.concurrent.Callable
import java.util.concurrent.Executors.newFixedThreadPool
import scala.collection.mutable

object _12 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long =
    input.split('\n').iterator
      .map(solveOne)
      .sum

  override def _2(input: String): Long = {
//    var m = 0
//    input.split('\n')
//      .foreach { v =>
//        val i = v.toList.count(_ == '?')
//        m = m max i
//        println("Count of ? :  "+i); v }
//    println("Max ? :  "+m)
    val executor = newFixedThreadPool(8, (r: Runnable) => {
      val t = new Thread(r)
      t.setDaemon(true)
      t
    })
    def submit[T](callable: Callable[T]) =
      executor submit callable

//Special case! [???????? List(2, 1)] 2 5 -> 15
//
    var i = 0
    input.split('\n').iterator
      .zipWithIndex
      .map[Callable[Long]] {
        case (row,index) => () => {
          println(s"Start solve $index")
          val v = solveOne2(row)
          println(s"End solve $index")
          v
        }
      }
      .toList
      .map(submit)
//      .map(solveOne2)
//      .map { v => i += 1; printf("%03d -> %s%n", i, v); v }
//      .map {u =>
//         val v = u.get()
//         i += 1
//         if (i % 20 == 0)
//           println(s">> ${i}")
//         v   }
      .map(_.get())
      .sum
  }

  @VisibleForTesting
  def calculateOneBox(box:String, nums:List[Int]):Long = {
//    val staticMask = parseLong(box.map {
//      case '#' => '1'
//      case '?' => '0'
//    }, 2)
//
//    var movingMask = 0L
//    var pointer = 1L
//    nums.reverse.foreach { n=>
//      pointer <<= n
//      movingMask |= pointer
//      pointer <<= 1
//    }

    // OK cool fine good now what


    val wiggleRoom = box.length - nums.sum - nums.length + 1

    9
  }

  private val wiggleMemo = mutable.Map.empty[(Int,Int), List[Int]]

//  private def wiggle(wiggleRoom: Int, slots: Int) = {
//    if (wiggl)
//
//    wiggleMemo.getOrElseUpdate((wiggleRoom,slots), {
//
//
//    })
//  }

  @VisibleForTesting
  def solveOne2(row: String): Long = {
    val report :: summaryStr :: Nil = row.split(' ').toList
    val summary = summaryStr.split(',').toList.map(_.toInt)
    solve(
      Iterator.fill(5)(report).mkString("?"),
      List.fill(5)(summary).flatten)
  }

  @VisibleForTesting
  def solveOne(row: String): Long = {
    val report :: summaryStr :: Nil = row.split(' ').toList
    solve(report, summaryStr.split(',').toList.map(_.toInt))
  }

  private def solve(report: String, find: List[Int]) = {
    val howManyBad = report.count(_ == '#')
    val howManyUnknown = report.count(_== '?')
//    println(s"Entered solve. There are $howManyBad definitely bad. Out of $howManyUnknown unknown, exactly ${find.sum-howManyBad} are bad")

    val allocations = allocateSubsections(report, find)
    // TODO Come here - I think the allocations are fine. Faster? Who knows.
//    println(s"found ${allocations.size} allocations")
//
//    solveRec(report, 0, find)
    allocations.iterator
      .map(_.iterator
      .map { case (a,b) => solveRecInner(a, b)}
      .product)
      .sum
  }

  private def allocateSubsections(report: String, toFind: List[Int]) = {
    val subsections = report.split("\\.+").toList
    val subsMins = subsections.map(_.count(_ == '#'))

    def ar(remainingSubsections: List[String], remainingToFind: List[Int], currentlyBeingFound:Option[String]=None, currentAcc:List[Int]=List.empty, acc: List[(String, List[Int])] = List.empty): Vector[List[(String, List[Int])]] =
      if (currentlyBeingFound.isEmpty && remainingToFind.isEmpty && remainingSubsections.isEmpty)
        Vector(acc)
      else if (currentlyBeingFound.isEmpty && remainingToFind.nonEmpty && remainingSubsections.isEmpty)
        Vector.empty
      else if (currentlyBeingFound.isEmpty && remainingSubsections.nonEmpty)
        ar(remainingSubsections.tail, remainingToFind, Some(remainingSubsections.head), List.empty, acc)

      // Is there a missing condition here for remainingToFind.isEmpty?

      else {
        (isAllocationPlausible(currentlyBeingFound.get, currentAcc), remainingToFind.isEmpty) match {
          case (-1, true) => Vector.empty
          case (-1, false) => ar(remainingSubsections, remainingToFind.tail, currentlyBeingFound, currentAcc appended remainingToFind.head, acc)
          case (0, true) => ar(remainingSubsections, remainingToFind, None, List.empty, acc appended(currentlyBeingFound.get, currentAcc))
          case (0, false) =>
                ar(remainingSubsections, remainingToFind.tail, currentlyBeingFound, currentAcc appended remainingToFind.head, acc) ++
                ar(remainingSubsections, remainingToFind, None, List.empty, acc appended(currentlyBeingFound.get, currentAcc))
          case (1, _) => Vector.empty
        }

//        val hremsu :: tailRemsu = remainingSubsections
//        var localAcc = Vector.empty[Int]
//        var h :: t = remainingToFind
//        val min = hremsu.count(_ == '#')
//
//        while (localAcc.sum < min) {
//          localAcc = localAcc appended h
//          if (t.isEmpty)
//            return None
//          h = t.head
//          t = t.tail

      }

    ar(subsections, toFind)
  }

  private val simpleWiggleMemo = mutable.Map.empty[(Int,Int), Long]

  private def simpleWiggle(howManyThings:Int, howMuchRoom:Int): Long = {
    if (howMuchRoom == 0)
      0L
    else if (howManyThings == 1)
      howMuchRoom
    else if (howMuchRoom == 1)
      1L
    else
      simpleWiggleMemo.getOrElseUpdate((howManyThings, howMuchRoom), {
        (1 to howMuchRoom)
          .map(simpleWiggle(howManyThings-1, _))
          .sum
      })
  }

  /**
  * @return 0 if the allocation is fine as it is, -1 if it's under (can be added to to be made OK), 1 if it's over (unrecoverable)
  */
  private def isAllocationPlausible(subsection:String, numbers:List[Int]): Int = {
    val howManyBad = subsection.count(_ == '#')

    val result = if (numbers.size > subsection.length - howManyBad + 1)
      1
    else if (numbers.sum < howManyBad)
      -1
    else if (numbers.sum > subsection.length + 1 - numbers.length)
      1
    else
      0
    result
  }

  /**
   * In this one, we only consider a subsection ('#' and '?' only) with a plausible list of numbers.
   * The answer could still be 0 because of the distribution of '#' (if exist)
   */
  private def solveRecInner(subsection: String, remaining: List[Int], pos: Int = 0): Long = {
    if (remaining.isEmpty)
      if (pos < subsection.length && subsection.substring(pos).contains('#'))
        0
      else
        1
    else if (pos >= subsection.length)
      0
//  Not sure what's wrong with this
//    else if (subsection.substring(pos).forall(_== '?')) {
//      val howManyThings = remaining.size
//      val howMuchRoom = subsection.length - pos - remaining.sum - remaining.size + 2
//      val wiggles = simpleWiggle(howManyThings, howMuchRoom)
//      //      println(s"Special case! [$subsection $remaining] $howManyThings $howMuchRoom -> $wiggles")
//      wiggles
//    }
    else if (subsection.forall(_== '?')) {
      val howManyThings = remaining.size
      val howMuchRoom = subsection.length - remaining.sum - remaining.size + 2
      val wiggles = simpleWiggle(howManyThings, howMuchRoom)
//      println(s"Special case! [$subsection $remaining] $howManyThings $howMuchRoom -> $wiggles")
      wiggles
    }
    else {
      val endPos = pos + remaining.head
      if (endPos > subsection.length)
        0
      else if (endPos < subsection.length && subsection(pos) == '#' && subsection(endPos) == '#')
        0
//      else if (subsection.substring(pos, endPos).contains('.'))
//        if (subsection(pos) != '#')
//          solveRecInner(subsection, remaining, pos + 1)
//        else
//          0
      else if (endPos < subsection.length && subsection(endPos) == '#')
        solveRecInner(subsection, remaining, pos + 1)
      else if (subsection(pos) == '#')
        solveRecInner(subsection, remaining.tail, endPos + 1)
      else
        solveRecInner(subsection, remaining, pos + 1) + solveRecInner(subsection, remaining.tail, endPos + 1)
    }
  }

  private def solveRec(report: String, pos: Int, remaining: List[Int]): Long = {
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
          solveRec(report, pos + 1, remaining)
        else
          0
      else if (endPos < report.length && report(endPos) == '#')
        solveRec(report, pos + 1, remaining)
      else if (report(pos) == '#')
        solveRec(report, endPos + 1, remaining.tail)
      else
        solveRec(report, pos + 1, remaining) + solveRec(report, endPos + 1, remaining.tail)
    }
  }
}

// 7361
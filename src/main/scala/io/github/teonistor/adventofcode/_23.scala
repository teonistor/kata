package io.github.teonistor.adventofcode

import io.vavr.collection

import scala.collection.immutable.{HashSet, TreeMap}


object _23 {

  // There's no way to achieve good time complexity with immutable data structures I can think of
  private class MutableCircle(seq: IndexedSeq[Int]) {

    private var cups = seq.toArray
    private var indexes = seq.zipWithIndex.sortBy(_._1).map(_._2).toArray
    private val size = cups.length

//  This is O(n) instead of O(n log n) but oh well it's just once
//    def init(seq: IndexedSeq[Int]) {
//      cups = Array.fill(seq.size)(0)
//      indexes = Array.fill(seq.size)(0)
//      seq.zipWithIndex.foreach(cupAndIndex => {
//        cups.update(cupAndIndex._2, cupAndIndex._1)
//        indexes.update(cupAndIndex._1, cupAndIndex._2)
//      })
//    }

    def apply(index: Int):Int={
      cups(index % size)
    }

    def ensureRange(i:Int):Int=
      (i + size) % size

    def indexOf(cup:Int): Int =
      indexes(cup)

    def getFourAt(index: Int): (Int, Set[Int])={
      (this(index), HashSet(this(index), this(index+1), this(index+2), this(index+3)))
    }

    /**
     * @param from Index of the "current" element
     * @param to Index of the element after which
     * @return the index displacement (0 or 3 depending on the side that was moved)
     */
    def displaceThree(from:Int,to:Int):Int= {
      /*   val middle = ensureRange(from + 3)

      val a:Int;

      if (ensureRange(from - to) > ensureRange(to - middle)) {
        // We move the section containing the middle
        // middle -> from; for (to-middle)

      } else {
        // We move the section containing the from
        // to -> to+3

      }

      if (shiftBegin < from) {
        // Very special case when we are right
      }
*/
      // You cannot get more optimised than this
      val clockwiseDisplacement = ensureRange(from - to)
      val anticlockwiseDisplacement = ensureRange(to - from - 3)

      if (clockwiseDisplacement > anticlockwiseDisplacement) {
        displaceAnticlockwise((from + 4) % size, anticlockwiseDisplacement)
        0
      } else {
        displaceClockwise((to + 1) % size, clockwiseDisplacement)
        3
      }
    }

    private def displaceClockwise(begin:Int, length:Int) {
      // Remember the 3 elements which will move out of the way
      val a = this (begin + length)
      val b = this (begin + length + 1)
      val c = this (begin + length + 2)

      if (begin + length > size) {
        println("Going clockwise over seam")
        // The section we need to displace is wrapping around the seam. The part of it that is over the seam is safe to move right away
        val howManyDisplacedFirst = ensureRange(begin + length)
        System.arraycopy(cups, 0, cups, 3, howManyDisplacedFirst)

        // Now we "carefully" move up to 3 elements across the seam
        val howManyDisplacedNext = length - howManyDisplacedFirst
        cups(2) = cups(size - 1)
        if (howManyDisplacedNext > 1)
          cups(1) = cups(size - 2)
        if (howManyDisplacedNext > 2)
          cups(0) = cups(size - 3)

        // Now we move the remaining elements (if any) in bulk safely as they will not hit the seam
        if (howManyDisplacedNext > 3)
          System.arraycopy(cups, begin, cups, begin + 3, howManyDisplacedNext - 3)


      } else {
        println("Going clockwise no seam")
        // The section we need to displace is continuous. But is the seam within the next 3 elements?
        val howManyDisplacedBulk =
          if (begin + length + 3> size) {
            // If yes, move up to 3 elements "carefully" first
            cups((begin + length + 2) % size) = cups(begin + length - 1)
            if (length > 1)
              cups((begin + length + 1) % size) = cups(begin + length - 2)
            if (length > 2)
              cups((begin + length) % size) = cups(begin + length - 3)

            length - 3
            // If not, everything can be moved in one go
          } else length

        if (howManyDisplacedBulk > 0)
          System.arraycopy(cups, begin, cups, begin + 3, howManyDisplacedBulk)
      }

      // Put down the 3 elements which we picked up at the beginning
      cups(begin) = a
      cups((begin + 1) % size) = b
      cups((begin + 2) % size) = c

      // Again this if to call the correct index update method
      if (begin + length > size)
        updateIndexOverSeam(begin, (begin + length + 2) % size)
      else
        updateIndexContinuous(begin, (begin + length + 2) % size)
    }


    private def displaceAnticlockwise(begin:Int, length:Int) {
      // Remember the 3 elements which will move out of the way
      val a = cups(ensureRange( begin -3))
      val b = cups(ensureRange( begin -2))
      val c = cups(ensureRange( begin -1))

      if (begin + length > size) {
        println("Going anticlockwise over seam")
        // The section we need to displace is wrapping around the seam. The part of it up to the seam is safe to move right away
        val howManyDisplacedFirst = size - begin
        System.arraycopy(cups, begin, cups, begin-3, howManyDisplacedFirst)

        // Now we "carefully" move up to 3 elements across the seam
        val howManyDisplacedNext = length - howManyDisplacedFirst
        cups(size-3)=cups(0)
        if (howManyDisplacedNext > 1)
          cups(size-2)=cups(1)
        if (howManyDisplacedNext > 2)
          cups(size-1)=cups(2)

        // Now we move the remaining elements (if any) in bulk safely as they will not hit the seam
        if (howManyDisplacedNext > 3)
          System.arraycopy(cups, 3, cups, 0, howManyDisplacedNext - 3)

      } else {
        println("Going anticlockwise no seam")
        // The section we need to displace is continuous. But is the seam within the previous 3 elements?
        val (howManyDisplacedBulk, andWhereFrom) =
          if (begin < 3) {
            // If yes, move up to 3 elements "carefully" first
            cups(ensureRange(begin-3))=cups(begin)
            if (length > 1)
              cups(ensureRange(begin-2))=cups(begin+1)
            if (length>2)
              cups(ensureRange(begin-1))=cups(begin+2)

            (length-3, begin+3)
            // If not, everything can be moved in one go
          } else (length, begin)

        if (howManyDisplacedBulk > 0)
          System.arraycopy(cups, andWhereFrom, cups, andWhereFrom-3, howManyDisplacedBulk)
      }

      // Put down the 3 elements which we picked up at the beginning
      cups(ensureRange(begin+length-3)) = a
      cups(ensureRange(begin+length-2)) = b
      cups(ensureRange(begin+length-1)) = c

      // Again this if to call the correct index update method
      if (begin + length > size)
        updateIndexOverSeam(begin-3, (begin + length -1) % size)
      else
        updateIndexContinuous(ensureRange(begin-3), (begin + length -1) % size)
      }

      private def updateIndexOverSeam(begin:Int, end:Int){
      (begin until size).foreach(i => indexes(cups(i)) = i)
      (0 to end).foreach(i => indexes(cups(i)) = i)
    }

    private def updateIndexContinuous(begin:Int, end:Int){
      (begin to end).foreach(i => indexes(cups(i)) = i)
    }

    override def toString: String = s"Cups: ${cups.toList}\nIndexes: ${indexes.toList}\n"
  }

  // Manual testing for MutableCircle
  def main(arg: Array[String]) {
    val circle = new MutableCircle(Vector(2, 3, 5, 6, 1, 4, 7, 8, 0))//.map(_-1))
    println(circle)

    circle.displaceThree(1,7)
    println(circle)
  }

  def findCandidate (banned: Set[Int], candidate:Int, ensure: Int=>Int):Int=
    if (banned contains candidate)
      findCandidate(banned,ensure(candidate - 1),ensure)
  else candidate

  def move(circle: MutableCircle, index:Int):Int={
    println(circle, index)

    val(current, getFour) = circle.getFourAt(index)

    val candidate = findCandidate(getFour, current, circle.ensureRange)
    println(candidate)

    val indexDelta = circle.displaceThree(circle.indexOf(current), circle.indexOf(candidate))
    circle.ensureRange(index + indexDelta + 1)
  }

  def move(circle: Vector[Int]): Vector[Int] = {
//    println(System.currentTimeMillis())
    val current = circle(0)
    val take1 = circle(1)
    val take2 = circle(2)
    val take3 = circle(3)

    println(System.currentTimeMillis())
    val tail = circle.drop(4)

//    println(System.currentTimeMillis())

//    val tailWithIndex = tail.to(LazyList).zipWithIndex
//    lazy val tuple = { println("Calculating!")
//      tailWithIndex.maxBy(_._1) }

//    println(System.currentTimeMillis())
//    val destinationIndex = tailWithIndex
//      .filter(_._1 < current).maxByOption(_._1).getOrElse(tuple)
//      ._2 + 1

    // also a reverse map, but how??

    val avoid = Set(current,take1,take2,take3)
    def findNext (candidate:Int):Int={
//      println(candidate)
      if (avoid contains candidate)
        findNext(if (candidate == 1) 9 else candidate - 1)
      else candidate
    }

//    println(System.currentTimeMillis())
    val destinationIndex = tail.indexOf(findNext(current)) + 1

    // The problem has moved to here

    println(System.currentTimeMillis())

    val a = tail.take(destinationIndex)
    val b = tail.drop(destinationIndex)
    val result=  Vector.concat(
      a,
      Seq(take1),
      Seq(take2),
      Seq(take3),
      b,
      Seq(current))

//    val result=List.concat(
//      tail.to(LazyList).take(destinationIndex),
//      List(take1),
//      List(take2),
//      List(take3),
//      tail.drop(destinationIndex),
//      List(current))

//    val result = tail.to(LazyList).take(destinationIndex).appended(take1).appended(take2).appended(take3)
//      .concat(tail.drop(destinationIndex)).appended(current).to(List)

//    println(System.currentTimeMillis())
//    System.exit(-1)
    result
  }

  def moveRec(circle: MutableCircle, index:Int, movesLeft: Int): Unit =
    if (movesLeft > 0)
     {
      if (movesLeft % 100000 == 0) println(s"$movesLeft moves left @${System.currentTimeMillis()}")
      moveRec(circle, move(circle,index), movesLeft - 1)
  }

  def _1(input: String): String = {
    val circle = new MutableCircle(input.toVector.map(_.toString.toInt - 1))
    moveRec(circle, 0, 100)
    val index = circle.indexOf(1)
    (index until 9).concat(0 until index).map(circle(_) + 1).mkString
  }

  def _2(input: String): Long = {
    val circle = new MutableCircle(input.toVector.map(_.toString.toInt - 1).concat(9 to 999999))
    moveRec(circle, 0, 10000000)

    val index = circle.indexOf('1')
    (circle(index + 1) + 1L) * (circle(index + 2) + 1L)
  }
}

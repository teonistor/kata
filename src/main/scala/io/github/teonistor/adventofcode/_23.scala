package io.github.teonistor.adventofcode

import scala.collection.immutable.HashSet


object _23 {

  private class MutableCircle(seq: IndexedSeq[Int]) {

    private class MutableNode(val value: Int, var next: MutableNode = null) {}

    private val size = seq.length
    private val nodes = {
      val temp = Array.tabulate(size)(new MutableNode(_))
      (0 to size - 2).foreach(i => temp(seq(i)).next = temp(seq(i + 1)))
      temp(seq.last).next = temp(seq(0))
      temp
    }
    private var current = nodes(seq(0))


    def ensureRange(i: Int): Int =
      (i + size) % size

    def iterateFrom(origin: Int): LazyList[Int] =
      LazyList.iterate(nodes(origin))(_.next).map(_.value)

    def getCurrentAndFour: (Int, Set[Int]) =
      (current.value, HashSet.iterate(current, 4)(_.next).map(_.value))

    /**
     * Rotate the current pointer once along
     */
    def advance() {
      current = current.next
    }

    /**
     * Move the 3 elements from after the current one to after the given element
     * @param to Element value after which to put the 3 elements
     */
    def displaceThree(to: Int) {
      val displaced = current.next
      current.next = displaced.next.next.next

      displaced.next.next.next = nodes(to).next
      nodes(to).next = displaced
    }

    override def toString: String =
      LazyList.iterate(current.next)(_.next).takeWhile(_ != current).prepended(current).map(_.value).mkString("[", " ", "]")
  }

  def findCandidate(banned: Set[Int], candidate: Int, ensure: Int => Int): Int =
    if (banned contains candidate)
      findCandidate(banned, ensure(candidate - 1), ensure)
    else candidate

  def move(circle: MutableCircle) {
    val (current, four) = circle.getCurrentAndFour
    val candidate = findCandidate(four, current, circle.ensureRange)

    circle.displaceThree(candidate)
    circle.advance()
  }

  def moveRec(circle: MutableCircle, movesLeft: Int): Unit =
    if (movesLeft > 0) {
      move(circle)
      moveRec(circle, movesLeft - 1)
    }

  def _1(input: String): String = {
    val circle = new MutableCircle(input.toVector.map(_.toString.toInt - 1))
    moveRec(circle, 100)
    circle.iterateFrom(0).slice(1, 9).map(_ + 1).mkString
  }

  def _2(input: String): Long = {
    val circle = new MutableCircle(input.toVector.map(_.toString.toInt - 1).concat(9 to 999999))
    moveRec(circle, 10000000)
    circle.iterateFrom(0).slice(1, 3).map(_ + 1L).product
  }
}

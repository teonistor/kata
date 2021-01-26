package io.github.teonistor.adventofcode

import scala.collection.immutable.HashSet


object _23 {


  private class MutableCircle(seq: IndexedSeq[Int]) {

    private class MutableNode(val value: Int, var next: MutableNode = null){}

    private val size = seq.length
    private val nodes = {
      val temp = Array.tabulate(size)(new MutableNode(_))
      (0 to size-2).foreach(i => temp(seq(i)).next = temp(seq(i+1)))
      temp(seq.last).next = temp(seq(0))
      temp
    }
    private var current = nodes(seq(0))

    private def apRec(index:Int,node: MutableNode):MutableNode={
      if(index ==0) node
      else apRec(index -1, node.next)
    }

    def apply(index: Int):Int={
      if (index == 0) current.value
      else if (index < 0) apply(index + size)
      else if (index >= size) apply(index - size)
      else apRec(index, current).value
    }

    def ensureRange(i:Int):Int=
      (i + size) % size

    def iterateFrom(origin:Int): LazyList[Int] =
      LazyList.iterate(nodes(origin))(_.next).map(_.value)
//
//    // TODO This is inefficient (obvs)
//    @tailrec private def indexRec(value:Int,accumulator:Int,node: MutableNode):Int=
//      if (node.value == value) accumulator
//      else indexRec(value,accumulator+1,node.next)
//
//    def indexOf(value:Int): Int =
//      indexRec(value, 0, current)

    def getCurrentAndFour: (Int, Set[Int])={
      (current.value,  HashSet.iterate(current, 4)(_.next).map(_.value))
    }

    /**
     * Rotate the current pointer once along
     */
    def advance(){
      current=current.next
    }

    /**
     * Move the 3 elements from after the current one to after the given element
     * @param to Element value after which to put the 3 elements
     */
    def displaceThree(to:Int){
      val displaced = current.next
      current.next = displaced.next.next.next

      displaced.next.next.next = nodes(to).next
      nodes(to).next = displaced
    }

    override def toString: String =
      LazyList.iterate(current.next)(_.next).takeWhile(_ != current).prepended(current).map(_.value).mkString("[", " ", "]")
  }

  // Manual testing for MutableCircle
//  def main(arg: Array[String]) {
//    val circle = new MutableCircle(Vector(2, 3, 5, 6, 1, 4, 7, 8, 0))//.map(_-1))
//    println(circle)
//
//    circle.displaceThree(1,7)
//    println(circle)
//  }

  def findCandidate (banned: Set[Int], candidate:Int, ensure: Int=>Int):Int=
    if (banned contains candidate)
      findCandidate(banned,ensure(candidate - 1),ensure)
  else candidate

  def move(circle: MutableCircle){
//    println(s"Circle: $circle")

    val(current, four) = circle.getCurrentAndFour

    val candidate = findCandidate(four, current, circle.ensureRange)
//    println(s"Candidate: $candidate")

    circle.displaceThree(candidate)
    circle.advance()
  }

  def moveRec(circle: MutableCircle,  movesLeft: Int): Unit =
    if (movesLeft > 0)
     {
//      if (movesLeft % 100000 == 0) println(s"$movesLeft moves left @${System.currentTimeMillis()}")
       move(circle)
       moveRec(circle,  movesLeft - 1)
  }

  def _1(input: String): String = {
    val circle = new MutableCircle(input.toVector.map(_.toString.toInt - 1))
    moveRec(circle,  100)
    circle.iterateFrom(0).slice(1, 9).map(_+1).mkString
//    val index = circle.indexOf(0)
//    (index + 1 until 9).concat(0 until index).map(circle(_) + 1).mkString
  }

  def _2(input: String): Long = {
    val circle = new MutableCircle(input.toVector.map(_.toString.toInt - 1).concat(9 to 999999))
    moveRec(circle,  10000000)
    circle.iterateFrom(0).slice(1, 3).map(_+1L).product

//    val index = circle.indexOf(0)
//    (circle(index + 1) + 1L) * (circle(index + 2) + 1L)
  }
}

package io.github.teonistor.adventofcode

import io.vavr.collection

import scala.collection.immutable.TreeMap


object _23 {

  def move(circle: List[Int]): List[Int] = {
//    println(System.currentTimeMillis())
    val current = circle.head
    val take1 = circle(1)
    val take2 = circle(2)
    val take3 = circle(3)

//    println(System.currentTimeMillis())
    val tail = circle.drop(4)

//    println(System.currentTimeMillis())

//    val tailWithIndex = tail.to(LazyList).zipWithIndex
//    lazy val tuple = { println("Calculating!")
//      tailWithIndex.maxBy(_._1) }

//    println(System.currentTimeMillis())
    // problem starts here
//    val destinationIndex = tailWithIndex
//      .filter(_._1 < current).maxByOption(_._1).getOrElse(tuple)
//      ._2 + 1

    // also a reverse map, but how??

    val avoid = Set(current,take1,take2,take3)
    def findNext (candidate:Int):Int={
      println(candidate)
      if (avoid contains candidate)
        findNext(if (candidate == 1) 9 else candidate - 1)
      else candidate
    }

    val destinationIndex = tail.indexOf(findNext(current)) + 1

    // until here

//    println(System.currentTimeMillis())
    val result = tail.to(LazyList).take(destinationIndex).appended(take1).appended(take2).appended(take3)
      .concat(tail.drop(destinationIndex)).appended(current).to(List)

//    println(System.currentTimeMillis())
//    System.exit(-1)
    result
  }

  def moveRec(circle: List[Int], movesLeft: Int): List[Int] =
    if (movesLeft == 0) circle
    else {
      /*if (movesLeft % 1000 == 0) */println(s"$movesLeft moves left")
      moveRec(move(circle), movesLeft - 1)
  }

  def _1(input: String): String = {
    val circle = input.toList.map(_.toString.toInt)
    val endCircle = moveRec(circle, 100)

    val index = endCircle.indexOf(1)
    endCircle.drop(index + 1).mkString + endCircle.take(index).mkString
  }

  def _2(input: String): Long = {
    val circle = input.toList.map(_.toString.toInt).concat(10 to 1000000)
    val endCircle = moveRec(circle, 10000000)

    val index = endCircle.indexOf('1')
    val rotatedCircle = endCircle.drop(index + 1).concat(endCircle.take(index))
    rotatedCircle.head.toLong * rotatedCircle(1).toLong
  }
}

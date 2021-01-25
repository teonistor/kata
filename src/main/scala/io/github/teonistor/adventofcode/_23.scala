package io.github.teonistor.adventofcode

import io.vavr.collection

import scala.collection.immutable.TreeMap


object _23 {

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

  def moveRec(circle: Vector[Int], movesLeft: Int): Vector[Int] =
    if (movesLeft == 0) circle
    else {
      if (movesLeft % 1000 == 0) println(s"$movesLeft moves left @${System.currentTimeMillis()}")
      moveRec(move(circle), movesLeft - 1)
  }

  def _1(input: String): String = {
    val circle = input.toVector.map(_.toString.toInt)
    val endCircle = moveRec(circle, 100)

    val index = endCircle.indexOf(1)
    endCircle.drop(index + 1).mkString + endCircle.take(index).mkString
  }

  def _2(input: String): Long = {
    val circle = input.toVector.map(_.toString.toInt).concat(10 to 1000000)
    val endCircle = moveRec(circle, 10000000)

    val index = endCircle.indexOf('1')
    val rotatedCircle = endCircle.drop(index + 1).concat(endCircle.take(index))
    rotatedCircle.head.toLong * rotatedCircle(1).toLong
  }
}

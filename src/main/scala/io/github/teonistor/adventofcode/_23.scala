package io.github.teonistor.adventofcode

object _23 {

  def move(circle: List[Int]): List[Int] = {
    val current = circle.head
    val take1 = circle(1)
    val take2 = circle(2)
    val take3 = circle(3)

    val tail = circle.drop(4)

    val destinationIndex = tail.zipWithIndex.filter(_._1 < current).maxByOption(_._1).getOrElse(tail.zipWithIndex.maxBy(_._1))
      ._2 + 1

    tail.to(LazyList).take(destinationIndex).appended(take1).appended(take2).appended(take3)
      .concat(tail.drop(destinationIndex)).appended(current).to(List)
  }

  def moveRec(circle: List[Int], movesLeft: Int): List[Int] =
    if (movesLeft == 0) circle
    else {
      if (movesLeft % 1000 == 0) println(s"$movesLeft moves left")
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

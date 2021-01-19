package io.github.teonistor.adventofcode

object _23 {

  def move(circle: List[Char]): List[Char] = {
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

  def _1(input: String): String = {
    // TODO var
    var circle = input.toList

    for (_ <- 1 to 100) {
      circle = move(circle)
    }

    val index = circle.indexOf('1')
    circle.drop(index + 1).mkString + circle.take(index).mkString
  }

  def _2(input: String): String = {
    "0"
  }
}

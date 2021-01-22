package io.github.teonistor.adventofcode

object _01 {

  def _1(input: String): Int = {
    val numbers = input.split('\n').to(LazyList).map(_.toInt)
    val number = numbers.find(numbers contains 2020 - _).get
    number * (2020 - number)
  }

  def _2(input: String): Int = {
    val numbers = input.split('\n').to(LazyList).map(_.toInt)
    val (a, b) = numbers.flatMap(a => numbers.filter(_ != a).map((a, _)))
      .find(ab => numbers contains 2020 - ab._1 - ab._2).get
    a * b * (2020 - a - b)
  }
}

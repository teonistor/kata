package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _04 extends StandardAdventOfCodeSolution[Int] {

  override def _1(input: String): Int = {
    val horizontal = input.linesIterator
      .toArray
    val array = horizontal.map(_.toArray)
    val verical = array
      .transpose
      .map(_.mkString)
    val diagUp = (0 to array.length + array(0).length - 2)
      .map(coordSum => (0 to coordSum)
        .flatMap(i => array.lift(i).flatMap(_.lift(coordSum - i)))
        .mkString)
    val diagDown = (-array.length + 1 until array(0).length)
      .map(coordDiff => array.indices
        .flatMap(i => array(i).lift(i + coordDiff))
        .mkString)

    val x = "XMAS".r
    val y = "SAMX".r

    Iterator(horizontal.iterator, verical.iterator, diagUp, diagDown)
      .flatten
      .map(s => x.findAllIn(s).size + y.findAllMatchIn(s).size)
      .sum
  }

  override def _2(input: String): Int = {
    val array = input.linesIterator.toArray
    (1 to array.length - 2).map(i =>
        (1 to array(i).length - 2).count(j =>
          if (array(i)(j) == 'A') {
            val m = LazyList((i - 1, j - 1), (i - 1, j + 1), (i + 1, j + 1), (i + 1, j - 1))
              .groupMapReduce { case (y, x) => array(y)(x) }(Set(_))(_ ++ _)
            if (m.keySet == Set('M', 'S')) {
              if (m('M').size == 2) {
                val l = m('M').toList
                l.head._1 == l(1)._1 || l.head._2 == l(1)._2
              } else
                false
            } else
              false
          } else
            false
        ))
      .sum
  }
}

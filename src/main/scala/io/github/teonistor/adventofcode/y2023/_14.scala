package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _14 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long = {
    input.split('\n')
      .map(_.toCharArray)
      .transpose
      .map{ chars =>
        val charsStr = chars.mkString
        val cubes = chars.iterator.zipWithIndex
          .filter(_._1 == '#')
          .map(_._2)
          .toList

        val starts = cubes.map(_+1).prepended(0)
        val ends = cubes.appended(chars.length)

        (starts zip ends).map {
          case (start,end) =>
            val howMany = charsStr.substring(start, end).count(_ == 'O')

            howMany.toLong * (chars.length - start) - howMany.toLong * (howMany - 1) / 2
        }
          .sum
      }
      .sum


  }

  override def _2(input: String): Long = {
    ???
  }
}


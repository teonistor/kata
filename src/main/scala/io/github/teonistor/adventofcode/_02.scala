package io.github.teonistor.adventofcode

import java.util.regex.Pattern

object _02 {
  private val regex = Pattern.compile("(\\d+)-(\\d+) (.): (.+)")

  def _1(input: String): Int =
    input.split('\n').to(LazyList)
      .map(regex.matcher)
      .count(mtch => {
        mtch.matches()

        val howMany = mtch.group(4).count(c => mtch.group(3).equals(c.toString))

        mtch.group(1).toInt <= howMany && howMany <= mtch.group(2).toInt
      })

  def _2(input: String): Int =
    input.split('\n').to(LazyList)
      .map(regex.matcher)
      .count(mtch => {
        mtch.matches()

        val first = mtch.group(4)(mtch.group(1).toInt - 1).toString
        val second = mtch.group(4)(mtch.group(2).toInt - 1).toString

        LazyList(first, second).count(mtch.group(3).equals) == 1
      })
}

package io.github.teonistor.adventofcode

import io.github.teonistor.adventofcode.AdventOfCodeSolution.safeFindAttributes

abstract class AdventOfCodeSolution[T] {
  val (year, day) = safeFindAttributes(getClass)

  def _1(input: String): T
  def _2(input: String): T
}

object AdventOfCodeSolution {
  private val findAttributes = ".*y(\\d+)\\._(\\d+).*".r

  private def safeFindAttributes(cls: Class[_]) =
    cls.getCanonicalName match {
      case findAttributes(year, day) => (year.toInt, day.toInt)
      case _=>
        System.err.printf("Could not extract year and day attributes from class %s%n", cls)
        (-1, -1)
    }
}

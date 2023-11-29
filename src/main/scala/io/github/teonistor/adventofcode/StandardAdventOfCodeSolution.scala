package io.github.teonistor.adventofcode

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution.safeFindAttributes

abstract class StandardAdventOfCodeSolution[T] extends AdventOfCodeSolution[T] {
  // Lazy to avoid ExceptionInInitializerError in case of a borked implementing object
  override lazy val (year, day) = safeFindAttributes(getClass)
}

object StandardAdventOfCodeSolution {
  private val findAttributes = ".*y(\\d+)[^.]*\\._(\\d+)[^.]*".r

  private def safeFindAttributes(cls: Class[_]) =
    cls.getCanonicalName match {
      case findAttributes(year, day) => (year.toInt, day.toInt)
      case _=> throw new IllegalStateException("Could not extract year and day attributes from class "+ cls)
    }
}

package io.github.teonistor.adventofcode

object _15 {

  private def solve(input: String, limit: Int) = {
    val ints = input.split(',').map(_.toInt)

    def sayNumber(pos: Int, ages: Map[Int, Int], lastNumber: Int): Int =
      if (pos == limit)
        lastNumber
      else if (ages contains lastNumber)
        sayNumber(pos + 1, ages + ((lastNumber, pos)), pos - ages(lastNumber))
      else
        sayNumber(pos + 1, ages + ((lastNumber, pos)), 0)

    sayNumber(ints.length - 1, Map.from((0 to ints.length - 2).map(i => (ints(i), i))), ints.last)
  }

  def _1(input: String): Int = solve(input, 2020 - 1)

  // Takes ~1.5 min
  def _2(input: String): Int = solve(input, 30000000 - 1)
}

package io.github.teonistor.adventofcode

import java.lang.Integer.parseInt

object _05 {

  def _1(input: String): Int = {
    input.split("\n").map(_
      .replaceAll("[BR]", "1")
      .replaceAll("[FL]", "0"))
      .map(parseInt(_, 2))
      .max
  }

  def _2(input: String): Int = {
    val splitInput = input.split("\n").toSet

    val existingTix = splitInput.map(_
      .replaceAll("[BR]", "1")
      .replaceAll("[FL]", "0"))
      .map(parseInt(_, 2))

    val unassigned = (0 to 2047).toSet &~ existingTix
    unassigned.filter(u => (existingTix contains u + 1) && (existingTix contains u - 1))
      .head
  }
}

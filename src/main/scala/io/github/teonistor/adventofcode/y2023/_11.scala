package io.github.teonistor.adventofcode.y2023

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.abs

object _11 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long =
    solve(input, 1)

  override def _2(input: String): Long =
    // I couldn't tell you why I have to put 1 less
    solve(input, 999999)

  @VisibleForTesting
  def solve(input: String, oldness: Int): Long = {
    val universe = input.split("\n")
    val compactGalaxies = universe.indices
      .flatMap(row => universe(row).indices
        .filter(col => universe(row)(col) == '#')
        .map((row, _)))
      .toSet
    val emptyRows = universe.indices
      .filter(row => universe(row).forall(_ == '.'))
      .toSet
    val emptyCols = universe(0).indices
      .filter(col => universe.forall(_(col) == '.'))
      .toSet

    val galaxies = compactGalaxies.map {
      case (row, col) => ((row + emptyRows.count(_ < row) * oldness).toLong, (col + emptyCols.count(_ < col) * oldness).toLong)
    }

    galaxies.iterator.flatMap(l =>
        galaxies.iterator.map(r =>
          abs(l._1 - r._1) + abs(l._2 - r._2)))
      .sum / 2
  }
}

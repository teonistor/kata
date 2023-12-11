package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _11Test extends AdventOfCodeTestBase {
  private val exampleInput = "...#......\n.......#..\n#.........\n..........\n......#...\n.#........\n.........#\n..........\n.......#..\n#...#....."

  autorun(_11, 374L, 82000210L, exampleInput)

  test("Extra example 1"){
    assert(_11.solve(exampleInput, 9) == 1030)
  }

  test("Extra example 2"){
    assert(_11.solve(exampleInput, 99) == 8410)
  }
}

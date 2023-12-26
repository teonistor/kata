package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _21Test extends AdventOfCodeTestBase {
  private val exampleMap =
    """...........
      |.....###.#.
      |.###.##..#.
      |..#.#...#..
      |....#.#....
      |.##..S####.
      |.##..#...#.
      |.......##..
      |.##.#.####.
      |.##..##.##.
      |...........""".stripMargin

//  autorun(_21, 16, -1, exampleMap)

  test("Part 2 extra examples 6") {
    assert(_21.solveUnbounded(exampleMap, 6) == 16)
  }
  test("Part 2 extra examples 10") {
    assert(_21.solveUnbounded(exampleMap, 10) == 50)
  }
  test("Part 2 extra examples 50") {
    assert(_21.solveUnbounded(exampleMap, 50) == 1594)
  }
  test("Part 2 extra examples 100") {
    assert(_21.solveUnbounded(exampleMap, 100) == 6536)
  }
  test("Part 2 extra examples 500") {
    assert(_21.solveUnbounded(exampleMap, 500) == 167004)
  }
  test("Part 2 extra examples 1000") {
    assert(_21.solveUnbounded(exampleMap, 1000) == 668697)
  }
  test("Part 2 extra examples 5000") {
    assert(_21.solveUnbounded(exampleMap, 5000) == 16733044)
  }
}

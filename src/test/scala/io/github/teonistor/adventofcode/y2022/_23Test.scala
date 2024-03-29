package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _23Test extends AdventOfCodeTestBase {

  test("Small example") {
    assert(_23._1(".....\n..##.\n..#..\n.....\n..##.\n.....") == 25)
    assert(_23._2(".....\n..##.\n..#..\n.....\n..##.\n.....") == 4)
  }

  autorun(_23, 110, 20, "....#..\n..###.#\n#...#.#\n.#...##\n#.###..\n##.#.##\n.#..#..")
}

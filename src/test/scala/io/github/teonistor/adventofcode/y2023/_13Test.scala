package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _13Test extends AdventOfCodeTestBase {
  autorun(_13, 405, 400,
    """#.##..##.
      |..#.##.#.
      |##......#
      |##......#
      |..#.##.#.
      |..##..##.
      |#.#.##.#.
      |
      |#...##..#
      |#....#..#
      |..##..###
      |#####.##.
      |#####.##.
      |..##..###
      |#....#..#
      |""".stripMargin)
}

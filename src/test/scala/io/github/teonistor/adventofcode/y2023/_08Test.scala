package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _08Test extends AdventOfCodeTestBase {

  test("Year 2023 day 08 part 1 larger example") {
    assert(_08._1("LLR\n\nAAA = (BBB, BBB)\nBBB = (AAA, ZZZ)\nZZZ = (ZZZ, ZZZ)") == 6L)
  }

  autorun(_08, 2L, 6L,
    "RL\n\nAAA = (BBB, CCC)\nBBB = (DDD, EEE)\nCCC = (ZZZ, GGG)\nDDD = (DDD, DDD)\nEEE = (EEE, EEE)\nGGG = (GGG, GGG)\nZZZ = (ZZZ, ZZZ)",
    "LR\n\n11A = (11B, XXX)\n11B = (XXX, 11Z)\n11Z = (11B, XXX)\n22A = (22B, XXX)\n22B = (22C, 22C)\n22C = (22Z, 22Z)\n22Z = (22B, 22B)\nXXX = (XXX, XXX)")
}
package io.github.teonistor.adventofcode.y2020

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _14Test extends AdventOfCodeTestBase {
  autorun(_14, 165L, 208L,
    """mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
      |mem[8] = 11
      |mem[7] = 101
      |mem[8] = 0""".stripMargin,
    """mask = 000000000000000000000000000000X1001X
      |mem[42] = 100
      |mask = 00000000000000000000000000000000X0XX
      |mem[26] = 1""".stripMargin)
}

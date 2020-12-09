package io.github.teonistor.adventofcode.y2020

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _08Test extends AdventOfCodeTestBase {
  autorun(_08, 5, 8, "nop +0\nacc +1\njmp +4\nacc +3\njmp -3\nacc -99\nacc +1\njmp -4\nacc +6")
}

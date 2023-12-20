package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _20Test extends AdventOfCodeTestBase {

  autorun(_20, 32000000, -1, "broadcaster -> a, b, c\n%a -> b\n%b -> c\n%c -> inv\n&inv -> a")

  test("Year 2023 day 20 part 1 example 2") {
    assert(_20._1("broadcaster -> a\n%a -> inv, con\n&inv -> b\n%b -> con\n&con -> output") == 11687500)
  }
}

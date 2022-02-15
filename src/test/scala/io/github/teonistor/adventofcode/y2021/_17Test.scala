package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _17Test extends AdventOfCodeTestBase {
  testAndRun(17, _17._1, _17._2,
    "target area: x=20..30, y=-10..-5",
    45,
    112,
    "target area: x=211..232, y=-124..-69"
  )

  test("calculate minimum X velocity") {
    assert(_17.calculateMinimumX(0) == 0)
    assert(_17.calculateMinimumX(1) == 1)
    assert(_17.calculateMinimumX(3) == 2)
    assert(_17.calculateMinimumX(5) == 3)
  }
}

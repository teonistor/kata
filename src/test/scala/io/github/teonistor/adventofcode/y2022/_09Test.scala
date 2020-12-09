package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _09Test extends AdventOfCodeTestBase {

  autorun(_09, 13, 1, "R 4\nU 4\nL 3\nD 1\nR 4\nD 1\nL 5\nR 2")

  test("medium example") {
    assert(_09._2("R 5\nU 8\nL 8\nD 3\nR 17\nD 10\nL 25\nU 20") == 36)
  }

  test("moveTowards") {
    assert(_09.moveTowards((2, 2), (3, 3)) == (3, 3))
    assert(_09.moveTowards((2, 2), (2, 3)) == (2, 3))
    assert(_09.moveTowards((2, 2), (2, 4)) == (2, 3))
    assert(_09.moveTowards((2, 2), (0, 2)) == (1, 2))
    assert(_09.moveTowards((2, 2), (2, 0)) == (2, 1))
    assert(_09.moveTowards((6, 7), (5, 5)) == (6, 6))
    assert(_09.moveTowards((6, 7), (8, 8)) == (7, 7))
    assert(_09.moveTowards((8, 8), (7, 7)) == (7, 7))
  }
}

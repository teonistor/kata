package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _12Test extends AdventOfCodeTestBase {
  private val exampleInput = "???.### 1,1,3\n.??..??...?##. 1,1,3\n?#?#?#?#?#?#?#? 1,3,1,6\n????.#...#... 4,1,1\n????.######..#####. 1,6,5\n?###???????? 3,2,1"

  autorun(_12, 21L, 525152L, exampleInput)

  test("Individual rows in part 1 example") {
    assert(_12.solveOne("???.### 1,1,3") == 1)
    assert(_12.solveOne(".??..??...?##. 1,1,3") == 4)
    assert(_12.solveOne("?#?#?#?#?#?#?#? 1,3,1,6") == 1)
    assert(_12.solveOne("????.#...#... 4,1,1") == 1)
    assert(_12.solveOne("????.######..#####. 1,6,5") == 4)
    assert(_12.solveOne("?###???????? 3,2,1") == 10)
  }

  test("Individual examples of my invention") {
    assert(_12.solveOne("???? 1,1") == 3)
    assert(_12.solveOne(".??.??. 1,1") == 4)
    assert(_12.solveOne(".............................. 1") == 0)
    assert(_12.solveOne("............?..?.............. 1") == 2)
    assert(_12.solveOne("............?..#.............. 2") == 0)
    assert(_12.solveOne("............#..#.............. 1") == 0)
    assert(_12.solveOne("?????????????????????????????? 1,1") == 28 * 29 / 2)
    (1 to 30).foreach(i =>
      assert(_12.solveOne("?????????????????????????????? " + i) == 31 - i))
    (1 to 29).foreach(i =>
      assert(_12.solveOne("############################## " + i) == 0))
  }

  test("Individual rows in part 2 example") {
    assert(_12.solveOne2("???.### 1,1,3") == 1)
    assert(_12.solveOne2(".??..??...?##. 1,1,3") == 16384)
    assert(_12.solveOne2("?#?#?#?#?#?#?#? 1,3,1,6") == 1)
    assert(_12.solveOne2("????.#...#... 4,1,1") == 16)
    assert(_12.solveOne2("????.######..#####. 1,6,5") == 2500)
    assert(_12.solveOne2("?###???????? 3,2,1") == 506250)
  }
}

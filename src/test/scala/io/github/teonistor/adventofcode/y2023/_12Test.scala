package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _12Test extends AdventOfCodeTestBase {

  // Caution! Real input part 2 took 3hr57min
  autorun(_12, 21L, 525152L, "???.### 1,1,3\n.??..??...?##. 1,1,3\n?#?#?#?#?#?#?#? 1,3,1,6\n????.#...#... 4,1,1\n????.######..#####. 1,6,5\n?###???????? 3,2,1")

  test("Individual rows in part 1 example") {
    assert(_12.solveSimpleRow("???.### 1,1,3") == 1)
    assert(_12.solveSimpleRow(".??..??...?##. 1,1,3") == 4)
    assert(_12.solveSimpleRow("?#?#?#?#?#?#?#? 1,3,1,6") == 1)
    assert(_12.solveSimpleRow("????.#...#... 4,1,1") == 1)
    assert(_12.solveSimpleRow("????.######..#####. 1,6,5") == 4)
    assert(_12.solveSimpleRow("?###???????? 3,2,1") == 10)
  }

  test("Individual part 1 examples of my invention") {
    assert(_12.solveSimpleRow("???? 1,1") == 3)
    assert(_12.solveSimpleRow(".??.??. 1,1") == 4)
    assert(_12.solveSimpleRow(".............................. 1") == 0)
    assert(_12.solveSimpleRow("............?..?.............. 1") == 2)
    assert(_12.solveSimpleRow("............?..#.............. 2") == 0)
    assert(_12.solveSimpleRow("............#..#.............. 1") == 0)
    assert(_12.solveSimpleRow("?????????????????????????????? 1,1") == 28 * 29 / 2)
    (1 to 30).foreach(i =>
      assert(_12.solveSimpleRow("?????????????????????????????? " + i) == 31 - i))
    (1 to 29).foreach(i =>
      assert(_12.solveSimpleRow("############################## " + i) == 0))
  }

  test("Individual rows in part 2 example") {
    assert(_12.solveFoldedRow("???.### 1,1,3") == 1)
    assert(_12.solveFoldedRow(".??..??...?##. 1,1,3") == 16384)
    assert(_12.solveFoldedRow("?#?#?#?#?#?#?#? 1,3,1,6") == 1)
    assert(_12.solveFoldedRow("????.#...#... 4,1,1") == 16)
    assert(_12.solveFoldedRow("????.######..#####. 1,6,5") == 2500)
    assert(_12.solveFoldedRow("?###???????? 3,2,1") == 506250)
  }

  test("Individual part 2 examples of my invention") {
    assert(_12.solveFoldedRow("????????.????. 1,2,2,1") == 13699867637L)
  }
}

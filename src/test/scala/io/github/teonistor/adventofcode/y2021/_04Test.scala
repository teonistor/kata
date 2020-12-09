package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _04Test extends AdventOfCodeTestBase {
  autorun(_04, 4512, 1924, "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n\n22 13 17 11  0\n 8  2 23  4 24\n21  9 14 16  7\n 6 10  3 18  5\n 1 12 20 15 19\n\n 3 15  0  2 22\n 9 18 13 17  5\n19  8  7 25 23\n20 11 10 24  4\n14 21 16 12  6\n\n14 21 17 24  4\n10 16 15  9 19\n18  8 23 26 20\n22 11 13  6  5\n 2  0 12  3  7")

  test("processBoard end condition when board wins later with nonzero") {
    val (iterations, score) = _04.processBoard(
      List(27, 2, 3),
      Array(Array(Some(2),Some(3),Some(27),Some(7)), Array(Some(7),None,Some(0),Some(8)), Array(Some(1),None,Some(9),None)),
      5, 20)

    assert (iterations == 8)
    assert (score() == 96)
  }

  test("processBoard end condition when board wins later with zero") {
    val (iterations, score) = _04.processBoard(
      List(27, 2, 0),
      Array(Array(Some(2),Some(0),Some(1),Some(7)), Array(Some(7),None,Some(0),Some(8)), Array(Some(1),None,Some(9),None)),
      8, 20)

    assert (iterations == 11)
    assert (score() == 0)
  }

  test("processBoard end condition when board is winning") {
    val (iterations, score) = _04.processBoard(
      List(27, 2, 0),
      Array(Array(Some(2),None,Some(1),Some(7)), Array(Some(7),None,Some(0),Some(8)), Array(Some(1),None,Some(9),None)),
      5, 20)

    assert (iterations == 5)
    assert (score() == 700)
  }

  test("bingo yes horizontal") {
    assert (_04.bingo(Array(
      Array(Some(1),None,Some(7),Some(9)),
      Array(None,None,None),
      Array(Some(0),Some(2),Some(3),Some(5)))))
  }

  test("bingo yes vertical") {
    assert (_04.bingo(Array(
      Array(None,None,Some(7),Some(9)),
      Array(None,Some(8),Some(0)),
      Array(None,Some(2),Some(3),Some(5)))))
  }

  test("bingo no") {
    assert (!_04.bingo(Array(
      Array(Some(1),Some(7),None,Some(9)),
      Array(None,Some(0),Some(1),None),
      Array(Some(9),Some(9),Some(2),Some(8)))))
  }
}

package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase
import io.github.teonistor.adventofcode.y2022._07.{Dir, File}

class _07Test extends AdventOfCodeTestBase {

  autorun(_07, 95437, 24933642,
    """$ cd /
      |$ ls
      |dir a
      |14848514 b.txt
      |8504156 c.dat
      |dir d
      |$ cd a
      |$ ls
      |dir e
      |29116 f
      |2557 g
      |62596 h.lst
      |$ cd e
      |$ ls
      |584 i
      |$ cd ..
      |$ cd ..
      |$ cd d
      |$ ls
      |4060174 j
      |8033020 d.log
      |5626152 d.ext
      |7214296 k""".stripMargin)

  test("recursiveSet") {
    val inputInner = Dir(List("a", "b"), Map.empty, Map.empty)
    val inputMiddle = Dir(List("b"), Map("a" -> inputInner), Map.empty)
    val inputOuter = Dir(List.empty, Map("b" -> inputMiddle), Map.empty)

    val newInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> newInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val (actualOuter, actualParentDirs) = _07.recursiveSet(newInner, Some(inputMiddle), Map(inputMiddle -> inputOuter))
    assert(actualOuter == expectedOuter)
    assert(actualParentDirs == Map(newInner -> expectedMiddle, expectedMiddle -> expectedOuter))
  }

  test("parseFs - dir in root") {
    val input = Dir(List.empty, Map.empty, Map.empty)

    val newInner = Dir(List("a"), Map.empty, Map.empty)
    val expectedOuter = Dir(List.empty, Map("a" -> newInner), Map.empty)

    val actual = _07.parseFs(List("dir a"), input, currentDir = Some(input))
    assert(actual == expectedOuter)
  }

  test("parseFs - defaults") {
    val newInner = Dir(List("a"), Map.empty, Map.empty)
    val expectedOuter = Dir(List.empty, Map("a" -> newInner), Map.empty)

    val actual = _07.parseFs(List("$ cd /","dir a"))
    assert(actual == expectedOuter)
  }

  test("parseFs - dir deeper") {
    val inputInner = Dir(List("b"), Map.empty, Map.empty)
    val inputOuter = Dir(List.empty, Map("b" -> inputInner), Map.empty)

    val newInner = Dir(List("a", "b"), Map.empty, Map.empty)
    val expectedMiddle = Dir(List("b"), Map("a" -> newInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List("dir a"), inputOuter, Map(inputInner -> inputOuter), Some(inputInner))
    assert(actual == expectedOuter)
  }

  test("parseFs - file") {
    val inputInner = Dir(List("a", "b"), Map.empty, Map.empty)
    val inputMiddle = Dir(List("b"), Map("a" -> inputInner), Map.empty)
    val inputOuter = Dir(List.empty, Map("b" -> inputMiddle), Map.empty)

    val newInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> newInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List("77 c.txt"), inputOuter, Map(inputInner -> inputMiddle, inputMiddle -> inputOuter), Some(inputInner))
    assert(actual == expectedOuter)
  }

  test("parseFs - multi") {
    val expectedInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> expectedInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List(
      "$ cd /",
      "$ ls",
      "dir b",
      "$ cd b",
      "$ ls",
      "dir a",
      "$ cd a",
      "$ ls",
      "77 c.txt"))
    assert(actual == expectedOuter)
  }

  test("parseFs - extraneous cd") {
    val expectedInner = Dir(List("a", "b"), Map.empty, Map("c.txt" -> File("c.txt", 77)))
    val expectedMiddle = Dir(List("b"), Map("a" -> expectedInner), Map.empty)
    val expectedOuter = Dir(List.empty, Map("b" -> expectedMiddle), Map.empty)

    val actual = _07.parseFs(List(
      "$ cd /",
      "$ ls",
      "dir b",
      "$ cd /",
      "$ cd b",
      "$ ls",
      "dir a",
      "$ cd a",
      "$ cd ..",
      "$ cd a",
      "$ ls",
      "77 c.txt"))
    assert(actual == expectedOuter)
  }
}

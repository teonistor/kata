package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _22Test extends AdventOfCodeTestBase {

  autorun(_22, 5, 7, "1,0,1~1,2,1\n0,0,2~2,0,2\n0,2,3~2,2,3\n0,0,4~0,2,4\n2,0,5~2,2,5\n0,1,6~2,1,6\n1,1,8~1,1,9")

  test("Stug") {
    val leanOn = Map(
      "A" -> Set("B", "C"),
      "B" -> Set("D"),
      "C" -> Set("D"),
    "D" -> Set.empty)
    val leanedOn = leanOn.iterator.flatMap {
        case (above, below) => below.iterator.map((_, above))
      }
      .to(Set)
      .groupMap(_._1)(_._2)

    println(leanedOn)
  }
}

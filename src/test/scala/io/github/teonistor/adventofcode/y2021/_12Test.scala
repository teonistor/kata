package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _12Test extends AdventOfCodeTestBase {

  test("Small example") {
    assert(_12._1("start-A\nstart-b\nA-c\nA-b\nb-d\nA-end\nb-end") == 10)
    assert(_12._2("start-A\nstart-b\nA-c\nA-b\nb-d\nA-end\nb-end") == 36)
  }

  test("Medium example") {
    assert(_12._1("dc-end\nHN-start\nstart-kj\ndc-start\ndc-HN\nLN-dc\nHN-end\nkj-sa\nkj-HN\nkj-dc") == 19)
    assert(_12._2("dc-end\nHN-start\nstart-kj\ndc-start\ndc-HN\nLN-dc\nHN-end\nkj-sa\nkj-HN\nkj-dc") == 103)
  }

  autorun(_12, 19, 103, "dc-end\nHN-start\nstart-kj\ndc-start\ndc-HN\nLN-dc\nHN-end\nkj-sa\nkj-HN\nkj-dc")
}

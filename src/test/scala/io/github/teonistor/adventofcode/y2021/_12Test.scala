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

  testAndRun(5, _12._1, _12._2,
    "dc-end\nHN-start\nstart-kj\ndc-start\ndc-HN\nLN-dc\nHN-end\nkj-sa\nkj-HN\nkj-dc",
    19,
    103,
    "start-co\nip-WE\nend-WE\nle-ls\nwt-zi\nend-sz\nwt-RI\nwt-sz\nzi-start\nwt-ip\nYT-sz\nRI-start\nle-end\nip-sz\nWE-sz\nle-WE\nle-wt\nzi-ip\nRI-zi\nco-zi\nco-le\nWB-zi\nwt-WE\nco-RI\nRI-ip"
  )
}

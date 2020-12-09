package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _21Test extends AdventOfCodeTestBase {
  autorun(_21, 152L, 301L,
    """root: pppw + sjmn
      |dbpl: 5
      |cczh: sllz + lgvd
      |zczc: 2
      |ptdq: humn - dvpt
      |dvpt: 3
      |lfqf: 4
      |humn: 5
      |ljgn: 2
      |sjmn: drzm * dbpl
      |sllz: 4
      |pppw: cczh / lfqf
      |lgvd: ljgn * ptdq
      |drzm: hmdt - zczc
      |hmdt: 32""".stripMargin)
}

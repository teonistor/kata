package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _11Test extends AdventOfCodeTestBase {
  testAndRun(_11,
    "Monkey 0:\n  Starting items: 79, 98\n  Operation: new = old * 19\n  Test: divisible by 23\n    If true: throw to monkey 2\n    If false: throw to monkey 3\n\nMonkey 1:\n  Starting items: 54, 65, 75, 74\n  Operation: new = old + 6\n  Test: divisible by 19\n    If true: throw to monkey 2\n    If false: throw to monkey 0\n\nMonkey 2:\n  Starting items: 79, 60, 97\n  Operation: new = old * old\n  Test: divisible by 13\n    If true: throw to monkey 1\n    If false: throw to monkey 3\n\nMonkey 3:\n  Starting items: 74\n  Operation: new = old + 3\n  Test: divisible by 17\n    If true: throw to monkey 0\n    If false: throw to monkey 1",
    10605L,
    2713310158L,
    "Monkey 0:\n  Starting items: 75, 63\n  Operation: new = old * 3\n  Test: divisible by 11\n    If true: throw to monkey 7\n    If false: throw to monkey 2\n\nMonkey 1:\n  Starting items: 65, 79, 98, 77, 56, 54, 83, 94\n  Operation: new = old + 3\n  Test: divisible by 2\n    If true: throw to monkey 2\n    If false: throw to monkey 0\n\nMonkey 2:\n  Starting items: 66\n  Operation: new = old + 5\n  Test: divisible by 5\n    If true: throw to monkey 7\n    If false: throw to monkey 5\n\nMonkey 3:\n  Starting items: 51, 89, 90\n  Operation: new = old * 19\n  Test: divisible by 7\n    If true: throw to monkey 6\n    If false: throw to monkey 4\n\nMonkey 4:\n  Starting items: 75, 94, 66, 90, 77, 82, 61\n  Operation: new = old + 1\n  Test: divisible by 17\n    If true: throw to monkey 6\n    If false: throw to monkey 1\n\nMonkey 5:\n  Starting items: 53, 76, 59, 92, 95\n  Operation: new = old + 2\n  Test: divisible by 19\n    If true: throw to monkey 4\n    If false: throw to monkey 3\n\nMonkey 6:\n  Starting items: 81, 61, 75, 89, 70, 92\n  Operation: new = old * old\n  Test: divisible by 3\n    If true: throw to monkey 0\n    If false: throw to monkey 1\n\nMonkey 7:\n  Starting items: 81, 86, 62, 87\n  Operation: new = old + 8\n  Test: divisible by 13\n    If true: throw to monkey 3\n    If false: throw to monkey 5"
  )
}

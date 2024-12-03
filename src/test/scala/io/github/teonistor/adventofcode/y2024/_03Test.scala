package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _03Test extends AdventOfCodeTestBase {
  autorun(_03, 161L, 48L,
    "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))",
    "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
}

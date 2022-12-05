package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _05Test extends AdventOfCodeTestBase {
  testAndRun(_05,
    "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2",
    "CMZ",
    "MCD",
    "    [H]         [D]     [P]        \n[W] [B]         [C] [Z] [D]        \n[T] [J]     [T] [J] [D] [J]        \n[H] [Z]     [H] [H] [W] [S]     [M]\n[P] [F] [R] [P] [Z] [F] [W]     [F]\n[J] [V] [T] [N] [F] [G] [Z] [S] [S]\n[C] [R] [P] [S] [V] [M] [V] [D] [Z]\n[F] [G] [H] [Z] [N] [P] [M] [N] [D]\n 1   2   3   4   5   6   7   8   9 \n\nmove 2 from 8 to 2\nmove 3 from 9 to 2\nmove 1 from 3 to 8\nmove 5 from 1 to 7\nmove 2 from 9 to 2\nmove 8 from 2 to 4\nmove 6 from 7 to 2\nmove 2 from 1 to 7\nmove 4 from 5 to 9\nmove 4 from 5 to 6\nmove 1 from 8 to 3\nmove 1 from 8 to 5\nmove 2 from 9 to 8\nmove 8 from 6 to 4\nmove 4 from 3 to 6\nmove 10 from 2 to 3\nmove 1 from 5 to 1\nmove 1 from 7 to 4\nmove 2 from 9 to 8\nmove 18 from 4 to 8\nmove 1 from 1 to 6\nmove 4 from 7 to 3\nmove 12 from 8 to 4\nmove 4 from 7 to 9\nmove 5 from 6 to 9\nmove 2 from 2 to 7\nmove 3 from 9 to 5\nmove 3 from 5 to 9\nmove 1 from 2 to 8\nmove 10 from 3 to 1\nmove 2 from 7 to 8\nmove 10 from 1 to 9\nmove 1 from 3 to 5\nmove 16 from 9 to 8\nmove 1 from 3 to 2\nmove 3 from 8 to 3\nmove 1 from 5 to 9\nmove 3 from 6 to 7\nmove 2 from 7 to 2\nmove 1 from 3 to 8\nmove 5 from 4 to 1\nmove 4 from 9 to 5\nmove 2 from 2 to 5\nmove 2 from 1 to 9\nmove 23 from 8 to 4\nmove 6 from 5 to 2\nmove 5 from 2 to 6\nmove 1 from 9 to 6\nmove 2 from 2 to 4\nmove 35 from 4 to 9\nmove 1 from 6 to 1\nmove 2 from 8 to 7\nmove 1 from 6 to 8\nmove 3 from 1 to 7\nmove 1 from 7 to 1\nmove 3 from 6 to 2\nmove 4 from 3 to 7\nmove 6 from 7 to 9\nmove 1 from 6 to 9\nmove 1 from 8 to 1\nmove 2 from 2 to 9\nmove 2 from 8 to 2\nmove 3 from 7 to 3\nmove 2 from 1 to 9\nmove 5 from 9 to 3\nmove 1 from 4 to 2\nmove 1 from 1 to 4\nmove 7 from 3 to 9\nmove 1 from 3 to 4\nmove 2 from 4 to 7\nmove 24 from 9 to 4\nmove 12 from 9 to 3\nmove 1 from 3 to 1\nmove 1 from 1 to 2\nmove 2 from 2 to 6\nmove 1 from 6 to 5\nmove 1 from 6 to 8\nmove 3 from 2 to 4\nmove 1 from 7 to 4\nmove 1 from 5 to 3\nmove 1 from 9 to 8\nmove 23 from 4 to 8\nmove 17 from 8 to 5\nmove 12 from 9 to 8\nmove 10 from 8 to 7\nmove 1 from 8 to 6\nmove 5 from 4 to 3\nmove 3 from 5 to 1\nmove 3 from 1 to 6\nmove 6 from 5 to 4\nmove 10 from 3 to 1\nmove 9 from 1 to 7\nmove 2 from 4 to 9\nmove 1 from 1 to 6\nmove 4 from 8 to 1\nmove 4 from 3 to 7\nmove 4 from 6 to 5\nmove 1 from 9 to 6\nmove 1 from 9 to 2\nmove 1 from 1 to 7\nmove 1 from 2 to 7\nmove 3 from 1 to 7\nmove 9 from 5 to 9\nmove 7 from 9 to 7\nmove 2 from 9 to 1\nmove 3 from 5 to 9\nmove 3 from 4 to 8\nmove 1 from 1 to 2\nmove 1 from 2 to 6\nmove 1 from 1 to 6\nmove 5 from 8 to 7\nmove 1 from 8 to 1\nmove 1 from 3 to 9\nmove 1 from 1 to 6\nmove 2 from 9 to 5\nmove 2 from 3 to 9\nmove 4 from 6 to 3\nmove 1 from 9 to 4\nmove 2 from 4 to 8\nmove 1 from 4 to 8\nmove 1 from 9 to 5\nmove 1 from 6 to 8\nmove 23 from 7 to 8\nmove 27 from 8 to 2\nmove 2 from 8 to 1\nmove 23 from 2 to 6\nmove 3 from 5 to 3\nmove 4 from 2 to 5\nmove 2 from 3 to 1\nmove 2 from 9 to 3\nmove 4 from 1 to 4\nmove 13 from 7 to 9\nmove 1 from 5 to 6\nmove 2 from 5 to 9\nmove 1 from 5 to 3\nmove 3 from 9 to 3\nmove 5 from 9 to 5\nmove 2 from 4 to 2\nmove 1 from 4 to 9\nmove 11 from 6 to 9\nmove 9 from 6 to 1\nmove 17 from 9 to 5\nmove 3 from 7 to 4\nmove 3 from 6 to 3\nmove 14 from 5 to 2\nmove 5 from 3 to 1\nmove 2 from 9 to 4\nmove 2 from 3 to 8\nmove 5 from 5 to 9\nmove 2 from 5 to 4\nmove 7 from 1 to 8\nmove 2 from 9 to 5\nmove 3 from 9 to 8\nmove 8 from 4 to 2\nmove 2 from 7 to 8\nmove 10 from 2 to 9\nmove 10 from 2 to 6\nmove 8 from 9 to 7\nmove 2 from 3 to 9\nmove 3 from 9 to 8\nmove 5 from 3 to 9\nmove 7 from 7 to 9\nmove 3 from 2 to 9\nmove 10 from 8 to 5\nmove 1 from 7 to 6\nmove 1 from 2 to 3\nmove 4 from 1 to 6\nmove 2 from 8 to 4\nmove 1 from 4 to 6\nmove 2 from 6 to 3\nmove 2 from 3 to 1\nmove 1 from 4 to 9\nmove 4 from 1 to 5\nmove 2 from 5 to 2\nmove 2 from 8 to 4\nmove 1 from 3 to 5\nmove 3 from 5 to 7\nmove 2 from 2 to 9\nmove 3 from 7 to 6\nmove 3 from 8 to 5\nmove 10 from 5 to 7\nmove 3 from 6 to 4\nmove 11 from 6 to 1\nmove 3 from 6 to 2\nmove 12 from 1 to 3\nmove 1 from 7 to 5\nmove 9 from 7 to 3\nmove 5 from 5 to 1\nmove 4 from 4 to 6\nmove 2 from 1 to 7\nmove 1 from 2 to 6\nmove 2 from 7 to 8\nmove 1 from 2 to 4\nmove 1 from 9 to 5\nmove 3 from 6 to 7\nmove 1 from 5 to 2\nmove 9 from 9 to 5\nmove 1 from 2 to 8\nmove 1 from 4 to 8\nmove 1 from 1 to 8\nmove 1 from 4 to 2\nmove 1 from 7 to 2\nmove 1 from 6 to 2\nmove 1 from 6 to 8\nmove 6 from 9 to 6\nmove 1 from 3 to 4\nmove 9 from 3 to 5\nmove 1 from 1 to 3\nmove 2 from 2 to 6\nmove 1 from 3 to 5\nmove 14 from 5 to 1\nmove 1 from 2 to 6\nmove 5 from 6 to 4\nmove 3 from 8 to 2\nmove 5 from 6 to 1\nmove 5 from 4 to 6\nmove 1 from 7 to 1\nmove 3 from 9 to 3\nmove 7 from 5 to 7\nmove 1 from 4 to 6\nmove 2 from 7 to 5\nmove 3 from 6 to 1\nmove 3 from 8 to 1\nmove 14 from 3 to 4\nmove 8 from 4 to 2\nmove 1 from 6 to 1\nmove 15 from 1 to 6\nmove 7 from 1 to 6\nmove 6 from 1 to 3\nmove 3 from 3 to 1\nmove 2 from 4 to 5\nmove 1 from 4 to 2\nmove 19 from 6 to 8\nmove 2 from 1 to 8\nmove 4 from 5 to 4\nmove 7 from 8 to 2\nmove 2 from 3 to 1\nmove 13 from 8 to 6\nmove 4 from 4 to 9\nmove 2 from 4 to 8\nmove 2 from 1 to 6\nmove 1 from 3 to 5\nmove 19 from 2 to 3\nmove 13 from 3 to 1\nmove 1 from 4 to 9\nmove 1 from 2 to 8\nmove 3 from 7 to 1\nmove 14 from 6 to 9\nmove 2 from 6 to 4\nmove 18 from 9 to 4\nmove 3 from 7 to 2\nmove 15 from 1 to 4\nmove 2 from 1 to 8\nmove 5 from 3 to 1\nmove 1 from 3 to 6\nmove 5 from 8 to 9\nmove 3 from 9 to 5\nmove 1 from 9 to 5\nmove 1 from 8 to 9\nmove 1 from 6 to 2\nmove 3 from 9 to 4\nmove 2 from 6 to 7\nmove 30 from 4 to 6\nmove 22 from 6 to 9\nmove 6 from 9 to 4\nmove 4 from 6 to 7\nmove 1 from 1 to 6\nmove 1 from 9 to 8\nmove 1 from 7 to 6\nmove 3 from 5 to 3\nmove 5 from 6 to 5\nmove 2 from 7 to 9\nmove 4 from 1 to 5\nmove 1 from 6 to 4\nmove 1 from 8 to 7\nmove 2 from 6 to 4\nmove 17 from 9 to 8\nmove 2 from 2 to 7\nmove 2 from 3 to 1\nmove 8 from 4 to 8\nmove 1 from 3 to 8\nmove 8 from 4 to 2\nmove 2 from 1 to 2\nmove 1 from 4 to 6\nmove 4 from 7 to 1\nmove 1 from 6 to 8\nmove 19 from 8 to 3\nmove 5 from 5 to 1\nmove 5 from 5 to 9\nmove 2 from 9 to 3\nmove 6 from 1 to 9\nmove 1 from 7 to 5\nmove 1 from 7 to 4\nmove 2 from 5 to 7\nmove 2 from 2 to 4\nmove 4 from 9 to 8\nmove 12 from 8 to 7\nmove 2 from 1 to 9\nmove 1 from 7 to 4\nmove 4 from 4 to 5\nmove 3 from 9 to 3\nmove 9 from 2 to 6\nmove 2 from 7 to 5\nmove 1 from 1 to 9\nmove 5 from 9 to 7\nmove 9 from 6 to 2\nmove 6 from 2 to 8\nmove 21 from 3 to 2\nmove 12 from 2 to 9\nmove 3 from 5 to 9\nmove 3 from 3 to 8\nmove 5 from 9 to 6\nmove 13 from 2 to 3\nmove 3 from 6 to 2\nmove 10 from 9 to 8\nmove 6 from 3 to 1\nmove 3 from 2 to 9\nmove 2 from 6 to 7\nmove 5 from 3 to 9\nmove 4 from 1 to 9\nmove 3 from 8 to 5\nmove 1 from 1 to 7\nmove 6 from 5 to 7\nmove 12 from 9 to 7\nmove 1 from 1 to 8\nmove 11 from 8 to 5\nmove 9 from 5 to 7\nmove 1 from 3 to 1\nmove 4 from 8 to 7\nmove 1 from 1 to 7\nmove 2 from 8 to 3\nmove 42 from 7 to 4\nmove 3 from 7 to 9\nmove 4 from 7 to 5\nmove 1 from 7 to 8\nmove 1 from 8 to 5\nmove 1 from 7 to 5\nmove 1 from 3 to 4\nmove 1 from 3 to 9\nmove 1 from 9 to 6\nmove 1 from 6 to 4\nmove 1 from 3 to 5\nmove 3 from 9 to 2\nmove 16 from 4 to 8\nmove 3 from 2 to 4\nmove 1 from 5 to 4\nmove 30 from 4 to 6\nmove 15 from 8 to 3\nmove 2 from 4 to 5\nmove 1 from 8 to 7\nmove 13 from 3 to 6\nmove 1 from 7 to 8\nmove 1 from 3 to 8\nmove 1 from 3 to 8\nmove 4 from 5 to 2\nmove 6 from 5 to 2\nmove 2 from 8 to 6\nmove 43 from 6 to 2\nmove 1 from 6 to 1\nmove 18 from 2 to 4\nmove 24 from 2 to 6\nmove 19 from 6 to 3\nmove 4 from 6 to 3\nmove 2 from 6 to 3\nmove 3 from 3 to 2\nmove 1 from 1 to 3\nmove 23 from 3 to 6\nmove 12 from 4 to 3\nmove 7 from 3 to 9\nmove 13 from 2 to 9\nmove 1 from 8 to 4\nmove 4 from 3 to 8\nmove 6 from 4 to 2\nmove 10 from 9 to 3\nmove 6 from 2 to 9\nmove 8 from 3 to 5\nmove 3 from 5 to 3\nmove 13 from 6 to 5\nmove 4 from 3 to 9\nmove 1 from 4 to 2\nmove 4 from 8 to 3\nmove 1 from 2 to 5\nmove 14 from 9 to 5\nmove 2 from 5 to 4\nmove 2 from 4 to 3\nmove 1 from 9 to 5\nmove 4 from 6 to 1\nmove 1 from 6 to 2\nmove 6 from 3 to 2\nmove 5 from 6 to 8\nmove 2 from 3 to 7\nmove 1 from 8 to 1\nmove 25 from 5 to 7\nmove 3 from 7 to 9\nmove 5 from 2 to 9\nmove 12 from 9 to 8\nmove 3 from 1 to 6\nmove 16 from 8 to 2\nmove 1 from 9 to 2\nmove 1 from 6 to 2\nmove 1 from 1 to 3\nmove 21 from 7 to 3\nmove 2 from 7 to 1\nmove 1 from 7 to 8\nmove 2 from 2 to 1\nmove 2 from 6 to 3\nmove 18 from 2 to 9\nmove 2 from 5 to 1\nmove 1 from 2 to 1\nmove 3 from 5 to 2\nmove 13 from 9 to 1\nmove 3 from 9 to 2\nmove 1 from 8 to 7\nmove 3 from 2 to 6\nmove 2 from 5 to 1\nmove 17 from 3 to 8\nmove 3 from 3 to 8\nmove 2 from 9 to 1\nmove 1 from 7 to 5\nmove 1 from 5 to 3\nmove 2 from 6 to 4\nmove 1 from 6 to 1\nmove 15 from 8 to 2\nmove 2 from 3 to 6\nmove 1 from 8 to 5\nmove 2 from 6 to 8\nmove 13 from 2 to 9\nmove 4 from 9 to 8\nmove 9 from 8 to 9\nmove 3 from 3 to 4\nmove 4 from 9 to 7\nmove 1 from 8 to 6\nmove 1 from 7 to 5\nmove 2 from 5 to 1\nmove 1 from 6 to 3\nmove 4 from 4 to 5\nmove 1 from 4 to 6\nmove 1 from 3 to 7\nmove 1 from 5 to 6\nmove 2 from 7 to 2\nmove 4 from 2 to 3\nmove 3 from 2 to 7\nmove 1 from 3 to 6\nmove 1 from 9 to 6\nmove 2 from 5 to 2\nmove 3 from 9 to 5\nmove 1 from 6 to 1\nmove 3 from 5 to 4\nmove 12 from 1 to 2\nmove 2 from 2 to 4\nmove 2 from 7 to 8\nmove 2 from 3 to 9\nmove 1 from 4 to 7\nmove 1 from 5 to 2\nmove 1 from 8 to 3\nmove 2 from 3 to 6\nmove 7 from 2 to 8\nmove 3 from 4 to 1\nmove 7 from 8 to 5\nmove 7 from 9 to 2\nmove 1 from 4 to 5\nmove 3 from 7 to 6\nmove 5 from 6 to 9\nmove 6 from 9 to 5\nmove 4 from 9 to 6\nmove 1 from 8 to 5\nmove 1 from 7 to 4\nmove 1 from 4 to 2\nmove 2 from 2 to 9\nmove 2 from 9 to 2\nmove 11 from 5 to 3\nmove 2 from 5 to 2\nmove 1 from 2 to 9\nmove 4 from 6 to 9\nmove 1 from 2 to 9\nmove 4 from 3 to 7\nmove 3 from 6 to 4\nmove 1 from 5 to 7\nmove 18 from 1 to 3\nmove 11 from 3 to 2\nmove 1 from 7 to 9\nmove 1 from 5 to 9\nmove 14 from 3 to 6\nmove 15 from 2 to 4\nmove 5 from 2 to 5\nmove 1 from 2 to 5\nmove 1 from 1 to 9\nmove 8 from 4 to 1\nmove 5 from 5 to 9\nmove 9 from 4 to 9\nmove 4 from 7 to 4\nmove 5 from 4 to 8\nmove 2 from 9 to 6\nmove 8 from 1 to 8\nmove 1 from 5 to 3\nmove 1 from 3 to 4\nmove 1 from 1 to 8\nmove 13 from 6 to 3\nmove 9 from 9 to 5\nmove 1 from 2 to 8\nmove 8 from 5 to 1\nmove 1 from 2 to 7"
  )
}
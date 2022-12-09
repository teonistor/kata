package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _09Test extends AdventOfCodeTestBase {
  testAndRun(_09,
    "R 4\nU 4\nL 3\nD 1\nR 4\nD 1\nL 5\nR 2",
    13,
    1,
    "R 1\nU 2\nR 1\nL 2\nD 2\nU 1\nR 2\nU 1\nL 2\nU 1\nD 2\nR 1\nD 1\nL 1\nU 2\nD 2\nL 2\nD 1\nU 2\nD 2\nL 1\nU 1\nL 2\nD 2\nU 2\nL 1\nU 2\nL 1\nR 2\nU 1\nL 1\nR 2\nD 1\nU 2\nR 1\nU 1\nR 2\nU 1\nR 2\nU 1\nD 1\nU 1\nD 1\nL 2\nD 2\nR 1\nU 1\nR 1\nU 1\nL 1\nU 1\nD 2\nR 2\nD 2\nL 2\nU 2\nR 1\nD 1\nU 1\nD 2\nR 1\nD 2\nL 2\nR 2\nL 1\nU 1\nL 2\nD 2\nL 2\nD 1\nR 1\nL 2\nD 2\nR 1\nL 1\nR 1\nD 1\nR 1\nD 2\nL 2\nU 1\nD 2\nR 2\nD 1\nU 1\nL 1\nD 1\nU 2\nR 1\nU 1\nL 2\nU 1\nL 1\nU 2\nL 2\nD 2\nU 1\nL 1\nU 2\nD 2\nL 2\nD 2\nR 2\nU 2\nL 2\nD 1\nU 1\nR 2\nD 2\nL 2\nR 2\nD 2\nL 1\nD 1\nU 2\nR 2\nD 3\nR 1\nU 3\nL 3\nD 2\nR 1\nD 1\nL 2\nD 1\nR 2\nU 3\nL 2\nU 2\nR 3\nU 3\nL 1\nD 3\nR 3\nU 2\nR 3\nU 3\nL 1\nD 3\nR 3\nL 2\nR 2\nL 3\nD 3\nR 2\nD 2\nR 2\nD 1\nR 1\nL 1\nD 2\nR 1\nD 2\nU 1\nR 2\nL 2\nR 2\nU 2\nR 2\nD 1\nU 1\nD 1\nL 3\nU 3\nD 3\nL 1\nU 2\nR 2\nU 2\nR 1\nU 1\nL 2\nU 2\nR 3\nU 1\nD 3\nR 3\nU 2\nR 1\nU 1\nD 3\nL 2\nU 3\nL 1\nR 2\nD 3\nR 2\nU 1\nR 2\nD 1\nU 3\nL 2\nD 1\nR 1\nL 3\nR 3\nD 2\nR 1\nU 2\nR 3\nD 2\nU 2\nL 2\nU 1\nR 2\nU 2\nL 2\nD 3\nL 2\nD 1\nL 1\nU 2\nL 1\nD 2\nR 1\nD 2\nR 1\nL 2\nU 1\nL 1\nU 1\nD 2\nR 4\nD 4\nL 2\nR 4\nD 1\nL 2\nD 2\nL 4\nU 4\nD 4\nL 3\nR 3\nD 1\nL 1\nD 1\nU 4\nR 4\nU 4\nD 2\nR 1\nU 3\nR 2\nU 3\nR 4\nD 3\nU 3\nL 4\nD 1\nR 2\nU 3\nD 4\nU 3\nR 2\nL 1\nD 2\nU 2\nR 3\nU 2\nR 2\nD 2\nL 2\nU 4\nR 1\nL 3\nR 1\nL 1\nD 3\nR 4\nD 2\nR 4\nU 3\nL 1\nD 1\nR 4\nU 3\nD 1\nL 3\nR 1\nL 3\nD 2\nL 2\nR 3\nL 4\nD 4\nU 1\nL 1\nR 1\nD 3\nL 2\nR 1\nD 3\nR 2\nD 4\nU 4\nR 3\nU 1\nD 3\nL 3\nD 3\nU 1\nD 2\nU 4\nR 4\nU 2\nD 1\nR 1\nD 4\nL 2\nR 3\nU 4\nL 1\nD 1\nU 4\nL 3\nD 4\nU 2\nR 4\nU 2\nR 4\nU 2\nL 1\nR 1\nL 3\nD 4\nL 2\nR 2\nL 3\nU 3\nR 4\nL 2\nR 1\nD 5\nU 1\nR 1\nU 2\nR 1\nU 4\nL 3\nU 4\nR 2\nD 3\nL 4\nR 3\nL 2\nU 2\nR 1\nL 3\nD 5\nR 3\nL 2\nU 5\nR 2\nU 5\nL 5\nU 4\nR 4\nD 2\nR 4\nD 3\nR 2\nD 3\nU 2\nL 3\nD 3\nL 1\nU 4\nD 3\nL 1\nD 1\nR 2\nL 4\nR 3\nU 1\nR 3\nL 2\nR 3\nD 4\nR 2\nU 3\nL 4\nR 5\nL 1\nU 1\nD 5\nU 2\nR 5\nL 4\nD 3\nL 1\nD 3\nU 1\nR 2\nD 1\nL 5\nD 2\nR 4\nL 1\nD 2\nR 4\nL 3\nR 3\nL 3\nR 4\nL 3\nU 3\nD 4\nU 2\nR 1\nD 1\nU 1\nR 5\nD 5\nL 5\nR 3\nU 2\nD 4\nL 5\nD 1\nL 4\nR 4\nU 2\nL 2\nR 2\nU 3\nL 1\nU 3\nL 1\nD 4\nU 5\nD 5\nR 4\nL 4\nR 5\nD 4\nU 5\nL 4\nU 1\nD 4\nR 5\nD 2\nU 4\nR 2\nU 3\nD 2\nU 6\nD 4\nR 1\nD 4\nR 3\nL 1\nR 5\nU 6\nD 5\nL 4\nR 3\nL 4\nR 5\nD 1\nR 3\nD 2\nL 6\nU 5\nR 6\nL 2\nD 6\nU 1\nL 3\nD 4\nR 6\nD 3\nL 3\nR 5\nU 6\nD 5\nU 1\nR 4\nL 4\nU 4\nR 5\nD 1\nL 4\nU 6\nL 2\nR 3\nL 2\nR 3\nL 3\nR 4\nU 3\nD 1\nR 6\nD 5\nL 2\nR 4\nU 4\nD 2\nL 1\nR 2\nD 3\nL 4\nD 4\nR 4\nD 6\nR 1\nL 2\nR 5\nD 3\nU 5\nR 4\nL 6\nD 6\nU 4\nR 4\nL 4\nU 4\nR 5\nD 1\nU 1\nR 4\nL 2\nU 6\nD 3\nL 3\nU 6\nD 1\nU 4\nD 6\nR 6\nU 1\nR 2\nU 5\nD 4\nL 2\nU 5\nD 1\nL 5\nU 1\nR 3\nD 2\nR 1\nL 3\nD 1\nR 1\nL 2\nR 6\nD 6\nR 4\nU 4\nL 5\nR 6\nL 5\nR 4\nD 3\nL 2\nR 4\nL 2\nU 6\nD 5\nR 1\nL 3\nR 1\nU 2\nR 6\nL 2\nD 7\nL 2\nD 4\nR 2\nU 6\nD 4\nR 5\nU 6\nR 3\nL 3\nU 2\nR 3\nU 1\nR 7\nU 2\nR 2\nD 7\nR 1\nU 5\nD 1\nL 5\nU 5\nR 2\nD 5\nR 4\nU 5\nL 3\nD 4\nL 6\nR 7\nL 2\nD 3\nL 2\nD 6\nU 6\nL 1\nD 7\nU 6\nL 7\nR 5\nL 1\nU 7\nR 1\nL 4\nR 4\nL 5\nU 7\nD 4\nR 7\nL 5\nU 7\nL 6\nU 3\nD 5\nR 5\nU 7\nR 5\nL 3\nU 6\nR 2\nU 3\nR 3\nU 6\nL 5\nD 6\nL 4\nD 5\nR 1\nL 3\nU 6\nR 6\nU 6\nL 1\nD 2\nL 5\nD 1\nL 6\nU 5\nD 5\nR 5\nD 6\nL 4\nU 5\nR 2\nU 4\nD 7\nL 1\nU 1\nD 1\nL 1\nD 4\nU 1\nL 6\nU 1\nR 5\nD 1\nU 1\nR 1\nU 5\nR 4\nU 5\nL 2\nU 6\nL 7\nR 2\nU 4\nR 5\nL 1\nR 8\nL 2\nD 7\nR 7\nD 2\nL 2\nR 2\nD 2\nU 7\nD 7\nR 4\nU 1\nR 2\nL 6\nU 1\nL 3\nU 2\nD 7\nR 6\nL 5\nD 1\nL 3\nU 6\nD 2\nU 2\nR 4\nD 1\nL 7\nU 3\nD 8\nU 4\nD 8\nL 8\nU 6\nL 3\nU 4\nR 4\nU 8\nD 1\nR 7\nU 5\nL 8\nU 4\nL 4\nD 7\nU 8\nL 2\nD 8\nL 1\nR 1\nL 1\nR 8\nL 8\nR 5\nL 1\nR 4\nD 3\nR 2\nU 8\nL 6\nR 4\nL 3\nR 8\nL 4\nD 5\nR 3\nU 2\nD 8\nU 4\nR 6\nD 4\nR 3\nL 7\nD 8\nL 2\nU 6\nD 7\nR 3\nL 5\nR 4\nD 3\nL 4\nR 8\nL 7\nD 8\nR 3\nU 3\nL 3\nR 6\nL 1\nU 3\nL 1\nU 4\nR 8\nL 8\nR 2\nD 3\nL 2\nR 1\nD 2\nR 6\nU 3\nD 1\nU 3\nR 1\nU 2\nR 2\nL 2\nD 6\nU 7\nL 8\nD 6\nU 2\nR 3\nL 6\nU 9\nD 6\nU 1\nD 3\nU 4\nR 3\nL 9\nD 6\nU 9\nD 1\nL 5\nU 5\nD 6\nU 4\nL 8\nU 8\nD 3\nL 3\nU 9\nR 5\nL 7\nD 1\nR 9\nU 8\nD 1\nR 1\nU 9\nD 4\nR 6\nU 8\nR 6\nU 3\nD 2\nU 8\nL 8\nU 9\nD 4\nL 3\nU 4\nD 3\nL 9\nR 7\nL 9\nR 3\nL 5\nD 7\nL 8\nU 9\nL 7\nD 1\nL 6\nU 7\nL 6\nD 8\nU 9\nL 7\nR 7\nD 1\nR 6\nD 4\nU 7\nR 7\nL 8\nD 4\nR 1\nL 7\nD 2\nU 3\nD 1\nR 7\nD 7\nR 7\nL 6\nD 9\nU 7\nD 5\nR 7\nL 1\nR 9\nU 1\nL 2\nU 5\nR 4\nD 7\nR 2\nD 5\nU 4\nD 3\nR 9\nU 2\nD 9\nU 1\nR 9\nU 9\nD 1\nL 7\nU 6\nD 6\nU 9\nL 5\nU 2\nD 6\nL 4\nD 3\nU 1\nR 2\nL 1\nR 1\nD 1\nR 1\nU 1\nR 4\nL 7\nR 2\nD 1\nU 3\nD 3\nR 10\nL 7\nR 7\nD 2\nL 6\nR 9\nU 9\nD 8\nU 9\nR 2\nU 6\nD 9\nU 1\nL 2\nR 10\nL 9\nR 10\nL 6\nR 4\nD 4\nR 4\nU 4\nD 4\nL 5\nR 8\nD 4\nL 7\nD 6\nL 8\nR 9\nL 8\nD 7\nR 2\nU 2\nD 5\nL 4\nR 8\nD 7\nR 6\nD 7\nL 5\nU 7\nR 1\nD 7\nR 9\nD 5\nU 3\nL 8\nD 7\nU 4\nD 4\nR 10\nU 7\nD 4\nU 2\nR 7\nL 4\nU 2\nD 9\nL 5\nR 2\nU 10\nL 9\nR 10\nL 1\nD 5\nR 10\nL 5\nR 5\nD 5\nR 10\nL 4\nU 9\nD 7\nR 10\nL 6\nU 3\nD 5\nU 6\nL 6\nU 10\nL 1\nU 10\nD 4\nL 7\nU 2\nL 10\nR 4\nL 7\nR 4\nD 10\nL 4\nU 10\nL 10\nD 9\nU 4\nR 8\nU 8\nR 5\nU 3\nD 10\nR 7\nL 10\nR 9\nL 1\nD 6\nL 11\nD 11\nR 3\nL 8\nU 6\nR 9\nD 5\nR 9\nD 4\nU 7\nD 4\nR 4\nU 10\nD 4\nR 2\nL 3\nU 5\nD 11\nR 8\nL 8\nU 7\nR 3\nD 8\nU 5\nR 10\nD 5\nR 2\nU 11\nD 10\nR 1\nU 9\nL 10\nR 2\nD 11\nR 6\nL 3\nR 10\nU 2\nD 6\nL 10\nU 5\nL 6\nU 1\nR 10\nU 8\nR 8\nD 4\nU 8\nL 11\nR 7\nL 2\nD 2\nR 5\nU 10\nL 3\nR 6\nL 8\nR 8\nU 8\nD 3\nL 7\nR 5\nL 6\nD 11\nL 6\nD 7\nU 6\nD 3\nU 9\nL 7\nR 8\nL 3\nR 10\nD 11\nL 11\nR 4\nD 6\nL 7\nD 9\nR 10\nU 11\nL 9\nD 8\nL 2\nU 1\nD 7\nU 10\nD 11\nR 5\nD 11\nL 6\nD 5\nR 6\nD 5\nR 11\nU 3\nD 6\nR 5\nU 7\nD 2\nR 6\nU 5\nD 9\nU 3\nR 7\nD 10\nU 5\nD 4\nR 6\nU 11\nL 10\nU 5\nL 6\nR 5\nU 7\nL 7\nR 5\nD 9\nU 11\nL 7\nU 8\nL 7\nU 3\nL 8\nR 10\nU 11\nR 8\nL 12\nU 8\nD 2\nL 2\nD 2\nU 10\nR 11\nU 2\nR 6\nU 3\nD 9\nU 5\nD 12\nL 11\nD 8\nU 12\nR 4\nU 1\nL 2\nR 10\nD 6\nL 10\nU 2\nR 4\nL 4\nD 8\nU 9\nL 4\nD 10\nU 5\nR 10\nL 2\nD 2\nU 2\nR 7\nD 8\nR 1\nU 11\nD 1\nL 7\nR 12\nD 12\nR 3\nL 11\nR 10\nU 11\nR 5\nD 6\nR 11\nD 2\nL 3\nD 12\nL 9\nR 6\nU 1\nD 2\nR 6\nD 5\nU 7\nR 2\nD 5\nU 3\nD 12\nR 12\nL 9\nR 9\nD 12\nR 8\nD 11\nL 7\nU 4\nD 11\nR 1\nU 9\nL 1\nD 2\nR 3\nU 1\nD 1\nR 2\nD 11\nU 12\nR 7\nD 1\nU 13\nL 5\nD 13\nL 10\nD 12\nL 7\nD 9\nL 7\nU 6\nL 1\nR 1\nL 10\nR 7\nL 6\nU 7\nR 10\nU 7\nR 11\nU 12\nL 2\nD 11\nL 5\nU 2\nD 8\nL 10\nD 13\nU 10\nL 9\nU 5\nD 11\nR 6\nL 9\nR 2\nL 6\nU 4\nR 7\nD 11\nL 3\nU 6\nD 13\nU 2\nR 13\nU 11\nL 12\nD 13\nL 1\nR 9\nD 10\nU 6\nL 13\nU 2\nL 12\nD 7\nR 1\nL 6\nD 1\nU 10\nD 5\nL 2\nU 12\nR 1\nU 9\nD 4\nU 10\nL 1\nR 1\nU 8\nR 2\nD 1\nL 6\nU 11\nD 2\nU 7\nD 11\nU 9\nD 6\nU 5\nD 13\nR 2\nU 8\nL 4\nU 4\nR 2\nU 1\nD 3\nL 1\nD 13\nR 9\nL 4\nD 5\nL 7\nU 2\nD 10\nL 6\nU 11\nD 1\nU 5\nR 4\nD 9\nR 12\nD 2\nU 9\nL 10\nU 6\nD 2\nL 8\nU 4\nL 4\nD 12\nU 3\nL 14\nD 12\nR 12\nL 7\nU 5\nL 2\nD 4\nL 13\nD 10\nL 8\nD 12\nR 13\nU 8\nD 14\nU 4\nR 11\nL 4\nR 13\nL 2\nU 1\nD 10\nL 12\nD 13\nL 4\nD 11\nR 14\nU 8\nL 11\nD 2\nU 8\nL 8\nD 3\nL 14\nU 7\nL 2\nR 2\nD 6\nU 1\nL 12\nR 12\nU 4\nL 1\nU 13\nR 12\nD 8\nU 10\nR 2\nL 7\nR 4\nD 4\nL 14\nU 2\nD 2\nU 11\nL 7\nR 12\nU 9\nL 11\nR 6\nD 6\nR 8\nL 1\nD 9\nL 7\nD 10\nR 6\nD 6\nL 5\nD 14\nL 12\nU 12\nR 4\nD 14\nL 10\nD 11\nL 3\nD 3\nU 9\nL 6\nD 10\nU 1\nL 10\nD 9\nR 8\nL 3\nU 7\nR 4\nU 9\nL 5\nR 7\nD 7\nL 5\nU 4\nR 4\nL 7\nR 8\nL 2\nR 5\nL 10\nR 2\nL 12\nD 12\nL 6\nR 4\nL 11\nD 13\nL 13\nR 10\nD 12\nU 5\nR 11\nL 15\nR 6\nU 9\nD 5\nU 6\nL 1\nD 1\nR 7\nD 5\nR 15\nU 11\nR 5\nD 15\nR 13\nU 8\nR 11\nU 7\nD 4\nL 6\nD 11\nU 1\nL 2\nR 11\nL 12\nD 5\nR 3\nL 14\nR 15\nL 14\nU 13\nD 9\nL 3\nD 4\nU 9\nL 3\nR 14\nU 3\nR 15\nL 14\nR 4\nU 15\nL 1\nD 11\nL 13\nD 14\nL 1\nD 1\nU 13\nD 7\nU 13\nD 15\nU 15\nL 6\nD 7\nR 15\nL 10\nU 6\nR 7\nL 13\nU 3\nD 9\nL 3\nD 10\nR 14\nD 15\nL 6\nD 4\nU 11\nL 11\nD 6\nU 8\nD 15\nR 5\nL 9\nD 15\nU 15\nR 9\nD 11\nU 7\nL 9\nU 8\nD 2\nL 4\nD 11\nU 12\nR 11\nU 5\nL 12\nU 5\nR 14\nL 10\nR 6\nD 3\nL 13\nU 6\nR 10\nD 10\nU 9\nD 3\nR 7\nL 13\nR 15\nU 6\nL 13\nU 11\nD 9\nL 7\nR 8\nU 6\nR 12\nU 2\nL 8\nU 10\nD 11\nU 14\nR 3\nL 15\nU 9\nL 2\nU 2\nD 11\nL 11\nR 3\nD 14\nU 3\nL 1\nU 2\nD 12\nL 5\nR 5\nD 10\nL 16\nR 4\nU 3\nL 6\nR 9\nL 7\nR 2\nU 15\nR 6\nL 12\nD 16\nL 16\nD 15\nU 12\nL 6\nD 9\nU 8\nL 10\nD 11\nU 4\nD 12\nU 7\nD 14\nU 1\nD 10\nR 10\nD 14\nL 5\nD 6\nR 8\nL 16\nU 7\nL 12\nR 13\nL 11\nD 12\nR 5\nD 11\nL 5\nR 4\nD 15\nL 13\nD 10\nR 2\nL 16\nD 14\nR 15\nD 11\nU 2\nD 12\nL 11\nD 1\nR 5\nD 6\nU 13\nL 10\nR 1\nU 10\nD 12\nL 10\nD 10\nR 11\nL 10\nD 11\nL 3\nR 11\nD 12\nL 6\nU 2\nL 10\nD 9\nL 11\nU 12\nD 14\nL 6\nU 16\nD 2\nL 2\nD 14\nU 7\nR 12\nD 14\nL 4\nU 3\nR 16\nD 2\nL 5\nR 10\nL 1\nR 11\nL 14\nD 3\nL 17\nD 4\nU 1\nR 8\nD 8\nL 5\nU 1\nL 13\nD 15\nU 17\nL 1\nD 14\nU 4\nR 1\nU 2\nD 14\nR 10\nU 14\nD 17\nR 10\nU 13\nD 12\nU 8\nL 11\nU 17\nR 12\nU 6\nD 8\nR 10\nU 16\nD 4\nR 3\nD 3\nU 4\nL 3\nU 5\nR 16\nL 5\nR 16\nD 17\nR 12\nL 11\nR 13\nU 12\nL 8\nR 5\nD 6\nU 16\nL 16\nD 2\nL 11\nD 1\nR 1\nD 14\nR 15\nL 6\nU 14\nR 12\nD 11\nL 9\nU 6\nL 11\nD 15\nU 17\nL 1\nU 3\nL 6\nU 5\nR 16\nL 14\nR 9\nL 12\nD 14\nU 13\nL 3\nU 13\nD 7\nR 2\nU 7\nD 5\nL 17\nD 6\nL 16\nR 5\nD 4\nR 3\nU 11\nD 3\nU 9\nD 10\nU 2\nL 6\nD 6\nR 15\nD 17\nR 1\nL 15\nD 2\nU 5\nR 2\nD 6\nL 8\nR 5\nL 2\nD 17\nU 9\nD 9\nL 7\nR 12\nL 12\nR 18\nL 10\nR 9\nD 6\nR 9\nU 15\nL 17\nD 18\nR 1\nL 15\nD 4\nR 6\nU 3\nR 6\nU 3\nD 1\nU 9\nD 8\nR 3\nD 6\nU 3\nR 8\nL 8\nR 10\nU 7\nD 16\nU 17\nR 11\nU 8\nD 1\nU 8\nL 4\nR 12\nD 10\nU 7\nD 18\nU 7\nD 12\nL 18\nU 9\nR 2\nU 5\nD 14\nR 13\nD 15\nU 17\nD 16\nL 4\nD 10\nL 2\nU 7\nL 7\nD 13\nR 13\nU 12\nR 16\nU 17\nR 3\nU 12\nR 6\nD 12\nU 11\nL 6\nU 15\nL 12\nR 1\nD 6\nL 16\nR 1\nD 7\nR 17\nL 15\nD 15\nU 9\nR 18\nD 13\nR 14\nD 1\nR 1\nD 16\nL 18\nR 1\nU 14\nL 8\nD 14\nR 15\nD 2\nU 10\nD 17\nU 3\nD 9\nR 7\nD 17\nU 3\nD 15\nL 17\nD 17\nR 1\nD 10\nL 9\nD 14\nR 5\nU 14\nR 14\nD 12\nR 18\nU 4\nD 2\nU 17\nD 12\nL 4\nU 9\nL 9\nU 17\nR 4\nU 17\nR 8\nD 10\nR 3\nD 9\nU 17\nL 5\nR 16\nU 9\nL 5\nD 15\nU 2\nD 7\nL 15\nU 1\nL 16\nR 8\nL 13\nR 3\nD 8\nR 1\nD 8\nL 10\nU 16\nR 16\nL 2\nU 3\nD 9\nU 12\nD 1\nL 10\nD 15\nU 7\nR 3\nD 15\nU 4\nL 6\nR 14\nU 13\nR 16\nU 2\nR 9\nU 15\nD 8\nL 8\nR 17\nD 3\nL 17\nD 13\nU 8\nD 11\nR 13\nL 3\nD 2\nU 14\nR 1\nU 10\nR 11\nD 16\nL 10\nR 17\nU 7\nL 13\nR 3\nL 18\nR 5\nU 7\nL 12\nD 4\nU 5\nL 17\nR 18\nL 4\nR 19\nL 12\nU 17\nR 2\nL 11\nR 9\nD 16\nR 19\nL 18\nU 11\nD 4\nR 13\nL 18\nU 13\nR 19\nD 3\nR 10\nL 3\nR 5\nD 2\nR 2\nL 18\nD 15\nR 19\nL 5\nD 4\nU 5\nR 12\nD 15\nL 13\nR 16\nU 16\nL 6\nU 12\nL 8"
  )

  test("medium example") {
    assert(_09._2("R 5\nU 8\nL 8\nD 3\nR 17\nD 10\nL 25\nU 20") == 36)
  }

  test("moveTowards") {
    assert(_09.moveTowards((2, 2), (3, 3)) == (3, 3))
    assert(_09.moveTowards((2, 2), (2, 3)) == (2, 3))
    assert(_09.moveTowards((2, 2), (2, 4)) == (2, 3))
    assert(_09.moveTowards((2, 2), (0, 2)) == (1, 2))
    assert(_09.moveTowards((2, 2), (2, 0)) == (2, 1))
    assert(_09.moveTowards((6, 7), (5, 5)) == (6, 6))
    assert(_09.moveTowards((6, 7), (8, 8)) == (7, 7))
    assert(_09.moveTowards((8, 8), (7, 7)) == (7, 7))
  }
}

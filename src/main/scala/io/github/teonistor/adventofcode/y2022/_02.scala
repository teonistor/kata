package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _02 extends StandardAdventOfCodeSolution[Int] {

  def _1(input: String): Int =
    input.split("\n").map {
      case "A X" => 1 + 3
      case "A Y" => 2 + 6
      case "A Z" => 3
      case "B X" => 1
      case "B Y" => 2 + 3
      case "B Z" => 3 + 6
      case "C X" => 1 + 6
      case "C Y" => 2
      case "C Z" => 3 + 3
    } .sum

  def _2(input: String): Int =
    input.split("\n").map {
      case "A X" => 3
      case "A Y" => 1 + 3
      case "A Z" => 2 + 6
      case "B X" => 1
      case "B Y" => 2 + 3
      case "B Z" => 3 + 6
      case "C X" => 2
      case "C Y" => 3 + 3
      case "C Z" => 1 + 6
    } .sum
}

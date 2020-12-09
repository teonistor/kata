package io.github.teonistor.adventofcode.y2021

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _02 extends StandardAdventOfCodeSolution[Long] {
  private val forward = "forward (\\d+)".r
  private val down = "down (\\d+)".r
  private val up = "up (\\d+)".r

  def _1(input: String): Long = {
    val numbers = input.split("\n").to(LazyList)
      .map {
        case forward(num) => (num.toLong, 0L)
        case down(num) => (0L, num.toLong)
        case up(num) => (0L, -num.toLong)
      }.unzip
    numbers._1.sum * numbers._2.sum
  }

  def _2(input: String): Long = {
    val instructions = input.split("\n").to(LazyList)
    recursively_2(instructions)
  }

  @tailrec
  private def recursively_2(instructions: Seq[String], aim: Int = 0, horizontal: Int = 0, vertical: Int = 0): Int =
    if (instructions.isEmpty)
      horizontal * vertical
    else
      instructions.head match {
        case forward(num) => recursively_2(instructions.tail, aim, horizontal + num.toInt, vertical + aim * num.toInt)
        case down(num) => recursively_2(instructions.tail, aim + num.toInt, horizontal, vertical)
        case up(num) => recursively_2(instructions.tail, aim - num.toInt, horizontal, vertical)
      }
}

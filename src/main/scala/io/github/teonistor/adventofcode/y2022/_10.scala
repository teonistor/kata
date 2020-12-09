package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.abs
import scala.annotation.tailrec

object _10 extends StandardAdventOfCodeSolution[String] {
  private val addx = "addx (.+)".r
  private val noop = "noop".r

  def _1(input: String): String =
    solve(input)._1.toString

  def _2(input: String): String = {
    val result = solve(input)
      ._2
      .grouped(40)
      .mkString("\n")
    println(result.replace('.', ' '))
    result
  }

  private def solve(input: String) =
    iterate(1, 1,
      input.split("\n").to(LazyList)
        .flatMap {
          case addx(x) => List(0, x.toInt)
          case noop() => Some(0)
        },
      List(20, 60, 100, 140, 180, 220))

  @tailrec
  private def iterate(cycle:Int, strength:Int, deltas:Seq[Int], interesting:Seq[Int],
                      crt: Vector[Char] = Vector.empty,
                      result: Int       = 0): (Int, String) =
    if (cycle > 240)
      (result, crt.mkString)
    else if (interesting.isEmpty || cycle != interesting.head)
      iterate(cycle + 1, strength + deltas.head, deltas.tail, interesting, crt.appended(pixel(cycle, strength)), result)
    else
      iterate(cycle + 1, strength + deltas.head, deltas.tail, interesting.tail, crt.appended(pixel(cycle, strength)), result + cycle * strength)

  private def pixel(cycle: Int, strength: Int) =
    if (abs((cycle - 1) % 40 - strength) < 2) '#'
    else '.'
}

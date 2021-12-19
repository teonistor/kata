package io.github.teonistor.adventofcode.y2021

import scala.annotation.tailrec
import scala.collection.mutable

object _14 {

  private val insertionRule = "([A-Z]{2}) -> ([A-Z])".r

  def _1(input: String): Long = {
    solve(input, 10)
  }

  def _2(input: String): Long = {
    solve(input, 40)
  }

  private def solve(input: String, steps:Int): Long = {
    val templateAndRules = input.split("\n\n")

    val rules = templateAndRules(1).split("\n").to(LazyList)
      .map { case insertionRule(k, v) => (k, v)}
      .toMap

    val finalPolymer = solveRecursively(templateAndRules(0).to(mutable.Queue).map(_.toString), rules, steps)
    val counts = finalPolymer.groupMapReduce(identity)(_=>1)(_+_).values
    counts.max - counts.min
  }

  @tailrec
  private def solveRecursively(polymer: mutable.Queue[String], rules: Map[String,String], steps:Int): String ={
    println(s"steps remaining: $steps")

    if (steps == 0)
      polymer.mkString

    else {
      val end = polymer.size - 2
      (0 to end).map(_*2)
        .foreach(i => polymer.insert(i+1, rules(polymer(i) + polymer(i+1))))

      solveRecursively(polymer, rules, steps-1)
    }
  }
}

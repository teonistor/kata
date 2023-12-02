package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.max

object _02 extends StandardAdventOfCodeSolution[Int] {

  private val line = "Game (\\d+): (.+)".r
  private val red = "(\\d+) red".r
  private val green = "(\\d+) green".r
  private val blue = "(\\d+) blue".r

  private val maxRed = 12
  private val maxGreen = 13
  private val maxBlue = 14

  override def _1(input: String): Int =
    input.split("\n").to(LazyList).flatMap {
      case line(game, cubes) => Option(game).filter(_=>
        cubes.split(";").to(LazyList)
          .map(_.strip())
          .forall(oneGame => oneGame.split(",").to(LazyList)
            .map(_.strip())
            .forall {
              case red(count) => count.toInt <= maxRed
              case green(count) => count.toInt <= maxGreen
              case blue(count) => count.toInt <= maxBlue
            }
          )
        )
      }
      .map(_.toInt)
      .sum

  override def _2(input: String): Int =
    input.split("\n").to(LazyList).map {
      case line(_, cubes) =>
        cubes.split(";").to(LazyList)
          .flatMap(_.split(","))
          .map(_.strip())
          .map {
            case red(count) => (count.toInt, 0, 0)
            case green(count) => (0, count.toInt, 0)
            case blue(count) => (0, 0, count.toInt)
          }
          .reduce((l, r) => (max(l._1, r._1), max(l._2, r._2), max(l._3, r._3)))
      }
      .map(t => t._1 * t._2 * t._3)
      .sum
}

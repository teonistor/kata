package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution
import io.github.teonistor.func.Utils.primes

import java.lang.Math.{max, pow}

object _08 extends StandardAdventOfCodeSolution[Long] {

  private val parser = "([a-zA-Z0-9]+) = \\(([a-zA-Z0-9]+), ([a-zA-Z0-9]+)\\)".r

  def _1(input:String): Long =
    solve(input, _=> List("AAA"))

  def _2(input:String): Long =
    solve(input, _.flatMap {
      case parser(from, _,_) => Some(from).filter(_.endsWith("A"))
    })

  private def solve(input:String, startingPointSelector:List[String]=>List[String]) = {
    val instructions :: map :: Nil = input.split("\n\n").toList

    val mapRows = map.strip()
      .split("\n").toList
    val fullMap = mapRows
      .flatMap {
        case parser(from, left, right) => Iterator(
          'L' + from -> left,
          'R' + from -> right)
      }
      .toMap
    val startingPoints = startingPointSelector(mapRows)

    def computeOne(start: String) = {
      val instructionLoop = Iterator.continually(instructions).flatten
      LazyList.iterate(start)(location => fullMap(instructionLoop.next() + location))
        .takeWhile(!_.endsWith("Z"))
        .size.toLong
    }

    startingPoints.iterator
      .map(computeOne)
      .map(primes(_))
      .reduce((l, r) => LazyList.concat(l, r).groupMapReduce(_._1)(_._2)(max))
      .map { case (base, exponent) => pow(base, exponent).toLong }
      .product
  }
}

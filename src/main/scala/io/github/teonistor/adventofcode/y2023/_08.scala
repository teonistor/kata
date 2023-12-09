package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution
import io.github.teonistor.func.Utils.primes

import java.lang.Math.{max, pow}

object _08 extends StandardAdventOfCodeSolution[Long] {

  private val parser = "([a-zA-Z0-9]+) = \\(([a-zA-Z0-9]+), ([a-zA-Z0-9]+)\\)".r

  def _1(input:String) = {
    val instructions :: map :: Nil = input.split("\n\n").toList

    val fullMap = map.strip()
      .split("\n").iterator
      .flatMap {
        case parser(from, left, right) => Iterator(
          'L' + from -> left,
          'R' + from -> right)
      }
      .toMap

    val instructionLoop = Iterator.continually(instructions).flatten
    LazyList.iterate("AAA")(l => fullMap(instructionLoop.next() + l))
      .takeWhile(_ != "ZZZ")
      .size

  }

  def _2_v1(input:String) = {
    val instructions :: map :: Nil = input.split("\n\n").toList

    val fullMap = map.strip()
      .split("\n").iterator
      .flatMap {
        case parser(from, left, right) => Iterator(
          'L' + from -> left,
          'R' + from -> right)
      }
      .toMap
    // TODO Duplication
    val startingPoints = map.strip()
      .split("\n").toList
      .flatMap {
        case parser(from, _, _) => Some(from).filter(_.endsWith("A"))
      }
    println(startingPoints)

    val instructionLoop = Iterator.continually(instructions).flatten
    LazyList.iterate(startingPoints)(locations => {
        // Do no inline. it is paramount that we only call the interator once
        val direction = instructionLoop.next()
        locations.map(from => {
          fullMap(direction + from)
        })
      })
      .takeWhile(!_.forall(_.endsWith("Z")))
      .size

  }

  def _2(input:String) = {
    val instructions :: map :: Nil = input.split("\n\n").toList

    val fullMap = map.strip()
      .split("\n").iterator
      .flatMap {
        case parser(from, left, right) => Iterator(
          'L' + from -> left,
          'R' + from -> right)
      }
      .toMap
    // TODO Duplication
    val startingPoints = map.strip()
      .split("\n").toList
      .flatMap {
        case parser(from, _, _) => Some(from).filter(_.endsWith("A"))
      }
    println(startingPoints)

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
// 18215611419223
}

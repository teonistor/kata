package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.collection.mutable

object _21 extends AdventOfCodeSolution[Long] {
  private val leafMonkey = "([a-z]{4}): (\\d+)".r
  private val nodeMonkey = "([a-z]{4}): ([a-z]{4}) (.) ([a-z]{4})".r

  def _1(input: String): Long = {
    val monkeys = input.split("\n").to(LazyList).map {
      case leafMonkey(name, number) => Left((name, number.toLong))
      case nodeMonkey(name, lhs, "+", rhs) => Right((name, ((_:Long) + (_:Long), lhs, rhs)))
      case nodeMonkey(name, lhs, "-", rhs) => Right((name, ((_:Long) - (_:Long), lhs, rhs)))
      case nodeMonkey(name, lhs, "*", rhs) => Right((name, ((_:Long) * (_:Long), lhs, rhs)))
      case nodeMonkey(name, lhs, "/", rhs) => Right((name, ((_:Long) / (_:Long), lhs, rhs)))
    }

    val memo = monkeys
      .filter(_.isLeft)
      .map(_.swap.getOrElse(null))
      .to(mutable.Map)
    val oper = monkeys
      .filter(_.isRight)
      .map(_.getOrElse(null))
      .toMap

    def doThing(monkey: String): Long =
      if (memo.contains(monkey))
        memo(monkey)
      else {
        val (op, lhs, rhs) = oper(monkey)
        val result = op(doThing(lhs), doThing(rhs))
        memo.put(monkey, result)
        result
      }

    doThing("root")

    // 1425736276 too low
  }

  def _2(input: String): Long = {
    type L = (String, Long)
    type R = (String, ((Long,Long) => Long, (Long,Long) => Long, (Long,Long) => Long, String, String))

    val monkeys = input.split("\n").to(LazyList).map {
      case leafMonkey(name, number) => Left((name, number.toLong))
      case nodeMonkey(name, lhs, "+", rhs) => Right[L,R]((name, (_+_, _-_, _-_, lhs, rhs)))
      case nodeMonkey(name, lhs, "-", rhs) => Right[L,R]((name, (_-_, _+_, -_+_, lhs, rhs)))
      case nodeMonkey(name, lhs, "*", rhs) => Right[L,R]((name, (_*_, _/_, _/_, lhs, rhs)))
      case nodeMonkey(name, lhs, "/", rhs) => Right[L,R]((name, (_/_, _*_, (r,l) => l/r, lhs, rhs)))
    }

    val memo = monkeys
      .filter(_.isLeft)
      .map(_.swap.getOrElse(null))
      .to(mutable.Map)
    val oper = monkeys
      .filter(_.isRight)
      .map(_.getOrElse(null))
      .toMap

    def doThing(monkey: String): Either[Long, Long=>Long] = {
      if (monkey == "humn")
        Right(identity)
      else if (memo.contains(monkey))
        Left(memo(monkey))
      else {
        val (straightOp, leftOp, rightOp, lhs, rhs) = oper(monkey)
        val leftResult = doThing(lhs)
        val rightResult = doThing(rhs)

        if (leftResult.isRight) {
          leftResult.map(func => func.compose(r => leftOp(r, rightResult.swap.getOrElse(1))))

        } else if (rightResult.isRight) {
          rightResult.map(func => func.compose(r => rightOp(r, leftResult.swap.getOrElse(1))))

        } else {

          val result = straightOp(leftResult.swap.getOrElse(1), rightResult.swap.getOrElse(1))
          memo.put(monkey, result)
          Left(result)
        }
      }
    }

    val subtrees = oper.view
      .filterKeys(_ == "root")
      .values.flatMap(m => List(m._4, m._5))
      .map(doThing)

    val monkeySide = subtrees.find(_.isLeft).flatMap(_.swap.toOption).get
    val humanSide = subtrees.find(_.isRight).flatMap(_.toOption).get
    println(monkeySide)

    humanSide(monkeySide)
  }
}

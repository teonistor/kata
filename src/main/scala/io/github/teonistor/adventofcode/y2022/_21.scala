package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.collection.mutable

object _21 extends StandardAdventOfCodeSolution[Long] {
  private val leafMonkey = "([a-z]{4}): (\\d+)".r
  private val nodeMonkey = "([a-z]{4}): ([a-z]{4}) (.) ([a-z]{4})".r

  def _1(input: String): Long = {
    val (memo, oper) = parseInput(input)

    def recu(monkey: String): Long =
      if (memo contains monkey)
        memo(monkey)
      else {
        val (op,_,_, lhs, rhs) = oper(monkey)
        val result = op(recu(lhs), recu(rhs))
        memo.put(monkey, result)
        result
      }

    recu("root")
  }

  def _2(input: String): Long = {
    val (memo, oper) = parseInput(input)

    def recu(monkey: String): Either[Long, Long=>Long] = {
      if (monkey == "humn")
        Right(identity)
      else if (memo contains monkey)
        Left(memo(monkey))
      else {
        val (straightOp, leftOp, rightOp, lhs, rhs) = oper(monkey)
        val leftResult = recu(lhs)
        val rightResult = recu(rhs)

        (leftResult, rightResult) match {
          case (Right(func), Left(num)) => Right(func.compose(leftOp(_, num)))
          case (Left(num), Right(func)) => Right(func.compose(rightOp(_, num)))
          case (Left(leftNum), Left(rightNum)) =>
            val result = straightOp(leftNum, rightNum)
            memo.put(monkey, result)
            Left(result)
        }
      }
    }

    val subtrees = oper.view
      .filterKeys(_ == "root")
      .values.flatMap(m => List(m._4, m._5))
      .map(recu)

    (subtrees.head, subtrees.tail.head) match {
      case (Right(func), Left(num)) => func(num)
      case (Left(num), Right(func)) => func(num)
    }
  }

  private def parseInput(input:String) = {
    type L = (String, Long)
    type R = (String, ((Long, Long) => Long, (Long, Long) => Long, (Long, Long) => Long, String, String))

    val monkeys = input.split("\n").to(LazyList).map {
      case leafMonkey(name, number) => Left((name, number.toLong))
      case nodeMonkey(name, lhs, "+", rhs) => Right[L,R] ((name, (_+_, _-_, _-_, lhs, rhs)))
      case nodeMonkey(name, lhs, "-", rhs) => Right[L,R] ((name, (_-_, _+_, -_+_, lhs, rhs)))
      case nodeMonkey(name, lhs, "*", rhs) => Right[L,R] ((name, (_*_, _/_, _/_, lhs, rhs)))
      case nodeMonkey(name, lhs, "/", rhs) => Right[L,R] ((name, (_/_, _*_, (r,l) => l/r, lhs, rhs)))
    }

    ( monkeys.flatMap {
        case Left(t) => Some(t)
        case _=> None
      }.to(mutable.Map),
      monkeys.flatMap {
        case Right(t) => Some(t)
        case _=> None
      }.toMap)
  }
}

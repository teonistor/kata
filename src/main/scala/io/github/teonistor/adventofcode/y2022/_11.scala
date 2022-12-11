package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _11 extends AdventOfCodeSolution[Long] {
  private val monkeyInput = """
      |Monkey (\d+):
      | +Starting items: (.+)
      | +Operation: new = old (.) (old|\d+)
      | +Test: divisible by (\d+)
      | +If true: throw to monkey (\d+)
      | +If false: throw to monkey (\d+)"""
    .stripMargin.strip.r

  private case class Monkey(
    id: Int,
    items: Queue[Int],
    op: Int => Int,
    divisor: Int,
    trueTarget: Int,
    falseTarget: Int,
    activity: Long)

  def _1(input: String): Long = {
    val monkeys = readInput(input)
    val monkeyIds = monkeys.map(_.id)
    val monkeyMap = monkeys.map(m => (
        m.id,
        m.copy(op=m.op.andThen(_/3))))
      .toMap

    playGame(20, monkeyIds, monkeyMap)
      .values.toList
      .map(_.activity)
      .sortBy(-_)
      .take(2)
      .product
  }

  def _2(input: String): Long = {
    1
  }

  private def readInput(input: String) =
    input.split("\n\n").to(LazyList).map {
      case monkeyInput(id, startItems, operator, operand, divisor, trueTarget, falseTarget) => (
        operator, operand,
        Monkey(
          id.toInt,
          startItems.split("[, ]+").to(Queue).map(_.toInt),
          _,
          divisor.toInt,
          trueTarget.toInt,
          falseTarget.toInt,
          0L))
    } .map {
      case ("*", "old", constructor) => constructor(old => old * old)
      case ("+", "old", constructor) => constructor(old => old + old)
      case ("*", value, constructor) => constructor(_ * value.toInt) // TODO Inefficiency: repeated parse
      case ("+", value, constructor) => constructor(_ + value.toInt) // TODO Inefficiency: repeated parse
    }

  @tailrec
  private def playGame(rounds: Int, monkeyIds: Seq[Int], monkeys: Map[Int, Monkey]): Map[Int, Monkey] = {
//    println(s"Round ${21-rounds}")
    if (rounds < 1)
      monkeys
    else
      playGame(rounds - 1, monkeyIds, playRound(monkeyIds, monkeys))
  }

  @tailrec
  private def playRound(monkeyIds: Seq[Int], monkeys: Map[Int, Monkey]): Map[Int, Monkey] =
    if (monkeyIds.isEmpty)
      monkeys
    else{
//      println(s"Monkey ${monkeyIds.head}")
      val newSituation = playTurn(monkeyIds.head, monkeys)
      playRound(monkeyIds.tail, newSituation)
    }

  @tailrec
  private def playTurn(currentMonkey: Int, monkeys: Map[Int, Monkey]): Map[Int, Monkey] =
    if (monkeys(currentMonkey).items.isEmpty)
      monkeys
    else {
      val worry = monkeys(currentMonkey).op(monkeys(currentMonkey).items.head)
      val targetMonkey = if (worry % monkeys(currentMonkey).divisor == 0) monkeys(currentMonkey).trueTarget else monkeys(currentMonkey).falseTarget

      val newSituation = monkeys.concat(List(
        currentMonkey -> monkeys(currentMonkey).copy(items=monkeys(currentMonkey).items.tail, activity=monkeys(currentMonkey).activity+1),
        targetMonkey -> monkeys.get(targetMonkey).map(m => m.copy(items=m.items.appended(worry))).get))
      playTurn(currentMonkey, newSituation)
    }
}

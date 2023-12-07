package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _07 extends StandardAdventOfCodeSolution[Long] {

  private val types = List(
    (j:Int, map:Map[Char, Int]) => j == 5 || map.exists(_._2 >= 5 - j),
    (j:Int, map:Map[Char, Int]) => map.exists(_._2 == 4 - j),
    (j:Int, map:Map[Char, Int]) => j == 1 && map.values.count(_ >= 2) == 2 || map.values.toSet == Set(2, 3),
    (j:Int, map:Map[Char, Int]) => map.exists(_._2 == 3 - j),
    (j:Int, map:Map[Char, Int]) => j == 1 && map.exists(_._2 == 2) || map.values.count(_ >= 2 - j) == 2,
    (j:Int, map:Map[Char, Int]) => map.exists(_._2 == 2 - j))

  private val defaultStrength = Map(
    'A' -> 14,
    'K' -> 13,
    'Q' -> 12,
    'J' -> 11,
    'T' -> 10,
    '9' -> 9,
    '8' -> 8,
    '7' -> 7,
    '6' -> 6,
    '5' -> 5,
    '4' -> 4,
    '3' -> 3,
    '2' -> 2)

  override def _1(input: String): Long =
    solve(input, defaultStrength, jIsJack)

  override def _2(input: String): Long =
    solve(input, defaultStrength.updated('J', 1), jIsJoker)

  private def solve(input: String, strength:Map[Char, Int], howToPrioritise:String=>(Int, Map[Char,Int])) =
    input.split("\n").to(LazyList)
      .map(_.split(" ").toList)
      .sortBy { case hand :: _ =>
        val first :: second :: third :: fourth :: fifth :: Nil = hand.map(strength).toList
        (typeScore(hand, howToPrioritise), first, second, third, fourth, fifth)
      }
      .map(_.tail.head.toLong)
      .zipWithIndex
      .map { case (bid, index) => bid * (index + 1) }
      .sum

  private def jIsJack(hand: String) =
    (0, hand.groupMapReduce(identity)(_ => 1)(_ + _))

  private def jIsJoker(hand: String) = {
    val value = hand.groupMapReduce(identity)(_ => 1)(_ + _)
    val j = value.getOrElse('J', 0)
    (j, value.removed('J'))
  }

  private def typeScore(hand: String, howToPrioritise: String=>(Int,Map[Char,Int])) = types.iterator
    .zipWithIndex
    .find(_._1.tupled(howToPrioritise(hand)))
    .map(6 - _._2)
    .getOrElse(0)
}

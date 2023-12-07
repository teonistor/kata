package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _07 extends StandardAdventOfCodeSolution[Long] {

  private val types = List(
    (hand: String) => bagHand(hand).exists(_._2 == 5),
    (hand: String) => bagHand(hand).exists(_._2 == 4),
    (hand: String) => bagHand(hand).values.toSet == Set(2,3),
    (hand: String) => bagHand(hand).exists(_._2 == 3),
    (hand: String) => bagHand(hand).values.count(_ == 2) == 2,
    (hand: String) => bagHand(hand).exists(_._2 == 2))

  private val byType = (hand: String) => types.iterator.zipWithIndex
    .find(_._1(hand))
    .map(6 - _._2)
    .getOrElse(0)

  override def _1(input: String): Long =
    input.split("\n").to(LazyList)
      .map(_.split(" ").toList)
//      .map { case hand :: bid :: Nil =>
      .sortBy { case hand :: _ =>
       val first :: second ::third :: fourth :: fifth :: Nil =  hand.map {
          case 'A' => 14
          case 'K' => 13
          case 'Q' => 12
          case 'J' => 11
          case 'T' => 10
          case '9' => 9
          case '8' => 8
          case '7' => 7
          case '6' => 6
          case '5' => 5
          case '4' => 4
          case '3' => 3
          case '2' => 2
        }.toList


    (byType(hand), first, second, third, fourth, fifth)
      }
      .map(_.tail.head.toLong)
      .zipWithIndex
      .map { case (bid,index) => bid * (index + 1)}
      .sum


  private val types2 = List(
    (hand: String) => { val (j,map) = bagHand2(hand); j == 5 || map.exists(_._2 >= 5-j) },
    (hand: String) => { val (j,map) = bagHand2(hand); map.exists(_._2 == 4-j) },
    (hand: String) => { val (j,map) = bagHand2(hand); j == 1 && map.values.count(_ >= 2) == 2 || map.values.toSet == Set(2, 3) },    // inexact
    (hand: String) => { val (j,map) = bagHand2(hand); map.exists(_._2 == 3-j) },
    (hand: String) => { val (j,map) = bagHand2(hand); j == 1 &&map.exists(_._2 == 2) || map.values.count(_ >= 2-j) == 2 },  // inexact
    (hand: String) => { val (j,map) = bagHand2(hand); map.exists(_._2 == 2-j) })

  private val byType2 = (hand: String) => types2.iterator.zipWithIndex
    .find(_._1(hand))
    .map(6 - _._2)
    .getOrElse(0)

  override def _2(input: String): Long =
    input.split("\n").to(LazyList)
      .map(_.split(" ").toList)
      .sortBy { case hand :: _ =>
        val first :: second :: third :: fourth :: fifth :: Nil = hand.map {
          case 'A' => 15
          case 'K' => 13
          case 'Q' => 12
          case 'T' => 10
          case '9' => 9
          case '8' => 8
          case '7' => 7
          case '6' => 6
          case '5' => 5
          case '4' => 4
          case '3' => 3
          case '2' => 2
          case 'J' => 1
        }.toList


        (byType2(hand), first, second, third, fourth, fifth)
      }
      .map(_.tail.head.toLong)
      .zipWithIndex
      .map { case (bid, index) => bid * (index + 1) }
      .sum

  private def bagHand(hand: String) =
    hand.groupMapReduce(identity)(_=>1)(_+_)


  private def bagHand2(hand: String) = {
    val value = hand.groupMapReduce(identity)(_ => 1)(_ + _)
    val j = value.getOrElse('J', 0)
    (j, value.removed('J'))
  }
}

package io.github.teonistor.adventofcode.y2021

import scala.annotation.tailrec

object _21 {

  private val startPosReader = "Player (\\d) starting position: (\\d+)".r

  def _1(input: String): Long = {
    val poss = input.split("\n").map {
      case startPosReader(player, position) => (player.toInt, position.toInt)
    } .sorted
      .map(_._2)

    val rolls = LazyList.continually(1 to 100).flatten
      .grouped(3)
      .map(_.sum)

    @tailrec
    def stuff(nextPlayer: Int, positionsAndScores: Array[(Int,Int)], diceRolls: Int): Long =
      if (positionsAndScores.exists(_._2 >= 1000))
        positionsAndScores.filter(_._2 < 1000).head._2 * diceRolls
      else {
        val newPos = (positionsAndScores(nextPlayer)._1 + rolls.next() - 1) % 10 + 1
        val newScore = positionsAndScores(nextPlayer)._2 + newPos
        positionsAndScores(nextPlayer) = (newPos, newScore)
        stuff((nextPlayer + 1) % 2, positionsAndScores, diceRolls + 3)
      }

    stuff(0, poss.map((_, 0)), 0)
//    rolls
//      .foldLeft((0, ))((data, roll) => {
//        val newPos = (data._2(data._1)._1 + roll -1) % 10 + 1
//        val newScore = data._2(data._1)._1 + newPos
//        data._2(data._1) = (newPos, newScore)
//        ((data._1 + 1) % 2, data._2, data._3 + 3)
//      })
//      .


  }

  // This takes about 100 seconds and can be improved
  def _2(input: String): Long = {
    val poss = input.split("\n").map {
      case startPosReader(player, position) => (player.toInt, position.toInt)
    } .sorted
      .map(_._2)
//      .to(Vector)

    val rolls = (1 to 3).flatMap(a =>
      (1 to 3).flatMap(b =>
        (1 to 3).map(c =>
          a + b + c)))
      .groupMapReduce(identity)(_ => 1)(_+_)
      .to(LazyList)

    def stuff(nextPlayer: Int,
              nextPlayerPosition: Int,
              nextPlayerScore: Int,
              otherPlayerPosition: Int,
              otherPlayerScore: Int,
              universes: Int): Map[Int,Long] =
      if (otherPlayerScore >= 21)
        Map(((nextPlayer + 1) % 2, universes))
      else {
        rolls.flatMap(rollAndUniverses => {
          val newPos = (nextPlayerPosition + rollAndUniverses._1 - 1) % 10 + 1
          val newScore = nextPlayerScore + newPos
          stuff((nextPlayer + 1) % 2, otherPlayerPosition, otherPlayerScore, newPos, newScore, rollAndUniverses._2)
            .view
            .mapValues(_ * universes)
        }).groupMapReduce(_._1)(_._2)(_+_)
      }

    stuff(0, poss(0), 0, poss(1), 0, 1)
      .values
      .max
  }

  def main(arg: Array[String]): Unit = {
  }
}

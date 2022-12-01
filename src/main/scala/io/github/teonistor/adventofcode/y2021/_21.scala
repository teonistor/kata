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

  def _2(input: String): Long = {
    println("Unimplemented")
    0
  }
}

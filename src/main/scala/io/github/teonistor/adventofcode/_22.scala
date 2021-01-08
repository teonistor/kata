package io.github.teonistor.adventofcode

import scala.collection.immutable.Queue

object _22 {
  private val inputR = "Player 1:\n([\\d\n]+)\n\nPlayer 2:\n([\\d\n]+)".r

  private def parseInput(input: String) = input match {
    case inputR(p1, p2) => Array(p1, p2).map(_.split('\n').to(Queue).map(_.toInt))
  }

  private def computeResult(winningDeck: Queue[Int]) = {
    val winningDeckRev = winningDeck.reverse.prepended(0).to(List)
    winningDeckRev.indices.map(i => i * winningDeckRev(i)).sum
  }

  def _1(input: String): Int = {
    val decks = parseInput(input)

    def play(p1: Queue[Int], p2: Queue[Int]): Queue[Int] = {
      if (p1.isEmpty)
        p2
      else if (p2.isEmpty)
        p1
      else if (p1.head > p2.head)
        play(p1.tail.appended(p1.head).appended(p2.head), p2.tail)
      else
        play(p1.tail, p2.tail.appended(p2.head).appended(p1.head))
    }

    computeResult(play(decks(0), decks(1)))
  }

  def _2(input: String): Int = {
    type GameState = (Queue[Int], Queue[Int])
    type GameResult = (Int, Queue[Int])

    def playGame(state: GameState) = playRound(state, Set.empty)

    def playRound(state: GameState, infinityPrevention: Set[GameState]): GameResult =
      if (infinityPrevention contains state)
        (1, state._1)

      else {
        val p1 = state._1
        val p2 = state._2

        if (p1.isEmpty) (2, p2)
        else if (p2.isEmpty) (1, p1)

        else if (
          // The sub-game rule
          if (p1.size > p1.head && p2.size > p2.head)
            playGame(p1.tail take p1.head, p2.tail take p2.head)._1 == 1
          else
            p1.head > p2.head

        )
          playRound((p1.tail.appended(p1.head).appended(p2.head), p2.tail), infinityPrevention + state)
        else
          playRound((p1.tail, p2.tail.appended(p2.head).appended(p1.head)), infinityPrevention + state)
      }

    val decks = parseInput(input)
    computeResult(playGame((decks(0), decks(1)))._2)
  }
}

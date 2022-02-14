package io.github.teonistor.adventofcode.y2021

import scala.annotation.tailrec

object _10 {
  private val pairs = Map(
    ('(', ')'),
    ('[', ']'),
    ('{', '}'),
    ('<', '>'))
  private val corruptionScore = Map(
    (')', 3L),
    (']', 57L),
    ('}', 1197L),
    ('>', 25137L))
  private val autocompleteScore = Map(
    (')', 1L),
    (']', 2L),
    ('}', 3L),
    ('>', 4L))

  def _1(input: String): Long =
    begin(input)
      .filter(_.isLeft)
      .map(_.swap.toOption.get)
      .sum

  def _2(input: String): Long = {
    val scores = begin(input)
      .filter(_.isRight)
      .map(_.toOption.get)
      .map(_.foldLeft(0L)((score, char) =>  score * 5 + autocompleteScore(pairs(char))))
      .sorted
    scores(scores.size / 2)
  }

  private def begin(input: String) =
    input.split("\n").to(LazyList)
      .map(s => parkour(s.toList))

  @tailrec
  private def parkour(input: List[Char], stack: List[Char] = List.empty): Either[Long, List[Char]] =
    if (input.isEmpty)
      Right(stack)
    else {
      val v = input.head
      if (pairs contains v)
        parkour(input.tail, stack prepended v)
      else if (pairs(stack.head) == v)
        parkour(input.tail, stack.tail)
      else
        Left(corruptionScore(v))
    }
}

package io.github.teonistor.kata

import scala.annotation.tailrec

/* An exploration of different ways to calculate the Fibonacci sequence, with no (obvious) loops
 */
object Fib {

  def fibNaive(i: Int): Long =
    if (i < 1)
      0
    else if (i == 1)
      1
    else
      fibNaive(i - 1) + fibNaive(i - 2)

  def fibWithInnerFunc(i: Int): Long =
    if (i < 1)
      0
    else if (i == 1)
      1
    else {

      @tailrec
      def fibRec(additionsLeft: Int, previous: Long, current: Long): Long =
        if (additionsLeft == 0)
          current
        else
          fibRec(additionsLeft - 1, current, previous + current)

      fibRec(i - 1, 0, 1)
    }

  @tailrec
  def fibCunning(i: Int, previous: Long = 0, current: Long = 1): Long =
    if (i < 1)
      previous
    else if (i == 1)
      current
    else
      fibCunning(i - 1, current, previous + current)

  @tailrec
  def fibCondensed(i: Int, current: Long = 0, next: Long = 1): Long =
    if (i < 1)
      current
    else
      fibCondensed(i - 1, next, current + next)

  @tailrec
  def fibMisfeatured(i: Int, current: Long = 0, next: => Long = 1): Long =
    if (i < 1)
      current
    else
      fibMisfeatured(i - 1, next, current + next)

  @tailrec
  def fibMoreCunning(i: Int, current: Long = 0, next: => Long = 1): Long =
    if (i < 1)
      current
    else {
      lazy val localNext = next
      fibMoreCunning(i - 1, localNext, current + localNext)
    }
}

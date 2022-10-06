package io.github.teonistor.kata

import java.util

object QuantumSudoku {
  private val ONE = Set(1)

  private val fixed = Array(
    Array(1, 1, 0, 1, 0, 1, 0, 1, 0),
    Array(1, 1, 0, 0, 1, 0, 0, 0, 1),
    Array(0, 0, 1, 0, 0, 0, 1, 0, 0),
    Array(1, 1, 0, 0, 1, 0, 1, 0, 1),
    Array(0, 0, 0, 0, 1, 1, 1, 0, 1),
    Array(0, 0, 1, 0, 0, 1, 0, 1, 1),
    Array(0, 0, 1, 0, 0, 0, 0, 0, 1),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(1, 1, 0, 0, 0, 0, 0, 1, 0))
  private val white = List(
    (0, 0, 1, 0),
    (1, 1, 1, 2),
    (2, 5, 3, 5),
    (4, 2, 4, 3),
    (6, 0, 6, 1),
    (8, 2, 8, 3))
  private val black = List(
    (4, 5, 5, 5),
    (8, 0, 8, 1))

  private val board = Array.fill(9, 9)(0)

  private var results = Vector.empty[Vector[Vector[Int]]]

  def main(arg: Array[String]): Unit = {
    iter(0, 0)
    println(s"\n\nTotal ${results.length} results\n\n")


  }

  def iter(x:Int, y:Int): Unit = {
    if (y == 9) {
      println(util.Arrays.deepToString(board.asInstanceOf[Array[AnyRef]]))
      results = results appended board.to(LazyList).map(_.toVector).toVector
    } else if (x == 9) {
      iter(0, y + 1)
    } else {
      board(y)(x) = 1
      while (board(y)(x) < 10) {
        if (valid()) {
          iter(x + 1, y)
        }
        board(y)(x) += 1
      }
      board(y)(x) = 0
    }
  }

  def valid() =
    validLines() && validCols() && validBoxes() && validWhites() && validBlacks()

  def validLines() = {
    (0 to 8).forall(y => {
      val set = board(y).to(LazyList)
        .filter(_ > 0)
        .groupMapReduce(identity)(_ => 1)(_ + _)
        .values.toSet
      set.isEmpty || set.equals(ONE)
    }
    )
  }

  def validCols() = {
    (0 to 8).forall(x => {
      val set = board.to(LazyList).map(_ (x))
        .filter(_ > 0)
        .groupMapReduce(identity)(_ => 1)(_ + _)
        .values.toSet
      set.isEmpty || set.equals(ONE)
    }
    )
  }

  def validBoxes() = {
    (0 to 2).map(_ * 3).forall(dx =>
      (0 to 2).map(_ * 3).forall(dy => {
        val set = (0 to 2).flatMap(x =>
          (0 to 2).map(y =>
            board(y + dy)(x + dx)))
          .filter(_ > 0)
          .groupMapReduce(identity)(_ => 1)(_ + _)
          .values.toSet
        set.isEmpty || set.equals(ONE)
      }))
  }

  def validWhites() = {
    white.forall(w => {
      val a = board(w._1)(w._2)
      val b = board(w._3)(w._4)
      a == 0 || b == 0 || a - b == 1 || b - a == 1
    })
  }

  def validBlacks() = {
    black.forall(w => {
      val a = board(w._1)(w._2)
      val b = board(w._3)(w._4)
      a == 0 || b == 0 || a * 2 == b || b * 2 == a
    })
  }
}

package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec

object _20 extends AdventOfCodeSolution[Long] {

  // Thanks past me for solving a similar problem in y2020._23
  private class MutableCircle(seq: IndexedSeq[Long]) {

    class MutableNode(val value: Long, var previous: MutableNode = null, var next: MutableNode = null) {

      @tailrec
      final def jump(n: Long): MutableNode = {
        if (n < 1) this
        else if (n == 1) next
        else next.jump(n-1)
      }
    }

    val size = seq.length
    val nodes = {
      val temp = Array.tabulate(size)(i => new MutableNode(seq(i)))
      (0 to size - 2).foreach(i => temp(i).next = temp(i + 1))
      temp.last.next = temp.head
      (1 until size).foreach(i => temp(i).previous = temp(i-1))
      temp.head.previous = temp.last
      temp
    }
//    private var current = nodes(seq(0))


    def ensureRange(i: Int): Int =
      (i + size) % size

//    def iterateFrom(origin: Int): LazyList[Int] =
//      LazyList.iterate(nodes(origin))(_.next).map(_.value)
//
//    def getCurrentAndFour: (Int, Set[Int]) =
//      (current.value, HashSet.iterate(current, 4)(_.next).map(_.value))
//
//    /**
//     * Rotate the current pointer once along
//     */
//    def advance() {
//      current = current.next
//    }

//    /**
//     * Move the 3 elements from after the current one to after the given element
//     *
//     * @param to Element value after which to put the 3 elements
//     */
    def displace(node:MutableNode, by:Int): Unit = {
      node.next.previous = node.previous
      node.previous.next = node.next

      val to = node.jump(by)
      node.previous = to
      node.next = to.next
      to.next = node
      node.next.previous = node


//      val displaced = current.next
//      current.next = displaced.next.next.next
//
//      displaced.next.next.next = nodes(to).next
//      nodes(to).next = displaced
    }

    override def toString: String =
      LazyList.iterate(nodes.head.next)(_.next).takeWhile(_ != nodes.head).prepended(nodes.head).map(_.value).mkString("[", " ", "]")
  }


  def _1(input: String): Long = {
    val circle = new MutableCircle(input.split("\n").map(_.toLong))
    var zero: circle.MutableNode = null

    circle.nodes.foreach(n => {
      if (n.value == 0)
        zero = n

      else if (n.value < 0)
        circle.displace(n, circle.size - 1 - ((-n.value) % (circle.size - 1)).toInt)
      else
        circle.displace(n, (n.value % (circle.size - 1)).toInt)
    })

    LazyList(1000, 2000, 3000)
      .map(zero.jump(_))
      .map(_.value)
      .sum

//    ???
    // 1883 too low
    // 16493 too high
    // 9866 correct
  }

  def _2(input: String): Long = {
    val multiplier = 811589153L
    val howManyRepetitions = 10

    solve(input, multiplier, howManyRepetitions)
  }

  private def solve(input: String, multiplier: Long, howManyRepetitions: Int) = {

    val circle = new MutableCircle(input.split("\n").map(_.toLong * multiplier))
    var zero: circle.MutableNode = null


    (1 to howManyRepetitions).flatMap(_ => {
      circle.nodes
    })
      .foreach(n => {
        if (n.value == 0)
          zero = n

        else if (n.value < 0)
          circle.displace(n, circle.size - 1 - ((-n.value) % (circle.size - 1)).toInt)
        else
          circle.displace(n, (n.value % (circle.size - 1)).toInt)
      })

    //      println("-----------------")
    //      LazyList.iterate(zero.next)(_.next)
    //        .takeWhile(zero != _)
    //        .map(_.value)
    //        .foreach(println)
    //    })

    LazyList(1000, 2000, 3000)
      .map(zero.jump(_))
      .map(_.value)
      //      .tapEach(println)
      .sum

    // 5629182365208 too low - given by the one when I flipped sign

  }
}

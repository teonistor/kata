package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _20 extends StandardAdventOfCodeSolution[Long] {

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

    def displace(node:MutableNode, by:Int): Unit = {
      node.next.previous = node.previous
      node.previous.next = node.next

      val to = node.jump(by)
      node.previous = to
      node.next = to.next
      to.next = node
      node.next.previous = node
    }
  }

  def _1(input: String): Long =
    solve(input, 1, 1)

  def _2(input: String): Long =
    solve(input, 811589153L, 10)

  private def solve(input: String, multiplier: Long, howManyRepetitions: Int) = {
    val circle = new MutableCircle(input.split("\n").map(_.toLong * multiplier))
    var zero: circle.MutableNode = null

    (1 to howManyRepetitions).flatMap(_ => circle.nodes)
      .foreach(node => {
        if (node.value == 0)
          zero = node
        else if (node.value < 0)
          circle.displace(node, circle.size - 1 - ((-node.value) % (circle.size - 1)).toInt)
        else
          circle.displace(node, (node.value % (circle.size - 1)).toInt)
      })

    LazyList(1000, 2000, 3000)
      .map(zero.jump(_))
      .map(_.value)
      .sum
  }
}

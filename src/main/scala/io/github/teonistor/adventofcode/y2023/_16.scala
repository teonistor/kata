package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.util.concurrent.Callable
import java.util.concurrent.Executors.newFixedThreadPool
import scala.annotation.tailrec

object _16 extends StandardAdventOfCodeSolution[Int] {

  private val propagation = Map(
    "L." -> List((0,1,'L')),
    "L/" -> List((-1,0,'B')),
    "L\\" -> List((1,0,'T')),
    "L-" -> List((0,1,'L')),
    "L|" -> List((-1,0,'B'), (1,0,'T')),

    "T." -> List((1,0,'T')),
    "T/" -> List((0,-1,'R')),
    "T\\" -> List((0,1,'L')),
    "T-" -> List((0,1,'L'), (0,-1,'R')),
    "T|" -> List((1,0,'T')),

    "R." -> List((0,-1,'R')),
    "R/" -> List((1,0,'T')),
    "R\\" -> List((-1,0,'B')),
    "R-" -> List((0,-1,'R')),
    "R|" -> List((-1,0,'B'), (1,0,'T')),

    "B." -> List((-1,0,'B')),
    "B/" -> List((0,1,'L')),
    "B\\" -> List((0,-1,'R')),
    "B-" -> List((0,1,'L'), (0,-1,'R')),
    "B|" -> List((-1,0,'B')))

  private val masks = Map(
    'L' -> 1,
    'T' -> 2,
    'R' -> 4,
    'B' -> 8)

  override def _1(input: String): Int =
    computeEnergisation(input, (0, 0, 'L'))

  override def _2(input: String): Int = {
    val executor = newFixedThreadPool(8, (r: Runnable) => {
      val t = new Thread(r)
      t.setDaemon(true)
      t
    })
    def submit[T](callable:Callable[T]) =
      executor submit callable

    val map = input.split('\n')
    val rightmost = map(0).length - 1
    val bottommost = map.length - 1

    LazyList.concat(
      map.indices.flatMap(row => List(
        (row, 0, 'L'),
        (row, rightmost, 'R'))),
      map(0).indices.flatMap(col => List(
        (0, col, 'T'),
        (bottommost, col, 'B'))))
      .map[Callable[Int]](start => () => computeEnergisation(input, start))
      .toList
      .map(submit)
      .map(_.get())
      .max
  }

  private def computeEnergisation(input: String, start: (Int, Int, Char)): Int = {
    val map = input.split('\n')
    val mask = Array.fill(map.length, map(0).length)(0)

    @tailrec
    def beam(input: List[(Int,Int,Char)]): Unit =
      if (input.nonEmpty) {
        val (row,col,from) :: rest = input

        if (mask.lift(row).flatMap(_.lift(col)).exists(u => (u & masks(from)) ==0)) {
          mask(row)(col) |= masks(from)

          beam(propagation(from.toString + map(row)(col))
            .map {
              case (dr, dc, from) => (row + dr, col + dc, from)
            } ++: rest)

        } else
          beam(rest)
      }

    beam(List(start))
    mask.map(_.count(_!=0)).sum
  }
}

package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

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

  override def _1(input: String): Int = {
    val map = input.split('\n')
    val mask = Array.fill(map.length, map(0).length)(0)

    def beam(input: List[(Int,Int,Char)]): Unit = {
      if (input.nonEmpty) {
        val (row,col,from) :: rest = input

        if (mask.lift(row).flatMap(_.lift(col)).exists(u => (u & masks(from)) ==0)) {

//          val prop = map.lift(row)
//            .flatMap(_.lift(col))
//            .map(from.toString + _).toList
//            .flatMap(propagation)
          mask(row)(col) |= masks(from)

          val prop = propagation(from.toString + map(row)(col))
            .map {
              case (dr, dc, from) => (row+dr,col+dc,from)
            }
          beam(prop ++: rest)

        } else
          beam(rest)
      }
    }

    beam(List((0, 0, 'L')))
    mask.map(_.count(_!=0)).sum
  }

  override def _2(input: String): Int = {
    2
  }

}

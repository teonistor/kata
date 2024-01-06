package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _24 extends StandardAdventOfCodeSolution[Long] {

  private val splitter = "([0-9-]+), +([0-9-]+), +([0-9-]+) +@ +([0-9-]+), +([0-9-]+), +([0-9-]+)".r
  private val overrider = "from ([0-9-]+) to ([0-9-]+)".r

  private case class Stone(px:Int, py:Int, pz:Int, vx:Int, vy:Int, vz:Int) {

    // p(0) = p...
    // p(1) = p... + v...
    // y = mx + c
  }

  override def _1(input: String): Long = {
    val ial = input.split('\n').toList
    val extract: String => (Long, Long, Long, Long, Long, Long) = {
      case splitter(px, py, pz, vx, vy, vz) => (px.toLong, py.toLong, pz.toLong, vx.toLong, vy.toLong, vz.toLong)
    }

    val (rangeStart, rangeEnd, stones) =
      if (input contains "from") {
        val overrider(from, to) = ial.head
        (from.toLong, to.toLong, ial.tail map extract)
      } else
        (200000000000000L, 400000000000000L, ial map extract)

    (1 until stones.size).flatMap(i =>
      (0 until i).flatMap { j =>
        val (x0a, y0a, _, dxa, dya, _) = stones(i)
        val (x0b, y0b, _, dxb, dyb, _) = stones(j)
        
        Some((i,j)).filter(_=>
          intersect2d(x0a, y0a, dxa, dya, x0b, y0b, dxb, dyb, rangeStart, rangeEnd)
        )
      })
      .size
  }
  
  private def intersect2d(x0a:Long, y0a:Long, dxa:Long, dya:Long, x0b:Long, y0b:Long, dxb:Long, dyb:Long, rangeStart:Long, rangeEnd:Long): Boolean = {
    // The algebraic gymnastics I have to do to avoid floats, man...
    val L = dya * dxb - dxa * dyb
    val R = dxa * dxb * (y0b - y0a) + dya * dxb * x0a - dxa * dyb * x0b

    if (L == 0) {
      // Lines are parallel or overlap

      if (R != 0L) {
        // Lines are parallel
        println("Parallel")
        return false
      }

      println("Edge case!")
      if (dxa == dxb && dya == dyb) {
        // Objects never catch each other
        return false
      }

      // TODO Range
      // TODO past/future
      true

    } else {
      // Lines intersect

      if (L > 0L && rangeStart * L <= R && R <= rangeEnd * L
        ||L < 0L && rangeStart * L >= R && R >= rangeEnd * L) {

        val y = (y0a + dya.toDouble / dxa * (R.toDouble / L - x0a)).toLong
        if (rangeStart <= y && y <= rangeEnd) {

          // past/future
          if (dya <= 0 == y <= y0a && dyb <= 0 == y <= y0b)
            return true

        }
      }

      false
    }
  }

  override def _2(input: String): Long = ???

}

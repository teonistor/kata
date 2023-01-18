package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.lang.Math.{max, min}
import scala.annotation.tailrec

object _14 extends AdventOfCodeSolution[Int] {

  private val sandOrigin = (500, 0)
  private type Continuation = (Array[Array[Boolean]], Int, Int, Int, Int, (Int, Int)) => Int

  def _1(input: String): Int = solve(input, sand1)

  def _2(input: String): Int = solve(input, sand2)

  private def solve(input: String, continuation: Continuation) = {
    val edges = input.split("\n").to(LazyList)
      .flatMap(streak => {
        val corners = streak.split("[ >-]+").to(LazyList)
          .map(_.split(",").map(_.toInt))
          .map(coords => (coords(0), coords(1)))

        corners.tail.zip(corners)
      })
      .toList

    val (pLeft, pRight, pTop, pBottom) = edges.flatMap(edge => Set(edge._1, edge._2))
      .foldLeft((sandOrigin._1, sandOrigin._1, sandOrigin._2, sandOrigin._2))((minMax, xy) => (
        min(minMax._1, xy._1),
        max(minMax._2, xy._1),
        min(minMax._3, xy._2),
        max(minMax._4, xy._2)))

    val left = pLeft - 500
    val right = pRight + 500
    val top = pTop
    val bottom = pBottom + 1

    val chart = Array.fill(bottom - top + 2, right - left + 2)(false)
    edges.tapEach(edge => {
      if (edge._1._1 == edge._2._1)
        (min(edge._1._2, edge._2._2) to max(edge._1._2, edge._2._2)).tapEach(i => chart(i - top)(edge._1._1 - left) = true)
      else
        (min(edge._1._1, edge._2._1) to max(edge._1._1, edge._2._1)).tapEach(j => chart(edge._1._2 - top)(j - left) = true)
    })

    continuation(chart, left, top, bottom, 0, sandOrigin)
  }

  @tailrec
  private def sand1(chart: Array[Array[Boolean]],
                    left: Int,
                    top: Int,
                    bottom: Int,
                    grains: Int,
                    position: (Int, Int) = sandOrigin): Int = {
    if (position._2 > bottom)
      grains
    else {
      val possibl = List((position._1, position._2 + 1), (position._1 - 1, position._2 + 1), (position._1 + 1, position._2 + 1))

      val maybeTuple = possibl.find(p => !chart.lift(p._2 - top).flatMap(u => u.lift(p._1 - left)).exists(identity))

      if (maybeTuple.isDefined)
        sand1(chart, left, top, bottom, grains, maybeTuple.get)
      else {
        chart(position._2 - top)(position._1 - left) = true
        sand1(chart, left, top, bottom, grains + 1)
      }
    }
  }

  @tailrec
  private def sand2(chart: Array[Array[Boolean]],
                    left: Int,
                    top: Int,
                    bottom: Int,
                    grains: Int,
                    position: (Int, Int) = sandOrigin): Int = {
    if (chart(sandOrigin._2 - top)(sandOrigin._1 - left))
      grains
    else {
      if (position._2 >= bottom) {
        chart(position._2 - top)(position._1 - left) = true
        sand2(chart, left, top, bottom, grains + 1)

      } else {

        val possibl = List((position._1, position._2 + 1), (position._1 - 1, position._2 + 1), (position._1 + 1, position._2 + 1))

        val maybeTuple = possibl.find(p => !chart(p._2 - top)(p._1 - left))

        if (maybeTuple.isDefined)
          sand2(chart, left, top, bottom, grains, maybeTuple.get)
        else {
          chart(position._2 - top)(position._1 - left) = true
          sand2(chart, left, top, bottom, grains + 1)
        }
      }
    }
  }
}

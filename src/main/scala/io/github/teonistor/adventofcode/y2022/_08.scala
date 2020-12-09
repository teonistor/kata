package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.util.concurrent.atomic.AtomicBoolean

object _08 extends StandardAdventOfCodeSolution[Int] {

  def _1(input: String): Int = {
    val array = input.split("\n").map(_.map(_.toString.toInt))
    val visible = Array.fill(array.length)(Array.fill(array(0).length)(false))

    val is = array.indices
    val js = array(0).indices

    def myFold(i: Int, j: Int, max: Int) =
      if (array(i)(j) <= max)
        max
      else {
        visible(i)(j) = true
        array(i)(j)
      }

    def myRanger(outer:Range, inner:Range, func:(Int,Int,Int)=>Int): Any =
      outer.foreach(l =>
        inner.foldLeft(-1)((max, r) =>
          func(l, r, max)))

    myRanger(is, js, myFold)
    myRanger(is, js.reverse, myFold)
    myRanger(js, is, (j,i,max) => myFold(i,j,max))
    myRanger(js, is.reverse, (j,i,max) => myFold(i,j,max))

    visible.flatten.count(identity)
  }

  def _2(input: String): Int = {
    val array = input.split("\n").map(_.map(_.toString.toInt))
    val scenic = Array.fill(array.length)(Array.fill(array(0).length)(Array.fill(4)(0)))

    val is = array.indices
    val js = array(0).indices

    is.foreach(i =>
      js.foreach(j => {
        val bump = new AtomicBoolean(true)

        scenic(i)(j)(0) = (j until array(0).length)
          .drop(1)
          .takeWhile(bump.get() && array(i)(_) < array(i)(j) || bump.getAndSet(false))
          .size

        bump.set(true)
        scenic(i)(j)(1) = (0 to j).reverse
          .drop(1)
          .takeWhile(bump.get() && array(i)(_) < array(i)(j) || bump.getAndSet(false))
          .size

        bump.set(true)
        scenic(i)(j)(2) = (i until array.length)
          .drop(1)
          .takeWhile(bump.get() && array(_)(j) < array(i)(j) || bump.getAndSet(false))
          .size

        bump.set(true)
        scenic(i)(j)(3) = (0 to i).reverse
          .drop(1)
          .takeWhile(bump.get() && array(_)(j) < array(i)(j) || bump.getAndSet(false))
          .size
      }))

    scenic.flatten
      .map(_.product)
      .max
  }
}

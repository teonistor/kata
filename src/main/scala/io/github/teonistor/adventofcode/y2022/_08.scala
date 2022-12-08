package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.util
import java.util.concurrent.atomic.AtomicBoolean

object _08 extends AdventOfCodeSolution[Int] {

  def _1(input: String): Int = {
    val array = input.split("\n").map(_.map(_.toString.toInt))
    val visible = Array.fill(array.length)(Array.fill(array(0).length)(false))

    val is = array.indices
    val js = array(0).indices

    is.foreach(i =>
      js.foldLeft(-1)((max, j) => {
        if (array(i)(j) > max) {
          visible(i)(j) = true
          array(i)(j)
        } else
          max
      }))

    is.foreach(i =>
      js.reverse.foldLeft(-1)((max, j) => {
        if (array(i)(j) > max) {
          visible(i)(j) = true
          array(i)(j)
        } else
          max
      }))

    js.foreach(j =>
      is.foldLeft(-1)((max, i) => {
        if (array(i)(j) > max) {
          visible(i)(j) = true
          array(i)(j)
        } else
          max
      }))

    js.foreach(j =>
      is.reverse.foldLeft(-1)((max, i) => {
        if (array(i)(j) > max) {
          visible(i)(j) = true
          array(i)(j)
        } else
          max
      }))

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

//        def stuff(b: Boolean) = {
//          if (b)
//            b
//            else
//        }

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

    println(util.Arrays.deepToString(scenic.asInstanceOf[Array[AnyRef]]))

    scenic.flatten
      .map(_/*.map(_+1)*/.product)
      .max

  }
}

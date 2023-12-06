package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.{ceil, floor, sqrt}
import java.math.MathContext

object _06 extends StandardAdventOfCodeSolution[Long]{


  override def _1(input: String): Long = {
    val timeRow :: distanceRow :: Nil = input.split("\n").toList
    val times = timeRow.replace("Time:", "").strip().split("\\s+").map(_.toLong)
    val distances = distanceRow.replace("Distance:", "").strip().split("\\s+").map(_.toLong)
    (times.iterator zip distances.iterator).map { case (distance, time) =>
      val delta = distance * distance - 4 * time
      if (delta < 0)
        throw new IllegalStateException(s"No real roots for $distance, $time")

      val sqrtdd = sqrt(delta) / 2
      val nd = distance.toDouble / 2

      val min = floor(nd - sqrtdd)
      val max = ceil(nd + sqrtdd)
      max.toLong - min.toLong - 1
    }
      .product
  }

  override def _2(input: String): Long = {

    val timeRow :: distanceRow :: Nil = input.split("\n").toList
    val times = timeRow.replace("Time:", "").replaceAll("\\s+", "").strip().split("\\s+").map(_.toLong)
    val distances = distanceRow.replace("Distance:", "").strip().split("\\s+").map(_.toLong)
    (times.iterator zip distances.iterator).map { case (distance, time) =>
        val delta = distance * distance - 4 * time
        if (delta < 0)
          throw new IllegalStateException(s"No real roots for $distance, $time")

        val sqrtdd = BigDecimal(BigDecimal(delta).bigDecimal.sqrt(MathContext.DECIMAL128)) / 2
        val nd = BigDecimal(distance) / 2

        val min = /*floor*/(nd - sqrtdd).bigDecimal.longValue()
        val max = /*ceil*/(nd + sqrtdd).bigDecimal.longValue()
        max-min
      }
      .product
  }

//  val time = new math.BigDecimal("44806572")
//  val distance = new math.BigDecimal("208158110501102")
//
//  // -x^2 + time * x - distance > 0
//
//  val d2 = time.multiply(new math.BigDecimal("0.5"))
//  val dsqd = time.multiply(time).divide(new math.BigDecimal(4)).subtract(distance).sqrt(MathContext.DECIMAL128)
//
//  val min = d2.subtract(dsqd)
//  val max = d2.add(dsqd)
//
//  println(min)
//  println(max)
//  println(39542396 - 5264175)

  // 34278221
}

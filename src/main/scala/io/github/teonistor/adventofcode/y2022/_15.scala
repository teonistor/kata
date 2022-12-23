package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.lang.Math.abs

object _15 extends AdventOfCodeSolution[Int]{

  private val sensorInfo = "Sensor at x=([0-9-]+), ?y=([0-9-]+): closest beacon is at x=([0-9-]+), ?y=([0-9-]+)".r

  private[y2022] case class Point(x:Int, y:Int) {
    private[y2022] def -(that: Point) = Point(x - that.x, y - that.y)
    private[y2022] def manhattan() = abs(x) + abs(y)
  }

  def _1(input: String): Int = {
    val lines = input.split("\n").toList
    val lineOfInterest = lines.head.toInt
    val sensorsAndBeacons = lines.tail.map {
      case sensorInfo(sx, sy, bx, by) => (Point(sx.toInt,sy.toInt), Point(bx.toInt,by.toInt))
    }

    sensorsAndBeacons.to(LazyList)
      .map(sb => (sb._1, sb._2, (sb._1 - sb._2).manhattan()))
      .map(sbd => (sbd._3 - abs(sbd._1.y - lineOfInterest), sbd._1.x))
      .filter(_._1 >= 0)
      .toSet
      .flatMap[Int](dx => dx._2 - dx._1 to dx._2 + dx._1)
      .diff(sensorsAndBeacons.to(LazyList)
        .filter(sb => sb._2.y == lineOfInterest)
        .map(sb => sb._2.x)
        .toSet)
      .size
  }

  def _2(input:String) = {
    56000011


  }

//  def main(arg: Array[String]): Unit = {
//    println(_1(testInput))
//    println(_1(problemInput))
//
//    // List(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
//    // 25
//    // 6668901 4811414
//  }
}

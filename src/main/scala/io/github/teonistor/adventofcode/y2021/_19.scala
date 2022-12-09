package io.github.teonistor.adventofcode.y2021

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.AdventOfCodeSolution

import java.lang.Math.abs
import java.util.concurrent.Callable
import java.util.concurrent.Executors.newFixedThreadPool

object _19 extends AdventOfCodeSolution[Int]{
  private type Point = (Int, Int, Int)
  private val scannerHeader = "--- scanner (\\d+) ---".r

  private val orientationR = "(-?[xyz])(-?[xyz])(-?[xyz])".r
  private val orientations =
    """xyz
      |xz-y
      |x-y-z
      |x-zy
      |-zyx
      |-zx-y
      |-z-y-x
      |-z-xy
      |-xy-z
      |-x-z-y
      |-x-yz
      |-xzy
      |zy-x
      |z-x-y
      |z-yx
      |zxy
      |y-z-x
      |yx-z
      |yzx
      |y-xz
      |-y-zx
      |-yxz
      |-yz-x
      |-y-x-z"""
    .stripMargin.split("\n").toList
    .map { case orientationR(x, y, z) => (point: Point) => listToPoint(List(x, y, z).map(extractCoordinate(point, _))) }

  def _1(input: String): Int = {
    val v = input.split("\n\n").to(LazyList)
      .map(readOneScannerInput)
      .map(_._2)
      // Slight edge case
      .toSet

    val r = solve(v.head, Set((0, 0, 0)), v.tail)


    r.head._1.size
  }

  def _2(input: String): Int = {
    val v = input.split("\n\n").to(LazyList)
      .map(readOneScannerInput)
      .map(_._2)
      // Slight edge case
      .toSet

    val value = solve(v.head, Set((0, 0, 0)), v.tail).head._2.toList
    value.indices.flatMap(i =>
      value.indices.map(j =>
        (value(i) - value(j)).manhattan))
      .max
  }

  @VisibleForTesting
  private[y2021] def readOneScannerInput(input: String) =
    input.split("\n").toList match {
      case header :: points => (
        header match {
          case scannerHeader(i) => i.toInt
        },
        points.map(_.split(",").map(_.toInt).toList)
          .map(listToPoint)
          .toSet)
    }

  private def solve(beacons: Set[Point], scanners: Set[Point], input: Set[Set[Point]], debugDepth:Int = 1): LazyList[(Set[Point],Set[Point])] =
    if (input.isEmpty)
      LazyList((beacons, scanners))

    else {
      println(s"Depth $debugDepth - ${beacons.size} beacons")

      input.to(LazyList).flatMap(current =>
        turnAndAffix(beacons, current)
          // Thanks game for stipulating at least 12 points must overlap
          .filter(_.matchedCount > 11)
          // Any new points not matched to existing ones must not be within range of existing beacons
          .filter(_.unmatched.forall(point => !scanners.exists(isInBox(point, _))))
          // Similarly, any existing points not matched must not be within range of the new beacon
          .filter(result => (beacons -- result.matched).forall(point => !isInBox(point, result.diff)))

          .flatMap(result =>
            solve(beacons ++ result.unmatched,
              scanners + result.diff,
              input - current,
              debugDepth + 1)))
//        .sortBy(_.size)
//        .distinct
    }

  @VisibleForTesting
  private[y2021] def isInBox(point: Point, boxCentre: Point) =
    abs(point._1 - boxCentre._1) < 1001 &&
      abs(point._2 - boxCentre._2) < 1001 &&
      abs(point._3 - boxCentre._3) < 1001

  private val executor = newFixedThreadPool(8)

  @VisibleForTesting
  private[y2021] def turnAndAffix(target: Set[Point], flotant: Set[Point]) ={
    val value = orientations.indices.to(List)
      .map[Callable[LazyList[TurnAndAffixResult]]](i => () => {
        val ori = orientations(i)
        affix(target, flotant.map(ori)).map(new TurnAndAffixResult(i, _))
    })
      .map(callable => executor.submit(callable))
      .to(LazyList)
      .flatMap(_.get)
      .sortBy(-_.matchedCount)
//    println("matchedCount: " + value.map(_.matchedCount).toList)
    value.filter(_.matchedCount > 1)
  }

  @VisibleForTesting
  private[y2021] def affix(target: Set[Point], flotant: Set[Point]) = {
    target.to(LazyList).flatMap(oneTarget => {
      flotant.to(LazyList).map(oneFlotant => {
        oneTarget - oneFlotant

      })
    })
      .distinct
      .map(diff => new AffixResult(diff, flotant.map(_ + diff).groupBy(target.contains)))
      .sortBy(-_.matchedCount)
  }

  private def listToPoint(list: List[Int]) =
    (list.head, list(1), list(2))

  private def extractCoordinate(point: Point, coord: String) =
    coord match {
      case "x" => point._1
      case "y" => point._2
      case "z" => point._3
      case "-x" => -point._1
      case "-y" => -point._2
      case "-z" => -point._3
    }

  private implicit class PointOps(val point: Point) {
    def -(other: Point): Point =
      (point._1 - other._1, point._2 - other._2, point._3 - other._3)

    def +(other: Point): Point =
      (point._1 + other._1, point._2 + other._2, point._3 + other._3)

    def manhattan =
      abs(point._1) + abs(point._2) + abs(point._3)
  }

  private[y2021] case class AffixResult(diff: Point, matched: Set[Point], unmatched: Set[Point]) {
    def this(diff: Point, mash: Map[Boolean, Set[Point]]) =
      this(diff, mash.getOrElse(true, Set.empty), mash.getOrElse(false, Set.empty))

    lazy val matchedCount = matched.size
  }

  private[y2021] case class TurnAndAffixResult(orientation: Int, diff: Point, matched: Set[Point], unmatched: Set[Point], matchedCount: Int) {
    def this(orientation: Int, affixResult: AffixResult) =
      this(orientation, affixResult.diff, affixResult.matched, affixResult.unmatched, affixResult.matchedCount)
  }
}

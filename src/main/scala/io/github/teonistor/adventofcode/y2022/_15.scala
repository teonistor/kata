package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution
import org.apache.commons.lang3.StringUtils.leftPad

import java.lang.Integer.toBinaryString
import java.lang.Math.abs

object _15 extends StandardAdventOfCodeSolution[Long]{

  private val sensorInfo = "Sensor at x=([0-9-]+), ?y=([0-9-]+): closest beacon is at x=([0-9-]+), ?y=([0-9-]+)".r

  private[y2022] case class Point(x:Int, y:Int) {
    private[y2022] def -(that: Point) = Point(x - that.x, y - that.y)
    private[y2022] def manhattan() = abs(x) + abs(y)
  }

  def _1(input: String): Long = {
    val lines = input.split("\n").toList
    val lineOfInterest = if (input.length > 1000) 2000000 else 10
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

  def _2(input:String): Long = {
    val lines = input.split("\n").toList
    val searchLimit = if (input.length > 1000) 4000000 else 20
    val sensorsAndBeacons = lines.tail.map {
      case sensorInfo(sx, sy, bx, by) => (Point(sx.toInt, sy.toInt), Point(bx.toInt, by.toInt))
    }

    val sbd = sensorsAndBeacons.map(sb => (sb._1, sb._2, (sb._1 - sb._2).manhattan()))

    val overlapMatrix = Array.fill(sbd.size)(0)
    val touchMatrix = Array.fill(sbd.size)(0)

    println("Building matrices...")

    sbd.indices.foreach(i =>
      sbd.indices.drop(i + 1).foreach(j => {
        val (oneBeacon, _, oneDistance) = sbd(i)
        val (anotherBeacon, _, anotherDistance) = sbd(j)
        val diff = (oneBeacon - anotherBeacon).manhattan() - oneDistance - anotherDistance

        if (diff < 0) {
          println(s"$oneBeacon [$i] with distance $oneDistance overlaps $anotherBeacon [$j] with distance $anotherDistance")
          overlapMatrix(i) |= (1 << j)
          overlapMatrix(j) |= (1 << i)
          touchMatrix(i) |= (1 << j)
          touchMatrix(j) |= (1 << i)

        } else if (diff == 0) {
          println(s"$oneBeacon [$i] with distance $oneDistance touches $anotherBeacon [$j] with distance $anotherDistance")
          touchMatrix(i) |= (1 << j)
          touchMatrix(j) |= (1 << i)

        } else {
          println(s"$oneBeacon [$i] with distance $oneDistance and $anotherBeacon [$j] with distance $anotherDistance do not meet")
        }
      }))

    println("Overlap matrix:")
    overlapMatrix.map(toBinaryString).map(leftPad(_, sbd.size, '0')).foreach(println)
    println("Touch matrix:")
    touchMatrix.map(toBinaryString).map(leftPad(_, sbd.size, '0')).foreach(println)

    val chains = sbd.indices.flatMap(a => {
      (a + 1 until sbd.size)
        .filter(b => (touchMatrix(b) & (1 << a)) > 0)
        .flatMap(b => {
          (a + 1 until sbd.size)
//            .filter(_ != b)
            .filter(c => (touchMatrix(c) & (1 << b)) != 0)
            .filter(c => (touchMatrix(c) & (1 << a)) == 0)
            .flatMap(c => {
              (b + 1 until sbd.size)
//                .filter(_ != b)
//                .filter(_ != c)
                .filter(d => (touchMatrix(d) & (1 << c)) != 0)
                .filter(d => (touchMatrix(d) & (1 << b)) == 0)
                .filter(d => (touchMatrix(d) & (1 << a)) != 0)
                .map(d => (a, b, c, d,
                  List((a,b), (b,c), (c, d), (a,d))
                    .count(lr => (overlapMatrix(lr._1) & (1 << lr._2)) != 0)))
            })
        })
    })

    // (3,4,10,9,  3)
    // (3,6,11,9,  4)
    // (3,9,10,4,  3)
    // (3,9,11,6,  4)

//    println("Found chains:")
//    chains.foreach(println)
//
//    val pts = (0 to searchLimit).flatMap(coord =>
//        List((0, coord), (searchLimit, coord), (coord, 0), (coord, searchLimit)))
//      .distinct
//      .map((Point.apply _).tupled)
//      .filter(u => sbd.forall(a => (a._1 - u).manhattan() > a._3))
//
//    println("Edge hit:")
//    pts.foreach(println)

    val smallestOfChains = chains.map(i =>
      List(sbd(i._1), sbd(i._2), sbd(i._3), sbd(i._4))
        .minBy(_._3))
      .distinct

    println(smallestOfChains.mkString("Will look around these: ", ", ", ""))

    val pts = smallestOfChains.flatMap(c =>
      (0 to c._3 + 1).flatMap(d => List(
        Point(c._1.x + d, c._1.y + c._3 + 1 - d),
        Point(c._1.x + d, c._1.y - c._3 - 1 + d),
        Point(c._1.x - d, c._1.y + c._3 + 1 - d),
        Point(c._1.x - d, c._1.y - c._3 - 1 + d))
      .filter(p => p.x >= 0 && p.x <= searchLimit && p.y >= 0 && p.y <= searchLimit)))

    val candidates = pts.filter(u => sbd.forall(a => (a._1 - u).manhattan() > a._3))

    println(candidates)

    candidates.head.x * 4000000L + candidates.head.y
//    val lines = input.split("\n").toList
//    val lineOfInterest = lines.head.toInt
//    val sensorsAndBeacons = lines.tail.map {
//      case sensorInfo(sx, sy, bx, by) => (Point(sx.toInt, sy.toInt), Point(bx.toInt, by.toInt))
//    }
//
//    val sensorsAndCoverage = sensorsAndBeacons.to(LazyList)
//      .map(sb => (sb._1,  (sb._1 - sb._2).manhattan()))
//
//    val overlaps = sensorsAndCoverage.flatMap(one =>
//      sensorsAndCoverage.filter(one!=_)
//        .map(another => (one._1, another._1, one._2 + another._2 - (one._1 - another._1).manhattan()))
//        .filter(_._3 >= 0)
//        .flatMap(abo => List(((abo._1, abo._2), abo._3), ((abo._2, abo._1), abo._3))))
//      .toMap
//    val graph = overlaps.keys.groupMap(_._1)(_._2)




    /*


    Graph theory approach
    There are 19 beacons in the world
    Find groups of 4 where neighbours at least touch, with at least one overlap, but opposites don't touch
    Search the contour of the smallest

    If not found like this, exhaustively search the edge





    Old idea I don't think it's right

    Find all the sensors whose area touches the edge
    Using roughly the part 1 algo find all the points on the edge where the beacon cannot be (~16MB - OK)
    if there is one (unlikely) you're done
    else I think the closest sensor to each edge gives a smaller rectangle which is guaranteed to be covered
    take each edge one unit inwards form this and repeat
      (take care to


     */
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

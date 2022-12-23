package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.{Queue, Seq}

object _18 extends AdventOfCodeSolution[Int] {

  private[y2022] case class Point(x:Int, y:Int, z:Int) {
    private def up = copy(y=y+1)
    private def down = copy(y=y-1)
    private def left = copy(x=x-1)
    private def right = copy(x=x+1)
    private def forth = copy(z=z+1)
    private def back = copy(z=z-1)

    private[y2022] def neighbours = LazyList(up, down, left, right, forth, back)
  }

  def _1(input: String): Int = {
    solve(input, getRock)
  }

  def _2(input: String): Int =
    solve(input, getAir)

  private def solve(input: String, getPointsOfInterest: Seq[Point] => (Seq[Point], Int)) = {
    val (pointsOfInterest, spuriousArea) = getPointsOfInterest(input.split("\n").to(LazyList)
      .map(_.split(",").map(_.toInt))
      .map(arr => Point(arr(0), arr(1), arr(2))))

    pointsOfInterest.foldLeft((-spuriousArea, Set.empty[Point]))((existing, point) => {
      ( existing._1 + 6 - 2 * point.neighbours.count(existing._2.contains),
        existing._2 + point)
      })
      ._1
  }

  private def getRock(rock: Seq[Point]) = (rock, 0)

  /**
   * @return A set of orthogonally connected air points and the exterior surface area of the containing box
   */
  private def getAir(rock: Seq[Point]) = {
    val minX = rock.map(_.x).min -1
    val maxX = rock.map(_.x).max +1
    val minY = rock.map(_.y).min -1
    val maxY = rock.map(_.y).max +1
    val minZ = rock.map(_.z).min -1
    val maxZ = rock.map(_.z).max +1

    def isInBox(point: Point) =
      minX <= point.x &&
      point.x <= maxX &&
      minY <= point.y &&
      point.y <= maxY &&
      minZ <= point.z &&
      point.z <= maxZ

    @tailrec
    def recu(remaining: Queue[Point], air: Set[Point]): Seq[Point] =
      if (remaining.isEmpty)
        air.toSeq
      else if(air contains remaining.head)
        recu(remaining.tail, air)
      else
        recu(remaining.tail ++ remaining.head.neighbours.filter(isInBox).filterNot(rock.contains), air + remaining.head)

    ( recu(Queue(Point(minX, minY, minZ)), Set.empty),
      2 * ((maxX - minX + 1) * (maxY - minY + 1) +
           (maxY - minY + 1) * (maxZ - minZ + 1) +
           (maxX - minX + 1) * (maxZ - minZ + 1)))
  }
}

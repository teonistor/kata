package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.collection.mutable

object _22 extends StandardAdventOfCodeSolution[Int] {

  private val splitter = "(\\d+),(\\d+),(\\d+)~(\\d+),(\\d+),(\\d+)".r

  private case class Brick(points: Set[(Int,Int,Int)]) {
    val lowestHeight = points.map(_._3).min
    lazy val planarProjection = points.map { case (x,y,_) => (x,y) }
    lazy val lowestProjection = points.map { case (x,y,_) => (x,y,lowestHeight)}
    lazy val floorProjection = lowestProjection.map { case (x,y,z) => (x,y,z-1)}

    def fall(howMuch: Int) = {
      if (howMuch < 0)
        println("Busted!")
      if (howMuch > 0)
        Brick(points.map {
          case (x, y, z) => (x, y, z - howMuch)
        })
      else
        this
    }
  }

  override def _1(input: String): Int = {
    // x,y,z follow the Blender convention
   val bricks = input.split('\n').iterator.map {
     case splitter(x1, y1, z1, x2, y2, z2) => //((x1.toInt,y1.toInt,z1.toInt),(x2.toInt,y2.toInt,z2.toInt))
       Brick((x1.toInt to x2.toInt).flatMap(x =>
         (y1.toInt to y2.toInt).flatMap(y =>
           (z1.toInt to z2.toInt).map((x, y, _)))).toSet)
   }.toSet

    val que = mutable.PriorityQueue.newBuilder(Ordering.by(-(_:Brick).lowestHeight))
      .addAll(bricks)
      .result()


    val settledBricks = fall(que)
    val leanOn = settledBricks.map(brick =>(brick, settledBricks.filter(_.points.exists(it => brick.floorProjection contains it))))
      .toMap

    val aye = leanOn.values.toSet
      .filter(_.size == 1)

//    println(s"Bricks size ${bricks.size}")
//    println(s"Settled bricks size ${settledBricks.size}")
//    println(s"LeanOn size ${leanOn.size}")
//
//    println(leanOn)
//    println(aye)

    bricks.size - aye.size
  }

  override def _2(input: String): Int = ???

  private def fall(floating: mutable.PriorityQueue[Brick], fallen: Set[Brick] = Set.empty, ground:Map[(Int,Int), Int] = Map.empty): Set[Brick]=
    if (floating.isEmpty)
      fallen
      else{
        val lowestBrick = floating.dequeue()
        val lowestPossible = lowestBrick.planarProjection.map(ground.getOrElse(_, 0)).max + 1
        val fallenBrick = lowestBrick.fall(lowestBrick.lowestHeight - lowestPossible)

        fall(floating, fallen + fallenBrick, ground ++ {
          val highestHeight = fallenBrick.points.map(_._3).max
          fallenBrick.planarProjection.map((_, highestHeight))
        })
//
//
//    bricks.find(brick => brick.lowestHeight > 1 && !bricks.exists(_.points overlaps brick.floorProjection)) match {
//      case None => bricks
//      case Some(brick) => fall(bricks - brick + Brick(brick.points.map {
//        case (x, y, z) => (x, y, z - 1)
//      }))
//    }
  }

  // 850 too high

  private implicit class SetsOverlap[T](val self:Set[T]) extends AnyVal {

    def overlaps(other:Set[T]) =
      self.exists(other)
  }
}

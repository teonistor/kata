package io.github.teonistor.adventofcode.y2023

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue
import scala.collection.mutable

object _22 extends StandardAdventOfCodeSolution[Int] {

  private val splitter = "(\\d+),(\\d+),(\\d+)~(\\d+),(\\d+),(\\d+)".r

  private case class Brick(points: Set[(Int,Int,Int)]) {
    private[_22] val lowestHeight = points.map(_._3).min
    private[_22] lazy val planarProjection = points.map { case (x,y,_) => (x,y) }
    private[_22] lazy val floorProjection = planarProjection.map { case (x,y) => (x,y,lowestHeight-1)}

    private[_22] def fall(howMuch: Int) =
      if (howMuch < 0)
        throw new IllegalArgumentException("Cannot fall up")
      else if (howMuch > 0)
        Brick(points.map {
          case (x, y, z) => (x, y, z - howMuch)
        })
      else
        this
  }

  override def _1(input: String): Int = {
    val downwards = computeLeaningMap(input)
    downwards.size - downwards.values.toSet.count(_.size == 1)
  }

  override def _2(input: String): Int = {
    val downwards = computeLeaningMap(input)
    val upwards = reverseDependence(downwards)

    @tailrec
    def ifItWereGone(disappearing:Queue[Brick], downwardsRemaining:Map[Brick,Set[Brick]]): Map[Brick, Set[Brick]] =
      if (disappearing.isEmpty)
        downwardsRemaining

      else {
        val nextToDisappear = disappearing.head
        val aboveTheDisappeared = upwards.getOrElse(nextToDisappear, Set.empty)
        val nextDownwards = aboveTheDisappeared.iterator
          // In other words: for every brick above the one about to be disappeared, we remove the one about to disappear from the downwards map
          .foldLeft(downwardsRemaining)((downwardsBeingUpdated, oneAboveTheDisappeared) => downwardsBeingUpdated.updatedWith(oneAboveTheDisappeared)(_.map(_ - nextToDisappear)))
        // then, all bricks with nothing left beneath them become candidates for disappearing (which is the same as falling for the purpose
        // of this quest). Note we don't filter them from the whole world, just from aboveTheDisappeared, so as not to drag unrelated bricks
        // in - in particular, bricks lying on the ground
        ifItWereGone(disappearing.tail ++ aboveTheDisappeared.filter(nextDownwards(_).isEmpty), nextDownwards)
      }

    downwards.keysIterator
      .map(disappearanceCandidate => ifItWereGone(Queue(disappearanceCandidate), downwards).count {
        case (possiblyFloating, beneath) => possiblyFloating.lowestHeight > 1 && beneath.isEmpty
      })
      .sum
  }

  private def computeLeaningMap(input:String)={
    val settledBricks = fall(mutable.PriorityQueue
      .newBuilder(Ordering.by(-(_: Brick).lowestHeight))
      .addAll(input.split('\n').iterator.map {
        // x,y,z follow the Blender convention
        case splitter(x1, y1, z1, x2, y2, z2) =>
          Brick((x1.toInt to x2.toInt).flatMap(x =>
            (y1.toInt to y2.toInt).flatMap(y =>
              (z1.toInt to z2.toInt).map((x, y, _)))).toSet)
      })
      .result())

    settledBricks.map(brick => (brick, settledBricks.filter(_.points.exists(brick.floorProjection.contains))))
      .toMap
  }

  @tailrec
  private def fall(floating: mutable.PriorityQueue[Brick], fallen: Set[Brick] = Set.empty, ground:Map[(Int,Int), Int] = Map.empty): Set[Brick]=
    if (floating.isEmpty)
      fallen
    else {
      val lowestBrick = floating.dequeue()
      val lowestPossible = lowestBrick.planarProjection.map(ground.getOrElse(_, 0)).max + 1
      val fallenBrick = lowestBrick.fall(lowestBrick.lowestHeight - lowestPossible)

      fall(floating, fallen + fallenBrick, ground ++ {
        val highestHeight = fallenBrick.points.map(_._3).max
        fallenBrick.planarProjection.map((_, highestHeight))
      })
    }

  @VisibleForTesting
  private[y2023] def reverseDependence[T](input: Map[T, Set[T]]) =
    input.iterator.flatMap {
        case (above, below) => below.iterator.map((_, above))
      }
      .to(Set)  // T shouldn't be erratic under equals()
      .groupMap(_._1)(_._2)
}

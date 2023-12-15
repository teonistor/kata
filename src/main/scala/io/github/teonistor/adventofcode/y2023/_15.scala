package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.util.concurrent.Callable
import java.util.concurrent.Executors.newFixedThreadPool
import scala.collection.immutable.VectorMap

object _15 extends StandardAdventOfCodeSolution[Long] {

  private val executor = newFixedThreadPool(8, (r: Runnable) => {
    val t = new Thread(r)
    t.setDaemon(true)
    t
  })

  override def _1(input: String): Long = {
    val strings = input.strip().split(',')
    val groupsOf = strings.length / 8 + 1

    strings.grouped(groupsOf)
      .map[Callable[Long]](ss => () =>
        ss.iterator.map(hash).sum)
      .toList
      .map(submit)
      .map(_.get())
      .sum
  }

  override def _2(input: String): Long = {
    val splitter = "([^=-]+)-|([^=-]+)=(\\d+)".r
    val strings = input.strip().split(',')
    val groupsOf = strings.length / 8 + 1

    strings.grouped(groupsOf)
      .map[Callable[Map[Long, List[(String, Option[Int])]]]](ss => () => ss.iterator.map {
          case splitter(null, label, focal) => (hash(label), (label, Some(focal.toInt)))
          case splitter(label, null, null) => (hash(label), (label, None))
        }
        .toList
        .groupMap(_._1)(_._2))
      .toList
      .map(submit)
      .flatMap(_.get())
      .foldLeft(Map.empty[Long, List[(String, Option[Int])]]) {
        case (acc, (key, list)) => acc.updatedWith(key)(_.map(_.appendedAll(list)).orElse(Some(list)))
      }
      .iterator
      .map[Callable[Long]](boxNumOps => () => boxNumOps match {
        case (boxNum, ops) => ops.foldLeft(VectorMap.empty[String, Int]) {
            case (acc, (key, focal)) => acc.updatedWith(key)(_=> focal)
          }
          .zipWithIndex
          .map {
            case ((_, focal), pos) => focal.toLong * (pos + 1)
          }
          .sum * (boxNum + 1)
      })
      .toList
      .map(submit)
      .map(_.get())
      .sum
  }

  private def hash(s: String) =
    s.foldLeft(0L)((l, c) => ((l + c) * 17) % 256L)

  private def submit[T](callable:Callable[T]) =
    executor submit callable
}

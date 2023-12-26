package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution
import io.github.teonistor.algohelper.LocalDiscCache

import scala.annotation.tailrec
import scala.collection.mutable

object _25 extends StandardAdventOfCodeSolution[Long] {

  private type Edge = (String,String)
  private type NodePair = (String,String)

  override def _1(input: String): Long = {
    val graph = input.split('\n').iterator.flatMap { row =>
        val from :: tos :: Nil = row.split(':').toList
        tos.strip().split(' ').iterator.flatMap(to =>
          Iterator(from -> to, to -> from))
      }
      .toSet
    println(s"DEBUG: ${graph.map(_._1).size} elements, ${graph.size} connections")

    val fanOut = graph
      .groupMap(_._1)(_._2)

    val everyInitialPath = everyPath(
      fanOut.toList.flatMap { case(from, tos) => tos.map((from, _)) },
      fanOut,
      graph.map(it => (it, Set(it))).toMap)

    // Using a cache here was a more than great idea, as it took an overnight run for the problem size :O
    val found = LocalDiscCache.computationSource(s"cache/2023_25_${input.length}", graph.iterator
      .filter { case (a,b) => a<b }  // No point checking edges from both directions
      .flatMap { edge1 =>
        val fanOut1 = fanOut.updatedWith(edge1._1)(_.map(_.excl(edge1._2))).updatedWith(edge1._2)(_.map(_.excl(edge1._1)))
        val initialPaths1 = everyInitialPath.filterNot(_._2.contains(edge1))
        val reconsPaths1 = discover(edge1, fanOut1, initialPaths1)

        if (reconsPaths1 contains edge1)
          reconsPaths1(edge1).iterator.flatMap { edge2 =>

            val fanOut2 = fanOut1.updatedWith(edge2._1)(_.map(_.excl(edge2._2))).updatedWith(edge2._2)(_.map(_.excl(edge2._1)))
            val initialPaths2 = initialPaths1.filterNot(_._2.contains(edge2))
            val reconsPaths2 = discover(edge2, fanOut2, initialPaths2)

            if (reconsPaths2 contains edge2)
              reconsPaths2(edge2).iterator.flatMap { edge3 =>

                val reconsPaths3 = discover(edge3,
                  fanOut2.updatedWith(edge3._1)(_.map(_.excl(edge3._2))).updatedWith(edge3._2)(_.map(_.excl(edge3._1))),
                  initialPaths2.filterNot(_._2.contains(edge3)))

                if (reconsPaths3 contains edge3)
                  None
                else
                  Some(List(edge1._1, edge1._2, edge2._1, edge2._2, edge3._1, edge3._2))
              }
            else
              None
          }
        else
          None
      }
      .next().mkString("\n"))
      // Back from the cached string
      .split('\n').toList
      .grouped(2)
      .flatMap {
        case from::to::Nil => Iterator((from,to), (to,from))
      }
      .toSet

    val fanOutWithFoundRemoved = graph
      .filterNot(found)
      .groupMap(_._1)(_._2)

    howManyReach(Set(found.head._1), fanOutWithFoundRemoved) * howManyReach(Set(found.head._2), fanOutWithFoundRemoved)
  }

  @tailrec
  private def everyPath(remaining: List[NodePair], fanOut: Map[String, Set[String]], acc: Map[NodePair, Set[Edge]]): Map[NodePair, Set[Edge]] =
    if (remaining.isEmpty)
      acc
    else {
      val next :: tail = remaining
      if (next._1 == next._2)
        everyPath(tail, fanOut, acc)

      else if (acc contains next)
        everyPath(tail, fanOut, acc)
      else if (acc contains (next._2, next._1))
        everyPath(tail, fanOut, acc.updated(next, acc((next._2, next._1)).map(swapNodes)))

      else
        everyPath(tail, fanOut, discover(next, fanOut, acc))
    }

  private def discover(quest: NodePair, fanOut: Map[String, Set[String]], acc: Map[NodePair, Set[Edge]]) = {

    val q = mutable.PriorityQueue.newBuilder(Ordering.by(-(_:(NodePair, Set[Edge]))._2.size))
      .addAll(acc)
      .result()

    @tailrec
    def discoverInner(accInner: Map[NodePair, Set[Edge]]=Map.empty): Map[NodePair, Set[Edge]] =
      if (accInner contains quest)
        acc ++ accInner
      else if (q.isEmpty)
        acc ++ accInner

      else {
        val ((from, mid), edges) = q.dequeue()
        val toAdd = fanOut(mid).toSeq
          .map(to => ((from, to), edges + ((mid, to))))
          .filter { case (k,v) => !accInner.get(k).exists(_.size <= v.size)}
        q.enqueue(toAdd:_*)
        discoverInner(accInner ++ toAdd)
      }

    acc ++ discoverInner()
  }

  private val swapNodes: NodePair => NodePair = {
    case (a,b) => (b,a)
  }

  @tailrec
  private def howManyReach(current:Set[String], fanOut: Map[String, Set[String]], acc: Set[String] = Set.empty): Int =
    if (current.isEmpty)
      acc.size
    else {
      val newacc = acc ++ current
      val nextcu = current flatMap fanOut
      howManyReach(nextcu filterNot newacc, fanOut, newacc)
    }

  override def _2(input: String): Long = -1
}

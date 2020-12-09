package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.max
import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _16 extends StandardAdventOfCodeSolution[Int] {

  private val valve = "Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? (.+)".r

  def _1(input: String): Int = {
    val valves = input.split("\n").to(LazyList)
      .map {
        case valve(name, flow, connections) => (name, flow.toInt, connections.split("[ ,]+")) }
      .sortBy(_._1)
//      .toList

    // Idea: There are less than 64 valves in both inputs, so connectivity (at least) can be modelled with bitmasks in a long

    val valveFlows = valves.map(v => (v._1, v._2)).toMap
    val valveConnections = valves.map(v => (v._1, v._3.toList)).toMap

    case class State (pressureRelieved: Int             = 0,
                      relievingNow: Int                 = 0,
                      timeLeft: Int                     = 30,
                      currentlyAt: String               = "AA",
                      visitedSinceLastOpen: Set[String] = Set.empty,
                      open: Set[String]                 = Set.empty)

    @tailrec
    def recu(q: Queue[State], r: Int): Int =
      if (q.isEmpty)
        r
      else {
        println(q.size)

        val current = q.head
        val newR = max(r, current.pressureRelieved)

        val na = LazyList.concat(
            if ((current.open contains current.currentlyAt) || valveFlows(current.currentlyAt) < 1) None
            else Some(State(
              current.pressureRelieved + current.relievingNow,
              current.relievingNow + valveFlows(current.currentlyAt),
              current.timeLeft - 1,
              current.currentlyAt,
              Set.empty,
              current.open incl current.currentlyAt
            )),
          valveConnections(current.currentlyAt)
            .map(State(
              current.pressureRelieved + current.relievingNow,
              current.relievingNow,
              current.timeLeft - 1,
              _,
              current.visitedSinceLastOpen + current.currentlyAt,
              current.open)))



        recu(q.tail.appendedAll(
            na.filter(_.timeLeft > 0).filterNot(s => s.visitedSinceLastOpen contains s.currentlyAt)),
          newR)
      }

//    val best = LazyList.iterate(Queue(State()))(q => q
//      .filter(_.timeLeft > 0)
//      // Prune loops where you go round without opening any valve and end up where you've been before
//      .filterNot(s => s.visitedSinceLastOpen contains s.currentlyAt)
//      .flatMap(current => {
//        val maybeOpen = if ((current.open contains current.currentlyAt) || valveFlows(current.currentlyAt) < 1) None
//        else Some(State(
//          current.pressureRelieved + current.relievingNow,
//          current.relievingNow + valveFlows(current.currentlyAt),
//          current.timeLeft - 1,
//          current.currentlyAt,
//          Set.empty,
//          current.open incl current.currentlyAt
//        ))
//
//        val moves = valveConnections(current.currentlyAt)
//          .map(State(
//            current.pressureRelieved + current.relievingNow,
//            current.relievingNow,
//            current.timeLeft - 1,
//            _,
//            current.visitedSinceLastOpen + current.currentlyAt,
//            current.open))
//
//        LazyList.concat(maybeOpen, moves)
//      }))
//
//    best.flatten
//      .drop(5_000_000)
//      .take(15_000_000)
//      .map(_.pressureRelieved)
//      .max

    recu(Queue(State()), 0)
  }


  def _2(input: String): Int =
    1
}

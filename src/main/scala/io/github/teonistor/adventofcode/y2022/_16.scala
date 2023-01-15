package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.collection.immutable.Queue

object _16 extends AdventOfCodeSolution[Int] {

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
                      timeLeft: Int                     = 30,
                      currentlyAt: String               = "AA",
                      visitedSinceLastOpen: Set[String] = Set.empty,
                      open: Set[String]                 = Set.empty)
    3

    val best = LazyList.iterate(Queue(State()))(q => q
      .filter(_.timeLeft > 0)
      // Prune loops where you go round without opening any valve and end up where you've been before
      .filterNot(s => s.visitedSinceLastOpen contains s.currentlyAt)
      .flatMap(current => {
        val relievedThisMinute = valveFlows.view.filterKeys(current.open.contains).values.sum

        val maybeOpen = if ((current.open contains current.currentlyAt) || valveFlows(current.currentlyAt) < 1) None
        else Some(State(
          current.pressureRelieved + relievedThisMinute,
          current.timeLeft - 1,
          current.currentlyAt,
          Set.empty,
          current.open incl current.currentlyAt
        ))

        val moves = valveConnections(current.currentlyAt)
          .map(State(
            current.pressureRelieved + relievedThisMinute,
            current.timeLeft - 1,
            _,
            current.visitedSinceLastOpen + current.currentlyAt,
            current.open))

        LazyList.concat(maybeOpen, moves)
      }))

    best.flatten
      .drop(5_000_000)
      .take(15_000_000)
      .map(_.pressureRelieved)
      .max


  }


  def _2(input: String): Int =
    1
}

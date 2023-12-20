package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _20 extends StandardAdventOfCodeSolution[Int] {

  private val parser = "(&|%|)([a-zA-Z]+)->(.+)".r

  override def _1(input: String): Int =
    repeatedly(input, 1000)

  override def _2(input: String): Int = ???

  case class Signal(high: Boolean, from: String, to: String)

  private trait Module {
    def receive(signal: Signal): (Module, Queue[Signal])
  }

  // Module implementations are case classes so we get equals() for free, which allows for easy checking of state repetition

  private case class Broadcaster(name: String, targets: List[String]) extends Module {
    override def receive(signal: Signal) =
      (this, targets.map(Signal(signal.high, name, _)).to(Queue))
  }

  private case class FlipFlop(name: String, targets: List[String], on: Boolean = false) extends Module {
    override def receive(signal: Signal) =
      if (signal.high)
        (this, Queue.empty)
      else
        (FlipFlop(name, targets, !on), targets.map(Signal(!on, name, _)).to(Queue))
  }

  private case class Conjunction(name:String, targets:List[String], sources:Set[String], haveBeenHigh:Set[String] = Set.empty) extends Module {
    override def receive(signal: Signal) = {
      val isBeingHigh = if (signal.high) haveBeenHigh + signal.from else haveBeenHigh - signal.from
      val sendHigh = sources != isBeingHigh
      (Conjunction(name, targets, sources, isBeingHigh), targets.map(Signal(sendHigh, name, _)).to(Queue))
    }
  }

  private def readInput(input:String) = {
    val partial = input.split('\n')
      .map(_.replace(" ", "") match {
        case parser(typeStr, name, targetsStr) => (typeStr, name, targetsStr.split(',').toList)
      })

    // First figure out sources because conjunction modules will need them
    val sources = partial.iterator.flatMap {
        case (_, source, targets) => targets.map((_, source))
      }
      .to(Set)
      .groupMap(_._1)(_._2)

    partial.iterator.map {
        case("", name, targets) => (name, Broadcaster(name, targets))
        case("%", name, targets) => (name, FlipFlop(name, targets))
        case("&", name, targets) => (name, Conjunction(name, targets, sources(name)))
      }
      .toMap[String,Module]
  }

  @tailrec
  private def propagate(state: Map[String, Module], signals: Queue[Signal], highSentSoFar: Int = 0, lowSentSoFar: Int = 0, buttonPressesSoFar: Int): (Map[String, Module], Int, Int) =
    if (signals.isEmpty)
      (state, highSentSoFar, lowSentSoFar)

    else {
      val nextSignal = signals.head
      if (nextSignal.to == "rx" && !nextSignal.high)
        println(s"!!! $buttonPressesSoFar")

      val (updatedState, newSignals) = state.get(nextSignal.to)
        .map(_.receive(nextSignal) match {
          case (updatedModule, newSignals) => (state.updated(nextSignal.to, updatedModule), newSignals)
        })
        .getOrElse((state, Queue.empty))
      propagate(updatedState,
        signals.tail ++ newSignals,
        if (nextSignal.high) highSentSoFar + 1 else highSentSoFar,
        if (nextSignal.high) lowSentSoFar else lowSentSoFar + 1,
        buttonPressesSoFar)
    }

  private def repeatedly(input: String, repetitions: Int) = {
    val (finalState, highSignalsSent, lowSignalsSent) = (1 to repetitions).foldLeft((readInput(input), 0, 0)) {
      case ((state, highSignalsSent, lowSignalsSent), i) => propagate(state, Queue(Signal(false, "button", "broadcaster")), highSignalsSent, lowSignalsSent, i)
    }
    highSignalsSent * lowSignalsSent
  }
}
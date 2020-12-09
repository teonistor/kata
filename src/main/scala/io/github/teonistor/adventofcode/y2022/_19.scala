package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _19 extends StandardAdventOfCodeSolution[Int] {

  private val blueprint = "Blueprint (\\d+):\\s+Each ore robot costs (\\d+) ore.\\s+Each clay robot costs (\\d+) ore.\\s+Each obsidian robot costs (\\d+) ore and (\\d+) clay.\\s+Each geode robot costs (\\d+) ore and (\\d+) obsidian.\\s*".r

  private case class State(
      ore: Int           = 0,
      clay: Int          = 0,
      obsidian: Int      = 0,
      geode: Int         = 0,
      oreRobot: Int      = 1,
      clayRobot: Int     = 0,
      obsidianRobot: Int = 0,
      geodeRobot: Int    = 0,
      timeLeft: Int      = 24)

  def _1(input: String): Int = {
//    val blueprints = blueprint.findAllMatchIn(input)
//      .map(m => (m.group(1).toInt, Map(
//        "ore" -> Map("ore" -> m.group(2).toInt),
//        "clay" -> Map("ore" -> m.group(3).toInt),
//        "obsidian" -> Map("ore" -> m.group(4).toInt, "clay" -> m.group(5).toInt),
//        "geode" -> Map("ore" -> m.group(6).toInt, "obsidian" -> m.group(7).toInt))))
//      .toMap

    val blueprints = blueprint.findAllMatchIn(input)
      .map/*[(Int, Seq[(State=>Boolean, State=>State)])]*/(m => {
        val oreOreCost = m.group(2).toInt
        val clayOreCost =  m.group(3).toInt
        val obsidianOreCost = m.group(4).toInt
        val obsidianClayCost = m.group(5).toInt
        val geodeOreCost =  m.group(6).toInt
        val geodeObsidianCost = m.group(7).toInt

        (m.group(1).toInt, List(
          ((_: State).ore >= oreOreCost) ->
            ((s: State) => s.copy(oreRobot = s.oreRobot + 1, ore = s.ore - oreOreCost)),
          ((_: State).ore >= clayOreCost) ->
            ((s: State) => s.copy(clayRobot = s.clayRobot + 1, ore = s.ore - clayOreCost)),
          ((s: State) => s.ore >= obsidianOreCost && s.clay >= obsidianClayCost) ->
            ((s: State) => s.copy(obsidianRobot = s.obsidianRobot + 1, ore = s.ore - obsidianOreCost, clay = s.clay - obsidianClayCost)),
          ((s: State) => s.ore >= geodeOreCost && s.obsidian >= geodeObsidianCost) ->
            ((s: State) => s.copy(geodeRobot = s.geodeRobot + 1, ore = s.ore - geodeOreCost, obsidian = s.obsidian - geodeObsidianCost))))


//        "ore" -> Map("ore" -> )
//        ,
//        "clay" -> Map("ore" ->)
//        ,
//        "obsidian" -> Map("ore" -> , "clay" -> )
//        ,
//        "geode" -> Map("ore" ->, "obsidian" -> )
//        ) )
      })
      .toMap

    println(s"Proceeding with ${blueprints.size} blueprints")

    blueprints
      .to(LazyList)
      .map(blueprint => {
      val (index, transitions) = blueprint

      val best = LazyList.iterate(Queue(State()))(q => q
        .filter(_.timeLeft > 0)
        .flatMap (state => {

        @tailrec
        def allBuildables(toAnalyse: Queue[State],
                          candidates: List[State]): List[State] = {
          if (toAnalyse.isEmpty)
            candidates
          else {
            val next = transitions.filter(_._1(toAnalyse.head)).map(_._2(toAnalyse.head))
            allBuildables(toAnalyse.tail ++ next, next reverse_::: candidates)
          }
        }

        val ab = allBuildables(Queue(state), List(state))

        ab.map(s => State(
          ore = s.ore + state.oreRobot,
          clay = s.clay + state.clayRobot,
          obsidian = s.obsidian + state.obsidianRobot,
          geode = s.geode + state.geodeRobot,
          timeLeft = state.timeLeft - 1))
        // mining as per original
        // time passes

      }))
        .flatten
//        .take(1_000_000)
        .map(_.geode)
        .max

        index * best
    })
      .sum


  }

  def _2(input: String): Int =
    1
}

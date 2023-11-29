package io.github.teonistor.adventofcode

import io.github.teonistor.algohelper.LocalDiscCache
import org.scalactic.source.Position
import org.scalatest.funsuite.AnyFunSuite

import java.lang.String.{format => sf}
import scala.util.Try

abstract class AdventOfCodeTestBase extends AnyFunSuite {

  def autorun[O](solution: AdventOfCodeSolution[O], exampleOutputOne: O, exampleOutputTwo: O, exampleInput: String, exampleInputTwoOverride: String = null)(implicit pos: Position): Unit = {

    lazy val problemInput = obtainProblemInput(solution)
    val name = sf("Year %04d day %02d part %d %s", solution.year, solution.day, _:Int, _:String)

    timedTest(name(1, "example"), solution._1(exampleInput), exampleOutputOne)(pos)
    timedTest(name(1, "problem"), solution._1(problemInput))(pos)
    timedTest(name(2, "example"), solution._2(Option(exampleInputTwoOverride).getOrElse(exampleInput)), exampleOutputTwo)(pos)
    timedTest(name(2, "problem"), solution._2(problemInput))(pos)
  }

  private def obtainProblemInput(solution: AdventOfCodeSolution[_]) =
    Try(LocalDiscCache.computationSource("cache/session", cancel))
      .flatMap(cookie => Try(LocalDiscCache.webSource(
        sf("cache/%04d_%02d", solution.year, solution.day),
        sf("https://adventofcode.com/%d/day/%d/input", solution.year, solution.day),
        ("session", cookie)))
        .recover { e =>
          println(s"Could not fetch problem input because $e")
          cancel
        }).get

  def timedTest[O](name: String, computation: => O, expected: O = null)(implicit pos: Position): Unit =
    test(name) {
      val startTime = System.currentTimeMillis()
      val actual = computation
      val endTime = System.currentTimeMillis()
      printf("%s [%8dms]: %s%n", name, endTime - startTime, actual)

      if (expected != null)
        assert(actual == expected)
    }(pos)
}

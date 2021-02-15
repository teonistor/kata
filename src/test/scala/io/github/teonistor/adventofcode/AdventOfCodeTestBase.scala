package io.github.teonistor.adventofcode

import org.scalatest.funsuite.AnyFunSuite

abstract class AdventOfCodeTestBase extends AnyFunSuite {

  def runOne[O](name: String, kind: String, solution: => O): O = {
    val startTime = System.currentTimeMillis()
    val actual = solution
    val endTime = System.currentTimeMillis()
    printf("%s - %s [%6dms]: %s%n", name, kind, endTime - startTime, actual)
    actual
  }

  def testAndRun[O](day: Int, part: Int, solution: String => O, exampleInput: String, exampleOutput: O, problemInput: String) {
    val name = "Day %02d part %d".format(day, part)
    test(name) {

      assert(runOne(name, "example", solution(exampleInput)).equals(exampleOutput))
      runOne(name, "problem", solution(problemInput))
    }
  }

  def testAndRun[O](day: Int, solutionOne: String => O, solutionTwo: String => O, exampleInput: String, exampleOutputOne: O, exampleOutputTwo: O, problemInput: String) {
    testAndRun(day, solutionOne, solutionTwo, exampleInput, exampleOutputOne, exampleInput, exampleOutputTwo, problemInput)
  }

  def testAndRun[O](day: Int, solutionOne: String => O, solutionTwo: String => O, exampleInputOne: String, exampleOutputOne: O, exampleInputTwo: String, exampleOutputTwo: O, problemInput: String) {
    testAndRun(day, 1, solutionOne, exampleInputOne, exampleOutputOne, problemInput)
    testAndRun(day, 2, solutionTwo, exampleInputTwo, exampleOutputTwo, problemInput)
  }
}

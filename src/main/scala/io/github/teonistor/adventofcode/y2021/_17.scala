package io.github.teonistor.adventofcode.y2021

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _17 extends StandardAdventOfCodeSolution[Int] {
  private val targetArea = "target area: x=([\\d-]+)..([\\d-]+), y=([\\d-]+)..([\\d-]+)".r

  def _1(input: String): Int = {
    /* At some point the probe will be at the same height (0) as it started, due to the discrete nature of the problem.
     * We don't even need to care about the X coordinate
     */
    val (_, _, y1, _) = readInput(input)

    val diffDown = -y1
    diffDown * (diffDown - 1) / 2
  }

  def _2(input: String): Int = {
    val (x1, x2, y1, y2) = readInput(input)

    // The smallest possible x velocity is n such that 1+2+...+n is greater or equal to the near end
    val minX = calculateMinimumX(x1)
    // The largest possible x velocity is just the far end
    val maxX = x2
    // The smallest possible y velocity is the far end
    val minY = y1
    // The biggest possible y velocity is one less than the absolute value of the far end (shoot up, don't overshoot when falling back down)
    val maxY = -y1 - 1

    val feasibleStepsX = (minX to maxX).map(computeFeasibleStepsX(_, x1, x2))
    val feasibleStepsY = (minY to maxY).map(computeFeasibleStepsY(_, y1, y2))

    feasibleStepsX.flatMap(stepX =>
      feasibleStepsY.map(stepY =>
        if (overlap(stepX, stepY)) 1 else 0))
      .sum
  }

  private def readInput(input: String) = input match {
    case targetArea(x1s, x2s, y1s, y2s) => (x1s.toInt, x2s.toInt, y1s.toInt, y2s.toInt)
  }

  @VisibleForTesting
  private[y2021] def calculateMinimumX(x: Int) =
    /* n (n + 1)
     * ————————— >= x  =>  n² + n - 2x >= 0
     *     2
     * Δ = 1 + 8x > 0 since x >= 0
     *        -1 ± √Δ
     * roots: ——————— and we only care about the positive root since n >= 0
     *           2
     *      -1 + √1 + 8x
     * n >= ————————————
     *           2
     */
    Math.ceil((Math.sqrt(1 + 8 * x) - 1) / 2).toInt

  @tailrec
  private def computeFeasibleStepsX(velocity: Int, x1: Int, x2: Int, x: Int = 0, stepsTaken: Int = 0, minSteps: Int = Integer.MAX_VALUE, maxSteps: Int = Integer.MIN_VALUE) : (Int, Int) =
    if (x > x2)
      (minSteps, maxSteps)

    else if (velocity == 0)
      (minSteps, Integer.MAX_VALUE)

    else if (x1 <= x)
      computeFeasibleStepsX(
        velocity - 1,
        x1, x2,
        x + velocity,
        stepsTaken + 1,
        Math.min(minSteps, stepsTaken),
        Math.max(maxSteps, stepsTaken))

    else
      computeFeasibleStepsX(
        velocity - 1,
        x1, x2,
        x + velocity,
        stepsTaken + 1,
        minSteps,
        maxSteps)

  @tailrec
  private def computeFeasibleStepsY(velocity: Int, y1: Int, y2: Int, y: Int = 0, stepsTaken: Int = 0, minSteps: Int = Integer.MAX_VALUE, maxSteps: Int = Integer.MIN_VALUE) : (Int, Int) =
    if (y < y1)
      (minSteps, maxSteps)

    else if (y <= y2)
      computeFeasibleStepsY(
        velocity - 1,
        y1, y2,
        y + velocity,
        stepsTaken + 1,
        Math.min(minSteps, stepsTaken),
        Math.max(maxSteps, stepsTaken))

    else
      computeFeasibleStepsY(
        velocity - 1,
        y1, y2,
        y + velocity,
        stepsTaken + 1,
        minSteps,
        maxSteps)

  def overlap(a: (Int, Int), b: (Int, Int)): Boolean =
    a._1 <= b._2 && b._1 <= a._2
}

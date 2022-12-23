package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution
import org.apache.commons.lang3.StringUtils.rightPad

import scala.annotation.tailrec

object _22 extends AdventOfCodeSolution[Int] {

  private val instruction = "(\\d+)|([LR])".r

  private[y2022] case class Situation(
      height: Int, width: Int,
      i: Int, j: Int,
      rot: Int = 0) {

    private[y2022] def move() = rot match {
      case 0 => copy(j = j + 1)
      case 1 => copy(i = i + 1)
      case 2 => copy(j = j - 1)
      case 3 => copy(i = i - 1)
    }

    private[y2022] def safeMove() = rot match {
      case 0 => copy(j = (j + 1) % width)
      case 1 => copy(i = (i + 1) % height)
      case 2 => copy(j = (j - 1 + width) % width)
      case 3 => copy(i = (i - 1 + height) % height)
    }

    private[y2022] def turnLeft() = copy(rot = (rot + 3) % 4)
    private[y2022] def turnRight() = copy (rot = (rot + 1) % 4)
  }

  def _1(input: String): Int =
    solve(input, None)

  def _2(input: String): Int =
    solve(input, Some(if (input.length < 250) lookupWarpSmall else lookupWarpLarge))

  private def lookupWarpSmall(i: Int, j: Int, rot: Int) =
    (rot, i / 4, j / 4) match {
      case (0, 0, _) => (11 - i, 15, 2)
      case (0, 1, _) => (8, 19 - i, 1)
      case (0, 2, _) => (11 - i, 11, 2)
      case (1, _, 0) => (11, 11 - j, 3)
      case (1, _, 1) => (15 - j, 8, 0)
      case (1, _, 2) => (7, 11 - j, 3)
      case (1, _, 3) => (19 - j, 0, 0)
      case (2, 0, _) => (4, 4 + i, 1)
      case (2, 1, _) => (11, 19 - i, 3)
      case (2, 2, _) => (7, 15 - i, 3)
      case (3, _, 0) => (0, 11 - j, 1)
      case (3, _, 1) => (j - 4, 8, 0)
      case (3, _, 2) => (4, 11 - j, 1)
      case (3, _, 3) => (19 - j, 11, 2)
    }

  // This is specific to my input. Good enough for the demo :P
  private def lookupWarpLarge(i: Int, j: Int, rot: Int) =
    (rot, i / 50, j / 50) match {
      case (0, 0, _) => (149 - i, 99, 2)
      case (0, 1, _) => (49, i + 50, 3)
      case (0, 2, _) => (149 - i, 149, 2)
      case (0, 3, _) => (149, i - 100, 3)
      case (1, _, 0) => (0, 100 + j, 1)
      case (1, _, 1) => (100 + j, 49, 2)
      case (1, _, 2) => (j - 50, 99, 2)
      case (2, 0, _) => (149 - i, 0, 0)
      case (2, 1, _) => (100, i - 50, 1)
      case (2, 2, _) => (149 - i, 50, 0)
      case (2, 3, _) => (0, i - 100, 1)
      case (3, _, 0) => (j + 50, 50, 0)
      case (3, _, 1) => (j + 100, 0, 0)
      case (3, _, 2) => (199, j - 100, 3)
    }

  private def solve(input: String, lookupWarp: Option[(Int, Int, Int) => (Int, Int, Int)]): Int = {
    val (chart, instructions) = parseInput(input.split("\n\n"))

    @tailrec
    def traversePath(instructions: Seq[Either[Int, Situation => Situation]], current: Situation): Situation =
      (instructions.headOption, lookupWarp) match {
        case (None, _) => current
        case (Some(Left(0)), _) => traversePath(instructions.tail, current)

        // For part 1, keep going in the same direction until you get out of the void
        case (Some(Left(n)), None) =>
          val next = LazyList.iterate(current)(_.safeMove()).tail
            .find(oneStep => chart(oneStep.i)(oneStep.j) != ' ').get
          chart(next.i)(next.j) match {
            case '.' => traversePath(Left(n - 1) +: instructions.tail, next)
            case '#' => traversePath(instructions.tail, current)
          }

        // For part 2, use the lookups to get out of the void
        case (Some(Left(n)), Some(lookup)) =>
          val next = current.move()
          chart.lift(next.i)
            .flatMap(_.lift(next.j)) match {
              case Some('.') => traversePath(Left(n - 1) +: instructions.tail, next)
              case Some('#') => traversePath(instructions.tail, current)
              case _ =>
                val (warpedI, warpedJ, warpedRot) = lookup(next.i, next.j, next.rot)
                chart(warpedI)(warpedJ) match {
                  case '.' => traversePath(Left(n - 1) +: instructions.tail, current.copy(i = warpedI, j = warpedJ, rot = warpedRot))
                  case '#' => traversePath(instructions.tail, current)
                }
            }

        case (Some(Right(op)), _) => traversePath(instructions.tail, op(current))
      }

    finalise(traversePath(
      instructions,
      Situation(chart.length, chart(0).length, 0, chart(0).indices.find(chart(0)(_) != ' ').get)))
  }

  private def parseInput(input: Array[String]) = {
    val lines = input(0).split("\n")
    val width = lines.map(_.length).max

    ( lines.map(rightPad(_, width)),
      instruction.findAllMatchIn(input(1)).to(LazyList)
        .map(m => (m.group(1), m.group(2)))
        .map[Either[Int, Situation => Situation]] {
          case (n, null) => Left(n.toInt)
          case (null, "L") => Right(_.turnLeft())
          case (null, "R") => Right(_.turnRight())
        })
  }

  private def finalise(end: Situation) = {
    // In the problem statement, the chart is 1-indexed but the rotations are 0-indexed
    (end.i + 1) * 1000 + (end.j + 1) * 4 + end.rot
  }
}

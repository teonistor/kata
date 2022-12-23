package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.annotation.tailrec

object _22 extends AdventOfCodeSolution[Int] {

  private val instruction = "(\\d+)|([LR])".r

  private case class Composure(
      height: Int, width: Int,
      i: Int, j: Int,
      rot: Int = 0) {

    def move() = rot match {
      case 0 => copy(j = j + 1)
      case 1 => copy(i = i + 1)
      case 2 => copy(j = j - 1)
      case 3 => copy(i = i - 1)
    }

    def safeMove() = rot match {
      case 0 => copy(j = (j + 1) % width)
      case 1 => copy(i = (i + 1) % height)
      case 2 => copy(j = (j - 1 + width) % width)
      case 3 => copy(i = (i - 1 + height) % height)
    }

    def left() = copy(rot = (rot + 3) % 4)
    def right() = copy (rot = (rot + 1) % 4)
  }

  def _1(input: String): Int = {
    val inputParts = input.split("\n\n")
    val map = {
      val temp = inputParts(0).split("\n")
      val width = temp.map(_.length).max
      temp.map(line => Array.tabulate(width)(line.lift(_).getOrElse(' ')))
    }
    val instructions = instruction.findAllMatchIn(inputParts(1)).to(LazyList)
      .map(m => (m.group(1), m.group(2)))
      .map[Either[Int, Composure=>Composure]] {
        case (n, null) => Left(n.toInt)
        case (null, "L") => Right(_.left())
        case (null, "R") => Right(_.right())
      }

    @tailrec
    def thing(instructions: Seq[Either[Int, Composure=>Composure]], c: Composure): Composure = {
      println(s"${c.i}, ${c.j}")

      if (instructions.isEmpty)
        c
      else instructions.head match {
        case Left(0) => thing(instructions.tail, c)
        case Left(n) =>
          val oneStep = LazyList.iterate(c.safeMove())(_.safeMove())
            .find(oneStep => map(oneStep.i)(oneStep.j) != ' ').get
          map(oneStep.i)(oneStep.j) match {
            case '.' => thing(Left(n - 1) +: instructions.tail, oneStep)
            case '#' => thing(instructions.tail, c)
          }
        case Right(op) => thing(instructions.tail, op(c))

      }
    }
    val end = thing(instructions, Composure(map.length, map(0).length, 0, map(0).indices.find(map(0)(_) != ' ').get))
    println(end)

    (end.i + 1) * 1000 + (end.j + 1) * 4 + end.rot
  }

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
  def lookupWarpLarge(i: Int, j: Int, rot: Int) =
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

  def _2(input: String): Int = {

    val inputParts = input.split("\n\n")
    val map = {
      val temp = inputParts(0).split("\n")
      val width = temp.map(_.length).max
      temp.map(line => Array.tabulate(width)(line.lift(_).getOrElse(' ')))
    }
    val instructions = instruction.findAllMatchIn(inputParts(1)).to(LazyList)
      .map(m => (m.group(1), m.group(2)))
      .map[Either[Int, Composure => Composure]] {
        case (n, null) => Left(n.toInt)
        case (null, "L") => Right(_.left())
        case (null, "R") => Right(_.right())
      }

    val lookupWarp = if (input.length < 250) lookupWarpSmall _ else lookupWarpLarge _

    @tailrec
    def thing(instructions: Seq[Either[Int, Composure => Composure]], c: Composure): Composure = {
      println(s"${c.i}, ${c.j}")

      if (instructions.isEmpty)
        c
      else instructions.head match {
        case Left(0) => thing(instructions.tail, c)
        case Left(n) =>
          val oneStep = c.move()
          map.lift(oneStep.i)
            .flatMap(_.lift(oneStep.j)) match {
              case Some('.') => thing(Left(n - 1) +: instructions.tail, oneStep)
              case Some('#') => thing(instructions.tail, c)
              case _ =>
                val (warpedI, warpedJ, warpedRot) = lookupWarp(oneStep.i, oneStep.j, oneStep.rot)
                println(s"i=$warpedI, j=$warpedJ, rot=$warpedRot")
                map(warpedI)(warpedJ) match {
                  case '.' => thing(Left(n - 1) +: instructions.tail, c.copy(i = warpedI, j = warpedJ, rot = warpedRot))
                  case '#' => thing(instructions.tail, c)
                }
          }
        case Right(op) => thing(instructions.tail, op(c))

      }
    }

    val end = thing(instructions, Composure(map.length, map(0).length, 0, map(0).indices.find(map(0)(_) != ' ').get))
    println(end)

    (end.i + 1) * 1000 + (end.j + 1) * 4 + end.rot
  }
}

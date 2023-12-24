package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _23 extends StandardAdventOfCodeSolution[Int] {

  override def _1(input: String): Int = {
    val map = input.split('\n')
      .to(Vector)
      .map(_.to(Vector))

    longMazeSlippery(0, map(0) indexOf '.', map, 0)
  }

  override def _2(input: String): Int = {
    val map = input.split('\n')
      .to(Vector)
      .map(_.to(Vector))

    longMaze(Queue(Parameter(0, map(0) indexOf '.', map, 0)), -1)
  }

  private case class Parameter(row:Int, col:Int, map:Vector[Vector[Char]], steps:Int)

  @tailrec
  private def longMaze(queue:Queue[Parameter], maxSoFar:Int):Int = {


    if (queue.isEmpty)
      maxSoFar
    else {
      val Parameter(row, col, map, steps) = queue.head

      if (row < 0)
        longMaze(queue.tail, maxSoFar)
      else
        map(row)(col) match {
          case '#' => longMaze(queue.tail, maxSoFar)
          case _=>
            if (row == map.length - 1)
              longMaze(queue.tail, steps max maxSoFar)
              else {
                val nextMap = makeWall(map, row, col)
                longMaze(queue.tail ++ Iterator(
                  Parameter(row, col + 1, nextMap, steps + 1),
                  Parameter(row + 1, col, nextMap, steps + 1),
                  Parameter(row, col - 1, nextMap, steps + 1),
                  Parameter(row - 1, col, nextMap, steps + 1)), maxSoFar)
              }
        }

//        if (row == map.length - 1) {
//
//        if (steps == 156) {
//          println("--------")
//          map.foreach(u => println(u.mkString))
//          println("--------")
//        }

//      }
//      else
//        map(row)(col) match {
//          case '#' => longMaze(queue.tail, maxSoFar)
//          case _=>
//            val nextMap = makeWall(map, row, col)
//            longMaze(queue.tail ++ Iterator(
//              Parameter(row, col + 1, nextMap, steps + 1),
//              Parameter(row + 1, col, nextMap, steps + 1),
//              Parameter(row, col - 1, nextMap, steps + 1),
//              Parameter(row - 1, col, nextMap, steps + 1)), maxSoFar)
//        }
    }
  }

  private def longMazeSlippery(row:Int, col:Int, map:Vector[Vector[Char]], steps:Int):Int =
    if (row < 0) // CBA
      -1
    else if (row == map.length - 1)
      steps
    else {
      val nextMap = makeWall(map, row, col)
      map(row)(col) match {
        case '#' => -1
        case '>' => longMazeSlippery(row, col+1, nextMap, steps+1)
        case 'v' => longMazeSlippery(row+1, col, nextMap, steps+1)
        case '<' => longMazeSlippery(row, col-1, nextMap, steps+1)
        case '^' => longMazeSlippery(row-1, col, nextMap, steps+1)
        case '.' => Iterator(
            longMazeSlippery(row, col+1, nextMap, steps+1),
            longMazeSlippery(row+1, col, nextMap, steps+1),
            longMazeSlippery(row, col-1, nextMap, steps+1),
            longMazeSlippery(row-1, col, nextMap, steps+1))
          .max
      }
    }

  private def makeWall(map: Vector[Vector[Char]], row: Int, col: Int) =
    map.updated(row, map(row).updated(col, '#'))
}

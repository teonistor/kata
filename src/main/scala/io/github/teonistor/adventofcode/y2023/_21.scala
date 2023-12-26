package io.github.teonistor.adventofcode.y2023

import com.google.common.annotations.VisibleForTesting
import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec
import scala.collection.mutable

object _21 extends StandardAdventOfCodeSolution[Int] {

  private val powersOf2 = List.iterate(1, 25)(_*2).reverse

  override def _1(input: String): Int = {

    // moveBounded(map, 64, Set(start))
    //      .size

    ???
  }

  override def _2(input: String): Int = {
    solveUnbounded(input, 26501365)
  }

  @VisibleForTesting
  private[y2023] def solveUnbounded(input: String, steps:Int): Int = {
    val mapChr = input.split('\n')
    val start = mapChr.indices
      .flatMap(row => mapChr(row).indices
      .find(mapChr(row)(_) == 'S')
      .map((row,_)))
      .head
    val map = mapChr
      .map(line => line.toCharArray.map(_ != '#'))

    val memo = mutable.Map.empty[(Int, Int, Int), Set[(Int, Int)]]

    def moveUnbounded(current: Set[(Int, Int)], steps: Int): Set[(Int, Int)] = {
//      val diff = steps - stepsso

      if (steps < 1)
        current
      else
        current.flatMap { case (currentRow, currentCol) =>
          val normalisedRow = currentRow %% map.length
          val normalisedCol = currentCol %% map(0).length

          def breakdownMove() = {
            powersOf2.find(_<steps).fold(
              Set((currentRow - 1, currentCol), (currentRow + 1, currentCol), (currentRow, currentCol - 1), (currentRow, currentCol + 1))
                .filter {
                  case (nr, nc) => map(nr %% map.length)(nc %% map(0).length)
                }
            )(power => moveUnbounded(moveUnbounded(current, power), steps - power))

//            if (steps > 65536)
//              moveUnbounded(moveUnbounded(current, 65536), steps - 65536)
//            else if (steps > 256)
//              moveUnbounded(moveUnbounded(current, 256), steps - 256)
//            else if (steps > 16)
//              moveUnbounded(moveUnbounded(current, 16), steps - 16)
//            else if (steps > 4)
//              moveUnbounded(moveUnbounded(current, 4), steps - 4)
//            else if (steps > 2)
//              moveUnbounded(moveUnbounded(current, 2), steps - 2)
//            else if (steps > 1)
//              moveUnbounded(moveUnbounded(current, 1), steps - 1)
//            else
//              Set((currentRow - 1, currentCol), (currentRow + 1, currentCol), (currentRow, currentCol - 1), (currentRow, currentCol + 1))
//                .filter {
//                  case (nr, nc) => map(nr %% map.length)(nc %% map(0).length)
//                }
          }

          memo.getOrElseUpdate((currentRow, currentCol, steps), {
            if (normalisedRow == currentRow && normalisedCol == currentCol)
              breakdownMove()
            else
              moveUnbounded(Set((normalisedRow, normalisedCol)), steps).map {
                case (row, col) => (row - normalisedRow + currentRow, col - normalisedCol + currentCol)
              }
          })

//          if (normalisedRow == currentRow && normalisedCol == currentCol)
//            memo.getOrElseUpdate((currentRow, currentCol, steps), breakdownMove())
//          else
//            moveUnbounded(Set((normalisedRow, normalisedCol)), steps).map {
//              case (row, col) => (row - normalisedRow + currentRow, col - normalisedCol + currentCol)
//            }
        }
    }

    moveUnbounded(Set(start), steps)
      .size
  }

  @tailrec
  private def moveBounded(map:IndexedSeq[String], remaining: Int, current: Set[(Int, Int)]): Set[(Int, Int)] =
    if (remaining <1 )
      current
    else moveBounded(map, remaining - 1, current.flatMap {
      case (row, col) => Iterator((row - 1, col), (row + 1, col), (row, col - 1), (row, col + 1))
        .filter {
          case (nr, nc) => map.lift(nr).exists(_.lift(nc).exists(_ != '#'))
        }
    })

  private implicit class DefinitelyPositiveMod(val self: Int) extends AnyVal {
    private[_21] def %%(other: Int) = (self % other + other) % other
  }
}
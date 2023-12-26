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

    val memo = mutable.Map.empty[(Int, Int, Int), Map[(Int, Int), Set[(Int, Int)]]]

    def moveUnbounded(current: Map[(Int, Int), Set[(Int, Int)]], steps: Int): Map[(Int, Int), Set[(Int, Int)]] = {
//      val diff = steps - stepsso
      println("Looking for steps " + steps)

      if (steps < 1)
        current
      else
        current.to(LazyList).flatMap { case ((currentRow, currentCol), boards) =>
//          val normalisedRow = currentRow %% map.length
//          val normalisedCol = currentCol %% map(0).length

          memo.getOrElseUpdate((currentRow, currentCol, steps), {
            println("Computing for steps " + steps)
            powersOf2.find(_ < steps).fold(
              Set((currentRow - 1, currentCol), (currentRow + 1, currentCol), (currentRow, currentCol - 1), (currentRow, currentCol + 1))
                .map {
                  case (nr, nc) => ((nr %% map.length, nc %% map(0).length), (nr,nc))
                }
                .filter {
                  case ((nnr, nnc),_) => map(nnr)(nnc)
                }
                .groupMap(_._1){ case ((nnr,nnc),(nr,nc)) => (sgn(nnr,nr), sgn(nnc,nc)) }
            )(power => moveUnbounded(moveUnbounded(current, power), steps - power))




//            if (normalisedRow == currentRow && normalisedCol == currentCol)
//              powersOf2.find(_ < steps).fold(
//                Set((currentRow - 1, currentCol), (currentRow + 1, currentCol), (currentRow, currentCol - 1), (currentRow, currentCol + 1))
//                  .filter {
//                    case (nr, nc) => map(nr %% map.length)(nc %% map(0).length)
//                  }
//              )(power => moveUnbounded(moveUnbounded(current, power), steps - power))
//            else
//              moveUnbounded(Set((normalisedRow, normalisedCol)), steps).map {
//                case (row, col) => (row - normalisedRow + currentRow, col - normalisedCol + currentCol)
//              }
          }).iterator.map {
            case (k, vs) => (k, vs.flatMap(v => boards.map(v +_)))//.map((k, _))
          }

//          if (normalisedRow == currentRow && normalisedCol == currentCol)
//            memo.getOrElseUpdate((currentRow, currentCol, steps), breakdownMove())
//          else
//            moveUnbounded(Set((normalisedRow, normalisedCol)), steps).map {
//              case (row, col) => (row - normalisedRow + currentRow, col - normalisedCol + currentCol)
//            }
        }.groupMapReduce(_._1)(_._2)(_++_)
    }

    moveUnbounded(Map(start -> Set((0,0))), steps)
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

  private def sgn(a:Int,b:Int) = {
    if (a < b) -1
    else if (a == b) 0
    else 1
  }

  private implicit class DefinitelyPositiveMod(val self: Int) extends AnyVal {
    private[_21] def %%(other: Int) = (self % other + other) % other
  }

  private implicit class PointOp(val self: (Int,Int)) extends AnyVal {

    private[_21] def + (other: (Int,Int)) =
      (other._1 + self._1, other._2 + self._2)

//    private[_18] def * (other: Long) =
//      (self._1 * other, self._2 * other)
  }
}
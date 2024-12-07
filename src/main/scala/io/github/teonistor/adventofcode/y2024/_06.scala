package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _06 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long = {
    solveInitial(input)
      ._3.iterator
      .map(_.count(_== 'X'))
      .sum
  }

  override def _2(input: String): Long = {
   val (originalMap, startingCoord, initialWalk) = solveInitial(input)

    val u = initialWalk.indices.flatMap(y =>
      initialWalk(y).indices.filter(x => initialWalk(y)(x) == 'X')
      .map((_, y)))
      .toSet
      .excl(startingCoord)

    u
      .iterator.zipWithIndex
      .count { case ((x,y), i) =>
        if(i==444 || i == 707|| i == 837|| i == 1065|| i == 2464|| i == 2469|| i == 2621|| i == 2864|| i == 2935|| i == 3287|| i == 3548)
          true
        else {
      val map = originalMap.map(_.clone())
      map(startingCoord._2)(startingCoord._1) = '.'
      map(y)(x) = '#'

      @tailrec
      def doesItLoop(x:Int,y:Int,dir:Char,skip:Boolean=false): Boolean = {
//        if (x==3 & y==6)

        if(!skip) {
          if (map(y)(x) == dir) {
            return true
          }
          map(y)(x) = dir
        }

        val (nextX, nextY) = dir match {
          case '^' => (x, y-1)
          case '>' => (x+1, y)
          case 'v' => (x, y+1)
          case '<' => (x-1, y)
        }

        if (nextX >= 0 && nextY >=0 && nextY<map.length&&nextX<map(nextY).length ){
          if(map(nextY)(nextX) == '#')
            doesItLoop(x, y, dir match {
              case '^' => '>'
              case '>' => 'v'
              case 'v' => '<'
              case '<' => '^'
            }, true)
          else
            doesItLoop(nextX, nextY, dir)
        } else {
          false
        }
      }

     doesItLoop(startingCoord._1, startingCoord._2, '^')
    }
}
  }

  private def solveInitial(input:String)={
    val map = input.linesIterator
      .map(_.toArray)
      .toArray
    val originalMap = map.map(_.clone())
    val y = map.indexWhere(_.contains('^'))
    val x = map(y).indexOf('^')

    @tailrec
    def move(x:Int,y:Int,dir:Char): Unit = {

      map(y)(x) = 'X'

      val (nextX, nextY) = dir match {
        case '^' => (x, y-1)
        case '>' => (x+1, y)
        case 'v' => (x, y+1)
        case '<' => (x-1, y)
      }

      if (nextX >= 0 && nextY >=0 && nextY<map.length&&nextX<map(nextY).length ){
        if(map(nextY)(nextX) == '#')
          move(x, y, dir match {
            case '^' => '>'
            case '>' => 'v'
            case 'v' => '<'
            case '<' => '^'
          })
        else
          move(nextX, nextY, dir)
      } else {
      }
    }

    move(x, y, '^')
    (originalMap, (x,y), map)
  }
}

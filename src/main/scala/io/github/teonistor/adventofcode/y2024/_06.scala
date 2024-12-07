package io.github.teonistor.adventofcode.y2024

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _06 extends StandardAdventOfCodeSolution[Int] {

  private val rotateRight = Map(
    '^' -> '>',
    '>' -> 'v',
    'v' -> '<',
    '<' -> '^')

  override def _1(input: String): Int =
    (navigate _).tupled(
        readLabMap(input))
      ._1.iterator
      .map(_.count(rotateRight.keySet))
      .sum

  override def _2(input: String): Int = {
    val (lab, start) = readLabMap(input)
    val (initialWalk, _) = navigate(lab, start)

    initialWalk.indices.iterator
      .flatMap(i => initialWalk(i).indices
        .map((i, _)))
      .toSet
      .excl(start)
      .filter { case (i, j) => rotateRight.keySet(initialWalk(i)(j)) }
      .iterator
      .count { case (i, j) =>
        val modifiedLab = lab.map(_.clone())
        modifiedLab(i)(j) = '#'
        navigate(modifiedLab, start)._2
      }
  }

  /**
   * @return The lab map as a 2D char array, and a pair of (i,j) coordinates of the starting point. The starting point
   *         is replaced in the map with the empty space marker.
   */
  private def readLabMap(input: String) = {
    val lab = input.linesIterator
      .map(_.toArray)
      .toArray
    val i = lab.indexWhere(_.contains('^'))
    val j = lab(i).indexOf('^')
    assert(j >= 0)
    lab(i)(j) = '.'
    (lab, (i, j))
  }

  /**
   * @return The lab map with places visited by the guard marked, and a boolean indicating whether he ends up in a loop
   */
  private def navigate(lab: Array[Array[Char]], start: (Int, Int)) = {
    val result = lab.map(_.clone())

    @tailrec
    def navigate0(i: Int, j: Int, dir: Char, mark: Boolean = true): Boolean = {
      if (mark) {
        if (result(i)(j) == dir)
          return true
        result(i)(j) = dir
      }

      val (nextI, nextJ) = dir match {
        case '^' => (i - 1, j)
        case '>' => (i, j + 1)
        case 'v' => (i + 1, j)
        case '<' => (i, j - 1)
      }

      if (nextI < 0 || nextJ < 0 || nextI >= result.length || nextJ >= result(nextI).length)
        false
      else if (result(nextI)(nextJ) == '#')
        navigate0(i, j, rotateRight(dir), false)
      else
        navigate0(nextI, nextJ, dir)
    }

    (result, navigate0(start._1, start._2, '^'))
  }
}

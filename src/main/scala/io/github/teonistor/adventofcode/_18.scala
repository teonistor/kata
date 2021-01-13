package io.github.teonistor.adventofcode

import scala.util.matching.Regex.Match

object _18 {

  private val lex = "(\\d+)|([+*])|(\\()|(\\))".r

  private def explicitateMatch(next: Match) =
    (next.group(1), next.group(2), next.group(3), next.group(4)) match {
      case (v, null, null, null) => (1, v)
      case (null, v, null, null) => (2, v)
      case (null, null, v, null) => (3, v)
      case (null, null, null, v) => (4, v)
    }

  private def computeIf(lhs: Int, op: Char, rhs: Int) =
    op match {
      case '+' => lhs + rhs
      case '*' => lhs * rhs
      case _ => rhs
    }

  private def parseLineRec(number: Int, op: Char, remaining: Iterator[Match]): Int = {
    if (remaining.isEmpty) {
      number

    } else {
      val next = explicitateMatch(remaining.next())

      next match {
        case (1, newNumber) => parseLineRec(computeIf(number, op, newNumber.toInt), op, remaining)
        case (2, newOp) => parseLineRec(number, newOp(0), remaining)
        case (3, _) => parseLineRec(computeIf(number, op, parseLineRec(0, '.', remaining)), op, remaining)
        case (4, _) => number

      }
    }
  }

  // 1568258264 -> too low

  // 2046446784 -> 53586054336
  // 53107865816 -> too low (handcranked)


  // handcrank debug data
  // (4 + 6 * 8 + 6) + (4 * 6 + 9) * (5 + 9 * (9 + 7 + 2 + 7) + 6 * 4 * 6) * 6 * (2 * 8 * (2 + 6 * 9 + 5 + 9 + 5) + 8 * 6) + ((7 * 3 * 4 + 2 + 4) + (4 + 5 + 2 * 9 + 3)) = 2046446784 should be 53586054336

//  private def parseLineFragment(remaining: Iterator[Match]): Int = {
//    val str = remaining.next().group(1)
//    if (str != null)
//      parseLineRec(str.toInt, remaining)
//
//    // To remove leading open paren. Dirty, I know, because we technically therefore have unbalanced paren
//    else
//    parseLineFragment(remaining)
//  }

  private def parseLine(line: String) = {
    val dbg = parseLineRec(0, '.', lex.findAllMatchIn(line))
    println(s"$line = $dbg")
    dbg
  }

  def _1(input: String): Int = {
    input.split('\n').to(LazyList)
      .map(parseLine)
      .sum
  }

  def _2(input: String): Int = {
    0
  }
}





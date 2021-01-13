package io.github.teonistor.adventofcode

import scala.util.matching.Regex.Match

object _18 {

  private val lex = "(\\d+)|([+*])|(\\()|(\\))".r

  private def parseLineRec(current: Int, remaining: Iterator[Match]): Int = {
    if (remaining.isEmpty) {
      current
    } else {

      val signOrParen = remaining.next()
      if (signOrParen.group(4) != null) {
        current

      } else {
        val numberOrParen = remaining.next()
        val number =
          if (numberOrParen.group(1) != null)
            numberOrParen.group(1).toInt
          else
            parseLineFragment(remaining)

        parseLineRec(if (signOrParen.group(2).equals("+")) current + number else current * number, remaining)
      }
    }
  }

  private def parseLineFragment(remaining: Iterator[Match]): Int = {
    val str = remaining.next().group(1)
    if (str != null)
      parseLineRec(str.toInt, remaining)

    // To remove leading open paren. Dirty, I know, because we technically therefore have unbalanced paren
    else
      parseLineFragment(remaining)
  }

  private def parseLine(line: String) = {
    val dbg = parseLineFragment(lex.findAllMatchIn(line))
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





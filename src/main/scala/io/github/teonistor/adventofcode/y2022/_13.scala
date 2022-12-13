package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.AdventOfCodeSolution

import scala.util.matching.Regex.Match

object _13 extends AdventOfCodeSolution[Int] {

  private val language = """
      |(?<STARTLIST>\[)
      |(?<ENDLIST>])
      |(?<COMMA>,)
      |(?<NUMBER>\d+)
      |""".stripMargin.strip.replace('\n', '|').r

  def _1(input: String): Int = {
    input.split("\n\n").to(LazyList)
      .map(_.split("\n")
      .map(parse))
      .zipWithIndex
      .filter(pair => correctlyOrdered(pair._1(0), pair._1(1)) == 1)
      .map(_._2 + 1)
      .sum
  }

  override def _2(input: String): Int = ???

  private def parse(row: String) = {
    val iterator = language.findAllMatchIn(row)
    doParse(iterator)
  }

  private def doParse(iterator:Iterator[Match], list:List[Any] = List.empty): List[Any] = {
    if (!iterator.hasNext)
      return list.reverse

    val value = iterator.next()
    if (value.group("ENDLIST") != null)
      list.reverse
    else if (value.group("NUMBER") != null)
      doParse(iterator, list prepended value.group("NUMBER").toInt)
    else if (value.group("STARTLIST") != null)
      doParse(iterator, list prepended doParse(iterator))
    else
      doParse(iterator, list)
  }

  private def correctlyOrdered(left: List[Any], right: List[Any]): Int = {
//    println(left)
//    println(right)
//    println("---------------------")

    (left, right) match {
      case (Nil, _::_) => myDebug(1, "the first list is empty and the second one isn't")
      case (Nil, Nil) => 0
      case (_::_, Nil) => myDebug(-1, "the first list is not empty and the second one is")
      case (leftHead::leftTail, rightHead::rightTail) =>
        val innerComp = leftHead match {
          case a:Int => rightHead match {
            case b: Int => myDebug(Integer.compare(b, a), "we compared integers")
            case b: List[Any] => correctlyOrdered(List(a), b)
          }
          case a:List[Any] => rightHead match {
            case b: Int => correctlyOrdered(a, List(b))
            case b: List[Any] => correctlyOrdered(a, b)
          }
        }
        if (innerComp == 0)
          correctlyOrdered(leftTail, rightTail)
        else
          innerComp
    }
  }

  def myDebug(v:Int, because:String)= {
//    println(s"$v because $because")
    v
  }
}

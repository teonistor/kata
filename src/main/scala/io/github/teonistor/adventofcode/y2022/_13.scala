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

  def _1(input: String): Int =
    input.split("\n\n").to(LazyList)
      .map(_.split("\n")
      .map(parse))
      .zipWithIndex
      .filter(pair => compare(pair._1(0), pair._1(1)) == -1)
      // This puzzle is 1-indexed
      .map(_._2 + 1)
      .sum

  override def _2(input: String): Int = {
    val dividers = List(
      List(List(2)),
      List(List(6)))
    val packets = input.split("\n+").toList
      .map(parse)
      .prependedAll(dividers)
      .sortWith(compare(_, _) == -1)

    dividers.map(packets.indexOf)
      // This puzzle is 1-indexed
      .map(_+1)
      .product
  }

  private def parse(row: String) =
    doParse(language.findAllMatchIn(row))
      // If you look closely, our code encloses the result in one extra list
      .head.asInstanceOf[List[Any]]

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

  private def compare(left: List[Any], right: List[Any]): Int =
    (left, right) match {
      case (Nil, _::_) => -1
      case (Nil, Nil) => 0
      case (_::_, Nil) => 1
      case (leftHead::leftTail, rightHead::rightTail) =>
        val innerComp = leftHead match {
          case left: Int => rightHead match {
            case right: Int => Integer.compare(left, right)
            case right: List[Any] => compare(List(left), right)
          }
          case left: List[Any] => rightHead match {
            case right: Int => compare(left, List(right))
            case right: List[Any] => compare(left, right)
          }
        }
        if (innerComp == 0)
          compare(leftTail, rightTail)
        else
          innerComp
    }
}

package io.github.teonistor.adventofcode

import scala.collection.immutable.Queue

object _07 {

  private type Bag = (String, List[(Int, String)])
  private val innerBags = "(\\d+) (.+?) bags?"
  private val zeroBags = "(.+) bags contain no other bags\\.".r
  private val someBags = s"(.+) bags contain (($innerBags, )*$innerBags)\\.".r

  private def parseSomeBags(bags: String): List[(Int, String)] =
    innerBags.r.findAllMatchIn(bags)
      .map(mtch => (mtch.group(1).toInt, mtch.group(2)))
      .toList

  private def parseBag(bag: String): Bag = bag match {
    case zeroBags(color) => new Bag(color, List.empty)
    case someBags(color, bags, _*) => new Bag(color, parseSomeBags(bags))
  }

  private def lookFor(color: String, bags: Map[String, Set[String]]): Set[String] =
    if (bags contains color)
      bags(color) ++ bags(color).flatMap(outerColor => lookFor(outerColor, bags))
    else
      Set.empty

  private def count(color: String, bags: Map[String, List[(Int, String)]]): Int =
    1 + bags(color).to(LazyList).map(countAndColor => countAndColor._1 * count(countAndColor._2, bags)).sum

  // This is the tail-recursive version of the above one-liner. Can we write a program-writing program to make the conversion for us?
  private def tailRecCount(bags: Map[String, List[(Int, String)]], colors: Queue[(Int, String)], accumulator: Int): Int =
    if (colors.isEmpty)
      accumulator
    else {
      val (count, color) = colors.head
      tailRecCount(bags,
        colors.tail.appendedAll(bags(color).to(LazyList).map(countAndColor => (countAndColor._1 * count, countAndColor._2))),
        accumulator + count)
    }

  def _1(input: String): Int = {
    val bags = input.split('\n').map(parseBag).to(List)

    val outerByInner = bags.to(Set).flatMap(bag => bag._2.map(subSpec => (subSpec._2, bag._1))).groupMap(_._1)(_._2)
    println(outerByInner.keySet)
    println(outerByInner)

    lookFor("shiny gold", outerByInner).size


  }

  def _2(input: String): Int = {
    val bags = input.split('\n').map(parseBag).to(List)
    tailRecCount(bags.toMap, Queue((1, "shiny gold")), 0) - 1
  }
}

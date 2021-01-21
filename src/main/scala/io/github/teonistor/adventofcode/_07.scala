package io.github.teonistor.adventofcode

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

  private def count(color: String, bags: Map[String, List[(Int, String)]]):Int = {
    1 + bags(color).to(LazyList).map(countAndColor => countAndColor._1 * count(countAndColor._2, bags)).sum
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
    count("shiny gold", bags.toMap) - 1
  }
}

package io.github.teonistor.adventofcode

object _07 {

  private type BagSpec = (String, List[(Int, String)])
  private val innerBags = "(\\d+) (.+?) bags?"
  private val zeroBags = "(.+) bags contain no other bags\\.".r
  private val someBags = s"(.+) bags contain (($innerBags, )*$innerBags)\\.".r

  private def digList(bags: String): List[(Int, String)] =
    innerBags.r.findAllMatchIn(bags)
      .map(mtch => (mtch.group(1).toInt, mtch.group(2)))
      .toList

  private def digBags(rule: String): BagSpec = rule match {
    case zeroBags(color) => new BagSpec(color, List.empty)
    case someBags(color, bags, _*) => new BagSpec(color, digList(bags))
  }

  private def addIndirect(current: Map[String, Set[String]], prev: Map[String, Set[String]]): Map[String, Set[String]] = {
    if (current.equals(prev)) {
      return current
    }

    val next = current.view.map(kv => (kv._1, kv._2 ++ kv._2.flatMap(current.getOrElse(_, Set.empty)))).toMap
    addIndirect(next, current)
  }

  def _1(input: String): Int = {
    val bagSpecs = input.split('\n').map(digBags).to(List)

    val outerByInner = bagSpecs.to(Set).flatMap(spec => spec._2.map(subSpec => (subSpec._2, spec._1))).groupMap(_._1)(_._2)
    val allOuterByInner = addIndirect(outerByInner, null)

    // TODO Broken - passes example but the problem answer is wrong
    println(allOuterByInner.keySet)
    allOuterByInner.count(_._2.contains("shiny gold"))
  }

  def _2(input: String): Int = {
    0
  }
}

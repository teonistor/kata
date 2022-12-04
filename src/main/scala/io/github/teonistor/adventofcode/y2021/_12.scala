package io.github.teonistor.adventofcode.y2021

import org.apache.commons.lang3.StringUtils.isAllUpperCase

object _12 {

  def _1(input: String): Int =
    solve(input, search1(_))

  def _2(input: String): Int =
    solve(input, search2(_))

  private def solve(input: String, search: Map[String, Set[String]] => Int) =
    Some(input.split("\n").to(LazyList)
      .map(_.split("-"))
      .flatMap(connection => List(
        connection(0) -> Set(connection(1)),
        connection(1) -> Set(connection(0))))
      .groupMapReduce(_._1)(_._2)(_|_))
      .filter(sanityCheck)
      .map(search)
      .getOrElse(-14)

  private def sanityCheck(connections: Map[String,Set[String]]) = {
    val startMissing = Some("No start cave").filter(_ => !(connections contains "start"))
    val endMissing = Some("No end cave").filter(_ => !(connections contains "end"))
    val infiniteLoop = Some("Infinite loop").filter(_ => connections
      .filter(kv => isAllUpperCase(kv._1)).exists(_._2.exists(isAllUpperCase(_))))

    List(startMissing, endMissing, infiniteLoop).flatten
      .tapEach(println)
      .isEmpty
  }

  private def search1(connections: Map[String, Set[String]],
                      current: String      = "start",
                      visited: Set[String] = Set.empty): Int =
    if (current == "end")
      1
    else if (visited contains current)
      0
    else
      connections(current).to(LazyList)
        .map(search1(connections, _, markCurrentAsVisitedIfNeeded(current, visited)))
        .sum


  private def search2(connections: Map[String, Set[String]],
                      current: String      = "start",
                      extraVisit: Boolean  = true,
                      visited: Set[String] = Set.empty): Int =
    if (current == "end")
      1
    else if (!(visited contains current))
      connections(current).to(LazyList)
        .map(search2(connections, _, extraVisit, markCurrentAsVisitedIfNeeded(current, visited)))
        .sum
    else if (isSmallCave(current) && extraVisit)
      search2(connections, current, false, visited - current)
    else
      0

  private def markCurrentAsVisitedIfNeeded(current: String, visited: Set[String]) =
    if (isAllUpperCase(current)) visited
    else visited + current

  private def isSmallCave(cave: String) =
    !(isAllUpperCase(cave) || cave == "start" || cave == "end")
}

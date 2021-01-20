package io.github.teonistor.adventofcode

object _19 {

  private val node = "(\\d+): ([0-9 ]+)( \\| ([0-9 ]+))?".r
  private val leaf = "(\\d+): \"([a-z]+)\"".r
  private val inner = "<(\\d+)>".r

  private type Node = (Int, Seq[Seq[Int]])
  private type Leaf = (Int, String)

  private def parseNode(num: String, alt1: String, alt2: String) = (
    num.toInt,
    if (alt2 == null) LazyList(alt1.split(' ').to(List).map(_.toInt))
    else LazyList(alt1.split(' ').to(List).map(_.toInt), alt2.split(' ').to(List).map(_.toInt)))

  private def parseRules(nodes: Seq[Node], leaves: Seq[Leaf], remaining: Seq[String]): (Seq[Node], Seq[Leaf]) =
    if (remaining.isEmpty)
      (nodes, leaves)
    else
      remaining.head match {
        case node(num, alt1, _, alt2) => parseRules(nodes.prepended(parseNode(num, alt1, alt2)), leaves, remaining.tail)
        case leaf(num, value) => parseRules(nodes, leaves.prepended((num.toInt, value)), remaining.tail)
      }

  private def makeRegex(index: Int, nodes: Map[Int, Seq[Seq[Int]]], leaves: Map[Int, String], overrides: Map[Int, String]) =
    if (overrides contains index)
      overrides(index)
    else if (nodes contains index)
      "(" +
        nodes(index).map(_.map(n => s"<$n>").mkString).to(LazyList).mkString("|") +
      ")"
    else
      leaves(index)

  private def makeRegexRec(str: String, nodes: Map[Int, Seq[Seq[Int]]], leaves: Map[Int, String], overrides: Map[Int, String]): String =
    if (inner.findAllMatchIn(str).isEmpty)
      str
    else
      makeRegexRec(inner.replaceAllIn(str, mtch => makeRegex(mtch.group(1).toInt, nodes, leaves, overrides)), nodes, leaves, overrides)

  private def solve(input: String, overrides: Map[Int, String]) = {
    val strings = input.split("\n\n")
    val (nodes, leaves) = parseRules(List.empty, List.empty, strings(0).split('\n'))

    val rx = makeRegexRec("<0>", nodes.toMap, leaves.toMap, overrides).r
    strings(1).split('\n').count(rx.matches)
  }

  def _1(input: String): Int = {
    solve(input, Map.empty)
  }

  def _2(input: String): Int = {
    // We create overrides for rules 8 and 11 (now recursive):
    //   8: 42 | 42 8
    //  11: 42 31 | 42 11 31

    val overrides = Map((8, "(<42>+)"), (11,
      // Brute-force a regex that means <42> at least once then <31> the same number of times
      (2 to 10).map(i => s"<42>{$i}<31>{$i}").mkString("(<42><31>|", "|", ")")))

    solve(input, overrides)
  }
}

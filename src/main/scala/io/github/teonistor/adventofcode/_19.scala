package io.github.teonistor.adventofcode

object _19 {

  private val node = "(\\d+): ([0-9 ]+)( \\| ([0-9 ]+))?".r
  private val leaf = "(\\d+): \"([a-z]+)\"".r
  private val inner = "<(\\d+)>".r

  private type Node = (Int, Seq[Seq[Int]])
  private type Leaf = (Int, String)

  private def parseNode(num: String, alt1: String, alt2: String) = {
    (num.toInt, LazyList(
      alt1.split(' ').to(List).map(_.toInt),
      if (alt2 == null) Seq.empty else alt2.split(' ').to(List).map(_.toInt)))
  }

  private def parseRules(nodes: Seq[Node], leaves: Seq[Leaf], remaining: Seq[String]): (Seq[Node], Seq[Leaf]) = {
    if (remaining.isEmpty)
      (nodes, leaves)
    else
      remaining.head match {
        case node(num, alt1, _, alt2) => parseRules(nodes.prepended(parseNode(num, alt1, alt2)), leaves, remaining.tail)
        case leaf(num, value) => parseRules(nodes, leaves.prepended((num.toInt, value)), remaining.tail)
      }
  }

  private def makeRegex(index: Int, nodes: Map[Int, Seq[Seq[Int]]], leaves: Map[Int, String]) = {
    if (nodes contains index)
      nodes(index).map(_.map(n => s"<$n>").prepended("").reduce(_ + _))
        .to(LazyList).reduce(_ + "|" + _)
    else
      leaves(index)
  }

  private def makeRegexRec(str: String, nodes: Map[Int, Seq[Seq[Int]]], leaves: Map[Int, String]):String = {
    if (inner.findAllMatchIn(str).isEmpty)
      str
    else
      makeRegexRec(inner.replaceAllIn(str, mtch => "(" + makeRegex(mtch.group(1).toInt, nodes, leaves) + ")"), nodes, leaves)
  }

  def _1(input: String): Int = {
    val strings = input.split("\n\n")
    val (nodes, leaves) = parseRules(List.empty, List.empty, strings(0).split('\n'))

    val rx = makeRegexRec("<0>", nodes.toMap, leaves.toMap).r

    strings(1).split('\n').count(rx.matches)
  }

  def _2(input: String): Int = {
    0
  }
}

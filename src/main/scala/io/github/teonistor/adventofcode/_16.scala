package io.github.teonistor.adventofcode

object _16 {
  private val broadRegex = "((?:.+: \\d+-\\d+ or \\d+-\\d+\n)+)\nyour ticket:\n([0-9,]+)\n\nnearby tickets:\n([0-9\\n,]+)".r
  private val ruleRegex = "(.+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".r

  private def parseInput(input: String) = input match {
    case broadRegex(rules, myTicket, nearby) => (
      ruleRegex.findAllMatchIn(rules).map(mtch => (mtch.group(1), makeFilter((2 to 5).map(mtch.group).map(_.toInt)))).toArray,
      myTicket.split(',').map(_.toInt),
      nearby.split('\n').map(_.split(',').map(_.toInt)))
  }

  private def makeFilter(limits: IndexedSeq[Int]): Int => Boolean =
    x => limits(0) <= x && x <= limits(1) || limits(2) <= x && x <= limits(3)

  def _1(input: String): Int = {
    val (rules, _, nearby) = parseInput(input)
    nearby.to(LazyList).flatten
      .filter(x => rules.count(_._2(x)) == 0).sum
  }

  def _2(input: String): Long = {
    val (rules, myTicket, nearby) = parseInput(input)
    val validTix = nearby.to(List).filter(_.forall(value => rules.count(_._2(value)) > 0)).prepended(myTicket)

    // For the first time in this challenge series the example and problem requirements aren't strictly the same, so we distinguish based on input size
    val wordToLookFor = if (nearby.length < 10) "seat" else "departure"

    // English: The plausible indexes for each rule are those where the rule evaluates true on all tickets at that index
    val plausibleFields = rules.toMap.view.mapValues(rule => validTix.head.indices.filter(i => validTix.forall(ticket => rule(ticket(i))))).toMap

    // We will be done when all the fields we're interested in are left with just one plausible index
    def done(fields: Map[String, IndexedSeq[Int]]) =
      fields.iterator.filter(_._1.startsWith(wordToLookFor)).forall(_._2.size == 1)

    def removeSingleValues(fields: Map[String, IndexedSeq[Int]]): Map[String, IndexedSeq[Int]] =
      if (done(fields))
        fields
      else {
        val unique = fields.values.toSet.filter(_.size == 1).flatten
        val newFields = fields.view.mapValues(seq => if (seq.length == 1) seq else seq.filterNot(unique.contains)).toMap
        removeSingleValues(newFields)
      }

    // .toLong just at the end because the product is too big for int
    removeSingleValues(plausibleFields).filter(_._1.startsWith(wordToLookFor)).values.flatten.map(myTicket(_).toLong).product
  }
}

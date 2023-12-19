package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _19 extends StandardAdventOfCodeSolution[Long] {

  private val parseWorkflow = "([^{]+)\\{([^}]+)}".r
  private val parseWorkflowInner = "([a-zA-Z]+)([<>])(\\d+):([a-zA-Z]+)".r
  private val parsePartInner = "([a-zA-Z]+)=(\\d+)".r

  override def _1(input: String): Long =
    solve(input, (intervals,partsStr) =>
      partsStr.replaceAll("[{}]", "")
        .split('\n').iterator
        .map(_.split(',').iterator
          .map {
            case parsePartInner(k, v) => (k, v.toLong)
          }.toMap)
        .filter(part => intervals
          .exists(_.forall {
            case (property, (start, end)) => part(property) >= start && part(property) <= end
          }))
        .map(_.values.sum)
        .sum)

  override def _2(input: String): Long =
    solve(input, (intervals,_) => intervals.map(_.valuesIterator
        .map { case (start, end) => end - start + 1 }
        .product)
      .sum)

  private def solve(input: String, continuation: (List[Intervalage],String)=>Long): Long = {
    val workflowsStr :: partsStr :: Nil = input.strip()
      .split("\\n{2,}").toList

    val workflows = workflowsStr.split('\n').iterator
      .map {
        case parseWorkflow(name, rulesStr) =>
          val rules = rulesStr.split(',').iterator.map {
            case parseWorkflowInner(property, sign, number, next) => Right(Rule(property, sign.charAt(0), number.toLong, next))
            case fallback => Left(fallback)
          }.toList
          (name, Workflow(name, rules.flatMap(_.toOption), rules.find(_.isLeft).get.swap.toOption.get))
      }.toMap

    def grindWorkflows(workflowKey: String, intervals: Intervalage) =
      if (workflowKey == "R")
        List.empty
      else if (workflowKey == "A")
        List(intervals)
      else {
        val selected = workflows(workflowKey)
        grindRules(selected.rules, selected.fallback, intervals)
      }

    def grindRules(rules: List[Rule], fallback: String, intervals: Intervalage): List[Intervalage] =
      if (rules.isEmpty)
        grindWorkflows(fallback, intervals)

      else {
        val Rule(ruleProp, sign, bound, next) :: tail = rules
        val (start, end) = intervals(ruleProp)

        if (sign == '<' && end < bound || sign == '>' && start > bound)
          // all in
          grindWorkflows(next, intervals)
        else if (sign == '<' && start > bound || sign == '>' && end < bound)
          // all out
          grindRules(tail, fallback, intervals)
        else if (sign == '<')
          // start to bound-1 in, bound to end out
          grindWorkflows(next, intervals.updated(ruleProp, (start, bound - 1))) ++ grindRules(tail, fallback, intervals.updated(ruleProp, (bound, end)))
        else
          // start to bound out, bound+1 to end in
          grindRules(tail, fallback, intervals.updated(ruleProp, (start, bound))) ++ grindWorkflows(next, intervals.updated(ruleProp, (bound + 1, end)))
      }

    continuation(grindWorkflows("in", Iterator("x", "m", "a", "s")
      .map((_, (1L, 4000L)))
      .toMap), partsStr)
  }

  private case class Workflow(name:String, rules:List[Rule], fallback:String)
  private case class Rule(property:String, sign:Char, bound:Long, next:String)
  private type Intervalage = Map[String, (Long,Long)]
}

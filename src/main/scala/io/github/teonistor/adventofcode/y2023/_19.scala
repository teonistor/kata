package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _19 extends StandardAdventOfCodeSolution[Long] {

  private val parseWorkflow = "([^{]+)\\{([^}]+)}".r
  private val parseWorkflowInner = "([a-zA-Z]+)([<>])(\\d+):([a-zA-Z]+)".r
  private val parsePartInner = "([a-zA-Z]+)=(\\d+)".r

  override def _1(input: String): Long = {
//    val workflows :: parts :: Nil = input.strip()
//      .split("\\n{2,}").toList
//
//val wf=    workflows.split('\n').iterator
//      .map {
//        case parseWorkflow(name, rulesStr)=>
//          val rules = rulesStr.split(',').iterator.map {
//            case parseWorkflowInner(property, "<", number, next) => Right((property, (_:Long) < number.toLong, next))
//            case parseWorkflowInner(property, ">", number, next) => Right((property, (_:Long) > number.toLong, next))
//            case fallback => Left(fallback)
//          }.toList
//          (name, Workflow(name, rules.flatMap(_.toOption), rules.find(_.isLeft).get.swap.toOption.get))
//      }.toMap
//
//    val ps = parts.replaceAll("[{}]", "")
//      .split('\n').iterator
//      .map(_.split(',').iterator.map {
//        case parsePartInner(k, v) => (k, v.toLong)
//      }.toMap)
//      .toList
//
//    def putPartThroughWorkflow(part:Map[String,Long], workflowKey:String): String = {
//      val sel = wf(workflowKey)
//      val next = sel.rules
//        .find(rule => rule._2(part(rule._1)))
//        .map(_._3)
//        .getOrElse(sel.fallback)
//
//      if (next == "A" || next == "R")
//        next
//      else
//        putPartThroughWorkflow(part, next)
//    }
//
//    ps.filter(putPartThroughWorkflow(_, "in") == "A")
//      .map(_.values.sum)
//      .sum
    ???
  }

  override def _2(input: String): Long = {
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


    def grind(intervals:Map[String, (Long,Long)], workflowKey:String): Long =
      if (workflowKey == "R")
        0L
      else if (workflowKey == "A")
        intervals.valuesIterator
          .map { case (start, end) => end - start + 1 }
          .product
      else {
        val sel = workflows(workflowKey)


        def fff(rules: List[Rule], intervals:Map[String, (Long,Long)]):Long =
          if (rules.isEmpty)
            grind(intervals, sel.fallback)

          else {
            val Rule(ruleProp, sign, bound, next) :: tail = rules
            val (start, end) = intervals(ruleProp)

             if (sign == '<' && end < bound || sign == '>' && start > bound)
              // all in
              grind(intervals, next)
            else if (sign == '<' && start > bound || sign == '>' && end < bound)
              // all out
              fff(tail, intervals)
            else if (sign == '<')
              // start to bound-1 in, bound to end out
              grind(intervals.updated(ruleProp, (start, bound - 1)), next) + fff(tail, intervals.updated(ruleProp, (bound, end)))
            else
              // start to bound out, bound+1 to end in
              fff(tail, intervals.updated(ruleProp, (start, bound))) + grind(intervals.updated(ruleProp, (bound+1, end)), next)
          }

        fff(sel.rules, intervals)
      }

    grind(Iterator("x", "m", "a", "s")
      .map((_, (1L, 4000L)))
      .toMap      , "in")
//    Iterator("x", "m", "a", "s")
//        .map(str => {
//          val value = grind(str, 1, 4000, "in")
//          value.iterator
//            .map { case (start, end) => end - start + 1 }
//            .sum
//        })
//      .map { v=> println(v); v }
//      .product

  }

  case class Workflow(name:String, rules:List[Rule], fallback: String)
  case class Rule(property:String, sign:Char, bound:Long, next:String)
}

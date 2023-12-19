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
    val workflows :: parts :: Nil = input.strip()
      .split("\\n{2,}").toList

    val wf = workflows.split('\n').iterator
      .map {
        case parseWorkflow(name, rulesStr) =>
          val rules = rulesStr.split(',').iterator.map {
            case parseWorkflowInner(property, sign, number, next) => Right(Rule(property, sign.charAt(0), number.toLong, next))
            case fallback => Left(fallback)
          }.toList
          (name, Workflow(name, rules.flatMap(_.toOption), rules.find(_.isLeft).get.swap.toOption.get))
      }.toMap


    def putIntervalThroughWorkflow(property:String, start:Long, end:Long, workflowKey:String): Set[(Long,Long)] =
      if (workflowKey == "R")
        Set.empty
      else if (workflowKey == "A")
        Set((start, end))
      else {
        val sel = wf(workflowKey)

        val activeRules = sel.rules
          .filter(_.property == property)

        def fff(rules: List[Rule], start: Long, end: Long /*, acc: Set[(Long, Long)]*/): Set[(Long, Long)] =
          if (rules.isEmpty)
            putIntervalThroughWorkflow(property, start, end, sel.fallback)

          else {
            val Rule(ruleProp, sign, bound, next) :: tail = rules
            if (property != ruleProp) {
              // not for us, could be either
              val possiblyOverlapping = fff(tail, start, end) ++ putIntervalThroughWorkflow(property, start, end, next)
              val (acc, overhang) = possiblyOverlapping.to(LazyList)
                .sortBy(_._1)
                .foldLeft((Set.empty[(Long,Long)], (-1L, -1L))) {
                  case ((acc, (-1L, -1L)), (currStart, currEnd)) => (acc, (currStart, currEnd))
                  case ((acc, (lastStart, lastEnd)), (currStart, currEnd)) =>
                    if (currStart - lastEnd > 1)
                      (acc.incl((lastStart, lastEnd)), (currStart, currEnd))
                    else
                      (acc, (lastStart min currStart, lastEnd max currEnd))
                }
              acc incl overhang

            } else if (sign == '<' && end < bound || sign == '>' && start > bound)
              // all in
              putIntervalThroughWorkflow(property, start, end, next)
            else if (sign == '<' && start > bound || sign == '>' && end < bound)
              // all out
              fff(tail, start, end)
            else if (sign == '<')
              // start to bound-1 in, bound to end out
              putIntervalThroughWorkflow(property, start, bound - 1, next) ++ fff(tail, bound, end)
            else
              // start to bound out, bound+1 to end in
              fff(tail, start, bound) ++ putIntervalThroughWorkflow(property, bound + 1, end, next)
          }

        fff(sel.rules, start, end)
      }

    Iterator("x", "m", "a", "s")
        .map(str => {
          val value = putIntervalThroughWorkflow(str, 1, 4000, "in")
          value.iterator
            .map { case (start, end) => end - start + 1 }
            .sum
        })
      .map { v=> println(v); v }
      .product

  }

  case class Workflow(name:String, rules:List[Rule], fallback: String)
  case class Rule(property:String, sign:Char, bound:Long, next:String)
}

// 319295
//
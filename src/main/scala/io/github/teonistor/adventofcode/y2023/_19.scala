package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _19 extends StandardAdventOfCodeSolution[Long] {

  private val parseWorkflow = "([^{]+)\\{([^}]+)}".r
  private val parseWorkflowInner = "([a-zA-Z]+)([<>])(\\d+):([a-zA-Z]+)".r
  private val parsePartInner = "([a-zA-Z]+)=(\\d+)".r

  override def _1(input: String): Long = {
    val workflows :: parts :: Nil = input.strip()
      .split("\\n{2,}").toList

val wf=    workflows.split('\n').iterator
      .map {
        case parseWorkflow(name, rulesStr)=>
          val rules = rulesStr.split(',').iterator.map {
            case parseWorkflowInner(property, "<", number, next) => Right((property, (_:Long) < number.toLong, next))
            case parseWorkflowInner(property, ">", number, next) => Right((property, (_:Long) > number.toLong, next))
            case fallback => Left(fallback)
          }.toList
          (name, Workflow(name, rules.flatMap(_.toOption), rules.find(_.isLeft).get.swap.toOption.get))
      }.toMap

    val ps = parts.replaceAll("[{}]", "")
      .split('\n').iterator
      .map(_.split(',').iterator.map {
        case parsePartInner(k, v) => (k, v.toLong)
      }.toMap)
      .toList

    def putPartThroughWorkflow(part:Map[String,Long], workflowKey:String): String = {
      val sel = wf(workflowKey)
      val next = sel.rules
        .find(rule => rule._2(part(rule._1)))
        .map(_._3)
        .getOrElse(sel.fallback)

      if (next == "A" || next == "R")
        next
      else
        putPartThroughWorkflow(part, next)
    }

    ps.filter(putPartThroughWorkflow(_, "in") == "A")
      .map(_.values.sum)
      .sum
  }

  override def _2(input: String): Long = ???

  case class Workflow(name:String, rules:List[(String, Long=>Boolean, String)], fallback: String)
}

package io.github.teonistor.adventofcode

import java.util.regex.Pattern

import scala.collection.immutable.HashMap
import scala.util.Try

object _04 {

  def _1(input: String): Int = {
    val req = "byr\niyr\neyr\nhgt\nhcl\necl\npid"
      .split('\n')
      .map(_ + ":")
    val splitInput = input.split("\n\n")

    splitInput.count(p => req.count(p.contains) == req.length)
  }

  def _2(input: String): Int = {
    val splitInput = input.split("\n\n")

    def forNum(min: Int, max: Int): String => Boolean =
      s => Try(s.toInt).map(i => min <= i && i <= max).getOrElse(false)

    val hgtCm = "(\\d{3})cm".r
    val hgtIn = "(\\d{2})in".r

    def hgt(s: String): Boolean = s match {
      case hgtCm(h) => 150 <= h.toInt && h.toInt <= 193
      case hgtIn(h) => 59 <= h.toInt && h.toInt <= 76
      case _ => false
    }

    val validations = HashMap(("byr", forNum(1920, 2002)),
      ("iyr", forNum(2010, 2020)),
      ("eyr", forNum(2020, 2030)),
      ("hgt", hgt(_)),
      ("hcl", (s: String) => s.matches("#[0-9a-f]{6}")),
      ("ecl", (s: String) => s.matches("amb|blu|brn|gry|grn|hzl|oth")),
      ("pid", (s: String) => s.matches("[0-9]{9}")))

    val reqs = "byr\niyr\neyr\nhgt\nhcl\necl\npid"
      .split('\n')
      .map(f => Pattern.compile(s"($f):([\\S]+)"))

    def valid(passport: String): Boolean =
      reqs.map(req => {
        val mtch = req.matcher(passport)
        mtch.find() && validations.get(mtch.group(1)).exists(_(mtch.group(2)))
      })
        .reduce(_ && _)

    splitInput.count(valid)
  }
}

package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.min
import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _05 extends StandardAdventOfCodeSolution[Long]{

  private val extractor = "seeds:(.+)\\n+seed-to-soil map:([\\s\\S]+)soil-to-fertilizer map:([\\s\\S]+)fertilizer-to-water map:([\\s\\S]+)water-to-light map:([\\s\\S]+)light-to-temperature map:([\\s\\S]+)temperature-to-humidity map:([\\s\\S]+)humidity-to-location map:([\\s\\S]+)".r

  override def _1(input: String): Long =
    solve(input, _.map(v => (v, v+1)))

  override def _2(input: String): Long =
    solve(input, _.grouped(2)
      .map(_.toList)
      .map { case start :: len :: Nil => (start, start + len) })

  private def solve(input: String, seedsToIntervals:Iterator[Long]=>Iterator[(Long,Long)]): Long = {
    val extractor(
      seedsStr,
      seedToSoilStr,
      soilToFertilizerStr,
      fertilizerToWaterStr,
      waterToLightStr,
      lightToTemperatureStr,
      temperatureToHumidityStr,
      humidityToLocationStr) = input

    seedsToIntervals(seedsStr
      .strip().split(' ')
      .iterator
      .map(_.toLong))
      .flatMap(parseMap(seedToSoilStr))
      .flatMap(parseMap(soilToFertilizerStr))
      .flatMap(parseMap(fertilizerToWaterStr))
      .flatMap(parseMap(waterToLightStr))
      .flatMap(parseMap(lightToTemperatureStr))
      .flatMap(parseMap(temperatureToHumidityStr))
      .flatMap(parseMap(humidityToLocationStr))
      .map(_._1)
      .min
  }

  private def parseMap(mapStr: String): ((Long, Long)) => Queue[(Long, Long)] = {
    val instructions = mapStr.strip()
      .split('\n')
      .map(_.split(' ').toList)
      .map { case dest :: src :: len :: Nil => (dest.toLong, src.toLong, src.toLong + len.toLong) }
      .sortBy(_._2)

    { case (inputStart, inputEnd) => chopRecu(inputStart, inputEnd, instructions.iterator
        .dropWhile(_._3 <= inputStart)
        .takeWhile(_._2 < inputEnd)
        .toList)
    }
  }

  @tailrec
  private def chopRecu(inputStart: Long, inputEnd: Long, instructionsRemaining: List[(Long,Long,Long)], result: Queue[(Long,Long)] = Queue.empty): Queue[(Long,Long)] =
    if (inputStart >= inputEnd)
      result
    else if (instructionsRemaining.isEmpty)
      result :+ (inputStart, inputEnd)
    else {
      val (instructionDest, instructionStart, instructionEnd) :: tail = instructionsRemaining

      if (inputStart < instructionStart)
        chopRecu(instructionStart, inputEnd, instructionsRemaining, result :+ (inputStart, min(inputEnd, instructionStart)))
      else
        chopRecu(instructionEnd, inputEnd, tail, result :+ (inputStart - instructionStart + instructionDest, min(inputEnd, instructionEnd) - instructionStart + instructionDest))
    }
}

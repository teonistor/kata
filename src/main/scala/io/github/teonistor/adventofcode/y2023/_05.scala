package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import java.lang.Math.min
import scala.annotation.tailrec
import scala.collection.immutable.Queue

object _05 extends StandardAdventOfCodeSolution[Long]{

  private val extractor = "seeds:(.+)\\n+seed-to-soil map:([\\s\\S]+)soil-to-fertilizer map:([\\s\\S]+)fertilizer-to-water map:([\\s\\S]+)water-to-light map:([\\s\\S]+)light-to-temperature map:([\\s\\S]+)temperature-to-humidity map:([\\s\\S]+)humidity-to-location map:([\\s\\S]+)".r

  override def _1(input: String): Long = {
    val extractor(seedsStr,
      seedToSoilStr,
      soilToFertilizerStr,
      fertilizerToWaterStr,
      waterToLightStr,
      lightToTemperatureStr,
      temperatureToHumidityStr,
      humidityToLocationStr) = input

    seedsStr.strip().split(' ')
      .iterator
      .map(_.toLong)
      .map(readMap(seedToSoilStr))
      .map(readMap(soilToFertilizerStr))
      .map(readMap(fertilizerToWaterStr))
      .map(readMap(waterToLightStr))
      .map(readMap(lightToTemperatureStr))
      .map(readMap(temperatureToHumidityStr))
      .map(readMap(humidityToLocationStr))
      .min

  }

  private def readMap(mapStr: String)={
    val instructions = mapStr.strip()
      .split('\n')
      .map(_.split(' ').toList)
      .map { case dest :: src :: len :: Nil => (dest.toLong, src.toLong, src.toLong + len.toLong)}

    (input: Long) => instructions
      .find(instruction => instruction._2 <= input && input < instruction._3)
      .map(instruction => input - instruction._2 + instruction._1)
      .getOrElse(input)
  }

  override def _2(input: String): Long = {
    val extractor(seedsStr,
    seedToSoilStr,
    soilToFertilizerStr,
    fertilizerToWaterStr,
    waterToLightStr,
    lightToTemperatureStr,
    temperatureToHumidityStr,
    humidityToLocationStr) = input

    val v1 = seedsStr.strip().split(' ')
      .iterator
      .grouped(2)
      .map(_.toList)
      .map { case start :: len :: Nil => (start.toLong, start.toLong + len.toLong) }
      .toList
    val v2 = v1
      .flatMap(mapToIntervaleriser(seedToSoilStr))
    val v3 = v2
      .flatMap(mapToIntervaleriser(soilToFertilizerStr))
    v3
      .flatMap(mapToIntervaleriser(fertilizerToWaterStr))
      .flatMap(mapToIntervaleriser(waterToLightStr))
      .flatMap(mapToIntervaleriser(lightToTemperatureStr))
      .flatMap(mapToIntervaleriser(temperatureToHumidityStr))
      .flatMap(mapToIntervaleriser(humidityToLocationStr))
      .map { v => println(v); v}
      .map(_._1)
      .min

  }

  private def mapToIntervaleriser(mapStr: String)= {
    val instructions = mapStr.strip()
      .split('\n')
      .map(_.split(' ').toList)
      .map { case dest :: src :: len :: Nil => (dest.toLong, src.toLong, src.toLong + len.toLong) }
      .sortBy(_._2)

    @tailrec
    def chopRecu(inputStart:Long, inputEnd:Long, instructionsRemaining: List[(Long,Long,Long)], result: Queue[(Long,Long)] = Queue.empty): Queue[(Long,Long)] = {
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

    val rrr: ((Long, Long)) => Queue[(Long, Long)] = (input: (Long, Long)) => {
      val interesting = instructions.iterator
        .dropWhile(_._3 <= input._1)
        .takeWhile(_._2 < input._2)
        .toList

      chopRecu(input._1, input._2, interesting)
    }

    rrr
  }
}

// 278755257
// 1926029149 too high
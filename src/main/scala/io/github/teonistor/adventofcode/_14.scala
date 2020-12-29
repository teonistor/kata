package io.github.teonistor.adventofcode

import java.lang.Long.parseLong

object _14 {
  private val mask = "mask = ([X01]{36})".r
  private val assign = "mem\\[(\\d+)\\] = (\\d+)".r

  def _1(input: String): Long = {
    val splitInput = input.split('\n').to(List)

    def convertMask(mask: String) = (
      parseLong(mask.replace('X', '0'), 2),
      parseLong(mask.replace('X', '1'), 2))

    def process(input: List[String], orMask: Long, andMask: Long, mem: Map[Int, Long]): Map[Int, Long] =
      if (input.isEmpty)
        mem
      else input.head match {
        case mask(maskStr) =>
          val (newOrMask, newAndMask) = convertMask(maskStr)
          process(input.tail, newOrMask, newAndMask, mem)
        case assign(location, value) => process(input.tail, orMask, andMask, mem + ((location.toInt, (parseLong(value) & andMask) | orMask)))
      }

    process(splitInput, 0, Int.MaxValue, Map.empty).values.sum
  }

  def _2(input: String): Long = {
    val splitInput = input.split('\n').to(List)

    def convertXorMask(unconvertedMask: String, masks: Set[String]): Set[String] =
      if (unconvertedMask.isEmpty)
        masks
      else unconvertedMask.head match {
        case 'X' => convertXorMask(unconvertedMask.tail, masks.flatMap(u => Set(u + '1', u + '0')))
        case _ => convertXorMask(unconvertedMask.tail, masks.map(_ + '0'))
      }

    def convertMask(unconvertedMask: String) = (
      parseLong(unconvertedMask.replace('X', '0'), 2),
      convertXorMask(unconvertedMask, Set("")).map(parseLong(_, 2)))

    def process(input: List[String], orMask: Long, xorMasks: Set[Long], mem: Map[Long, Long]): Map[Long, Long] =
      if (input.isEmpty)
        mem
      else input.head match {
        case mask(maskStr) =>
          val (newOrMask, newXorMasks) = convertMask(maskStr)
          process(input.tail, newOrMask, newXorMasks, mem)

        case assign(location, value) =>
          val locationLong = parseLong(location)
          val valueLong = parseLong(value)
          process(input.tail, orMask, xorMasks, mem ++ xorMasks.map(xorMask => {
            val newLoc = (locationLong | orMask) ^ xorMask
            (newLoc, valueLong)}))
      }

    process(splitInput, 0L, Set.empty, Map.empty).values.sum

    // Attempt 1 -> 4293442121642 -> too high
    // Discovered problem: I misread requirement 2 (1 sets bit to 1) as 1 flips the bit. We can't use XOR bit map by itself.
    //   -> Need one OR map (for the 1s) and a set of XOR maps (for the Xs)
  }
}

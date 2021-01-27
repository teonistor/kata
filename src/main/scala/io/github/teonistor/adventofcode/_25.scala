package io.github.teonistor.adventofcode

import scala.collection.mutable

object _25 {

  // Not sure if this is adding much benefit to this problem though
  private val memo = mutable.LinkedHashMap.empty[(Int,Int,Int), Int]

  private def transform(seed: Int, subject: Int, loops: Int, cap: Int): Int =
      if (loops == 0)
        seed
      else{
        if (loops % 100 == 0) println(s"Loop count $loops")
        transform(transform(seed, subject, cap), subject, loops - 1, cap)}

  private def transform(seed: Int, subject: Int, cap: Int) =
    if (memo contains (seed, subject,  cap))
      memo((seed, subject,  cap))
else
    {val v=(seed.toLong * subject.toLong) % cap.toLong
  memo.put((seed, subject,  cap), math.toIntExact(v))
      math.toIntExact(v)}

  def _1(input: String): Int = {

//    LazyList.iterate(1)(transform(_,7,20201227))
    val keys = input.split('\n').map(_.toInt).zipWithIndex.toMap

    val loops = LazyList.iterate(0)(_ + 1)
      .map(loops => keys.get(transform(1, 7, loops, 20201227)).map((_, loops)))
      .filter(_.isDefined)
      .map(_.get)
      .take(2)
      .toMap

    println(s"Loops: $loops")

//    LazyList.iterate(0)(_ + 1).filter(l => transform(keys.head._1, 7, loops((keys.head._2 + 1) % 2) + l, 20201227)== 14897079)
//      .take(1)
//      .foreach(println)

    val encryptionKey = keys
      .map(seedAndI => transform(1, seedAndI._1, loops((seedAndI._2 + 1) % 2), 20201227)).toSet

    if (encryptionKey.size != 1)
      throw new RuntimeException(s"Postcondition failure: Both devices should arrive at the same key but ${encryptionKey.size} found!")

//    println(s"All memo: ${memo.to(LazyList).sortBy(_._1).to(List)}")
//    println(s"All memo: $memo")
    memo.zipWithIndex.foreach(u => println(s"${u._2}: ${u._1._1._1} -> ${u._1._2}"))
    encryptionKey.head
  }

  def _2(input: String): Int = {
    0
  }
}


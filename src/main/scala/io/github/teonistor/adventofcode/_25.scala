package io.github.teonistor.adventofcode

import scala.collection.mutable

object _25 {

  private def seek(seed: Int, subject: Int, cap: Int, loopCount: Int, key1:Int,key2:Int,result1:Option[Int], result2:Option[Int]): (Int,Int) =
    if (result1.isDefined&&result2.isDefined)
      (result1.get,result2.get)
    else{
      val possibleResult1 = result1.orElse(if (seed==key1)Option(loopCount) else Option.empty)
      val possibleResult2 = result2.orElse(if (seed==key2)Option(loopCount) else Option.empty)
      seek(transform(seed, subject, cap), subject, cap, loopCount+1, key1, key2, possibleResult1 ,possibleResult2)
     }

  private def transform(seed: Int, subject: Int, cap: Int) =

    {val v=(seed.toLong * subject.toLong) % cap.toLong
      math.toIntExact(v)}

  def _1(input: String): Int = {

    val keys = input.split('\n').map(_.toInt)

    val (result1, result2) = seek(1, 7,20201227, 0, keys(0), keys(1), Option.empty, Option.empty)

    val encryptionKey = Set(
        (keys(0), result2),
        (keys(1),result1)    )
      .map(keyAndLoopCount => LazyList.iterate(1)(transform(_, keyAndLoopCount._1, 20201227)).drop(keyAndLoopCount._2).head)
//
//
//
//    val loops = LazyList.iterate(0)(_ + 1)
//      .map(loops => keys.get(transform(1, 7, loops, 20201227)).map((_, loops)))
//      .filter(_.isDefined)
//      .map(_.get)
//      .take(2)
//      .toMap
//
//    println(s"Loops: $loops")

//    LazyList.iterate(0)(_ + 1).filter(l => transform(keys.head._1, 7, loops((keys.head._2 + 1) % 2) + l, 20201227)== 14897079)
//      .take(1)
//      .foreach(println)

//    val encryptionKey = keys
//      .map(seedAndI => transform(1, seedAndI._1, loops((seedAndI._2 + 1) % 2), 20201227)).toSet

    if (encryptionKey.size != 1)
      throw new RuntimeException(s"Postcondition failure: Both devices should arrive at the same key but ${encryptionKey.size} found!")

    encryptionKey.head
  }
}


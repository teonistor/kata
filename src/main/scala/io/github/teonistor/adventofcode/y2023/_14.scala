package io.github.teonistor.adventofcode.y2023

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

object _14 extends StandardAdventOfCodeSolution[Long] {

  override def _1(input: String): Long = {
    input.split('\n')
      .map(_.toCharArray)
      .transpose
      .map{ chars =>
        val charsStr = chars.mkString
        val cubes = chars.iterator.zipWithIndex
          .filter(_._1 == '#')
          .map(_._2)
          .toList

        val starts = cubes.map(_+1).prepended(0)
        val ends = cubes.appended(chars.length)

        (starts zip ends).map {
          case (start,end) =>
            val howMany = charsStr.substring(start, end).count(_ == 'O')

            howMany.toLong * (chars.length - start) - howMany.toLong * (howMany - 1) / 2
        }
          .sum
      }
      .sum


  }

  override def _2(input: String): Long = {
    val mapp = input.split('\n')

    class CrisCros {

      var targets: Array[CrisCros] = Array.empty
      var count: Int = 0

//      def reset(): Unit = {
//        count = 0
//      }

      def notifyFirst(): Unit = {
        (0 until count).foreach(targets(_).count += 1)
        count = 0
      }

      def notifyLast(): Unit = {
        (targets.length-count until targets.length).foreach(targets(_).count += 1)
        count = 0
      }

      def weight(top:Long) = {
        val result = top * count - count.toLong * (count - 1) / 2
        count = 0
        result
      }

    }

    val horizontals = Array.fill(mapp.length, mapp(0).length)(null.asInstanceOf[CrisCros])
    val verticals = Array.fill(mapp.length, mapp(0).length)(null.asInstanceOf[CrisCros])

    mapp.indices.foreach { row =>
      var keeper = new CrisCros
      mapp(row).indices.foreach { col =>
        mapp(row)(col) match {
          case '#' => keeper = new CrisCros
          case _=> horizontals(row)(col) = keeper
        }
      }
    }

    mapp(0).indices.foreach { col =>
      var keeper = new CrisCros
      mapp.indices.foreach { row =>
        mapp(row)(col) match {
          case '#' => keeper = new CrisCros
          case _=> verticals(row)(col) = keeper
        }
      }
    }

    mapp.indices.foreach ( row =>
      mapp(row).indices.foreach ( col =>
        if (mapp(row)(col) != '#') {
          horizontals(row)(col).targets = horizontals(row)(col).targets appended verticals(row)(col)
          verticals(row)(col).targets = verticals(row)(col).targets appended horizontals(row)(col)
          if (mapp(row)(col) == 'O') {
//            horizontals(row)(col).count += 1
            verticals(row)(col).count += 1
          }
        }
      ))

    val allHorizontals = horizontals.toList.flatten.filter(_!=null)
    val allVerticals = verticals.toList.flatten.filter(_!=null)

    var known = Map.empty[List[Int], Int]


    def oneRound(i:Int): Unit = {
      allVerticals.foreach(_.notifyFirst())
      allHorizontals.foreach(_.notifyFirst())
      allVerticals.foreach(_.notifyLast())
      allHorizontals.foreach(_.notifyLast())


      if (known != null) {
        val arr = allVerticals.map(_.count)
        if (known contains arr) {
          println(s"Found repetition at round $i from round ${known(arr)}")
          known = null
        } else
          known = known.updated(arr, i)
      }
    }



//    println("Rock count by col after 0 rounds: ")
//    verticals.foreach(row => println(row.iterator.map(u => if (u!=null) String.format("%4d", u.count) else "   #").mkString))
//
//    oneRound()
//
//    println("Rock count by col after 1 rounds: ")
//    verticals.foreach(row => println(row.iterator.map(u => if (u!=null) String.format("%4d", u.count) else "   #").mkString))
//
//    oneRound()
//
//    println("Rock count by col after 2 rounds: ")
//    verticals.foreach(row => println(row.iterator.map(u => if (u!=null) String.format("%4d", u.count) else "   #").mkString))

    // 5 for example
    (1 to 115).foreach { i =>
      if (i % 1000000 == 0)
        println("Round "+ i)

//      println(verticals.indices.map { row =>
//          val top = verticals.length - row
//          verticals(row).iterator
//            .filter(_!= null)
//            .map(_.weight(top)).sum
//        }
//        .sum)

      oneRound(i)
    }

    allVerticals.foreach(_.notifyFirst())
    allHorizontals.foreach(_.notifyFirst())
    allVerticals.foreach(_.notifyLast())


    val endMap = Array.fill(mapp.length, mapp(0).length)('.')
    endMap.indices.foreach { row =>
      var lastFoundSpace = 0
      endMap(row).indices.foreach { col =>

        mapp(row)(col) match {
          case '#' =>
            lastFoundSpace = col + 1
            endMap(row)(col) = '#'
          case _=>
            if (col >= lastFoundSpace + horizontals(row)(col).targets.length - horizontals(row)(col).count)
              endMap(row)(col) = 'O'
        }
      }
    }

    println("-------")
    println(endMap.map(_.mkString).mkString("\n"))
    println("-------")

    endMap.indices.iterator
      .map(i => (endMap.length.toLong - i) * endMap(i).count(_== 'O'))
      .sum

//    verticals.indices.map { row =>
//      val top = verticals.length - row
//      verticals(row).iterator
//        .filter(_!= null)
//        .map(_.weight(top)).sum
//    }
//      .sum
  }

}

// 110407
// 87273
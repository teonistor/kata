package io.github.teonistor.adventofcode.y2020

import io.github.teonistor.adventofcode.AdventOfCodeTestBase

class _11Test extends AdventOfCodeTestBase {
  testAndRun(11, _11._1, _11._2,
    "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL",
    37,
    26,
    "LLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLL.L.LLLLLL.LLLLLL.LLL.LLLLLLLL.LLLLL.L.LLLLLLLLLLLLLLLL.LLLLLLL\nLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLL.LLL.LLLLLLLLLLLLL.L.LLLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLL.LLL..LLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLL.L.LLLLLLLLLLLLLLLL.LLLLL.L\nLLLLL.LLLLLLLLL.LLLLLLL.LLLL.L.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLL\nL.LLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LL.LLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LL.LLLLLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLL\nLLLLLLLLLLLLLLLLLLL.LLL.LLLLLL.L.LLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LL.LLLL\nLLLLL.LLLLLLLLL.LLLLLLLLLLL.LL.LLLLL.LLLLLL.LL.LLLLLLLL..LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLL.LL.LLL.LLLLLLLLLLLLL.LLLL.L.LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLL.L.LLLLLLL\n......L....L...L...L.....L........LLL..LL..LL..L.L..LL.L..L.....LL......L.LLL.L..L....LLL.......LL\nLLLLL.LLLLLLLL.LLLLLLLL.LLLLL..LLLLL.LLL.LLLL..LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\nLLLLL.LLLLLLLLL.LL.LLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LL..L.L\nLLLLL.LLLLLLL...LLLLLLLLLLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLL.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLL..LLLLLLL.LLLLL.LL.LLL.LLLLLLLLL.LLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLL.LL.LLLLLLLLLLLL..LLLLLLLL.LLLLLLLLL.LLLLLLLLLLL.LLLL.LL.LLLLLL.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLL.LL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LL.LLLL\nL.LL..LLLLLLLLL.LLLLLLL.L.LLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL..LLLLLLLLL.LLLLLLLLLLLLLL\n.L.......L..LL.....L.L.....L..............L.L.L.LL..LL.L.L..LLL.L..LL.............LLL.L......L.LL.\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LL.LLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.L.LLLL.LLLLLLL\nLLLLL.LLL.LLLLL.LLLLLLL.LLLLLL..LLLLLLLLLLLLLLLLLLLL.LLL.L.LLLLLLLLLLLLLL.LLLLLLLLLL.LLLLL..L.LLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLL..LLLLLL.LLLLLLLLL.LLLLLLL.LL.LL.\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLL.L.LL.LLLLL..L..LLL.L.LLLLLLL..LLLLLLLL..LLLLL.LLLLLLL\nLLLLL.LLL.LLLLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLL.LLL.LLLLL.LL.LL.LLLL.LLLLLL.LL.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLL..LLLLLLLLLLLLL.L.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LL.LLLL\nLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.L.LLL.LLLLLL.LL.LLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL.LLL.LLLLLLLLLL\n......L.....L...LL.L.L..L...L....L...LLL...LLLLL..L.....L.....L..L......L...L.L.......L..L.LL...L.\nLL.LLL.LLLLLLLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLL.LL.LLLL.LLLLLLLLL.LL.LLLLLLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLL..LLLLLLLLL..LLLLLLLL.LLLL.LLL..LLLLLLLLLLLLLLLL.LLLL.L.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL..LLLLLL.LLLLLL.LLLLLL.LL.LLLLLLL\nLLLLL..LL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLL.LLLL..LLLLLLL\n.LLL...LL.....L.L..L.LL..LL......LL.L.L........L.L.....LLLL.L.L.......L......LL.....LLLL....L.L.LL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLL\nLLLL..LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL.L..LLLLLLL..LLLL.L.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLLLLL.LL.LLLLLLL.LLLL.L.LLLLLLLLLLLL.LL.LLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL..LLLLL.LLLLLLL\nLLLLLLLLLLL.LLL.LLLLLLLL.LLLLL.LLLLL.LLLLLLLLLLLLLLLLLLL.LLLL.LLL.LLLLLLLLLLLLLLL.L.LLLLLLLLLLLLLL\n.L.L...L...L.....L...LLLLLLL.LL....LL..LLL...L...L.LLL..L.L...L.L.L....L.LL.LLL..L....L..LL..L...L\nLLLLLLLLLLL.LL..LLLL..L.LLLLLL.LLLLLLLLLLLL.LL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL....LLLLLLLLLLL\nLLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLL.LL.LLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLL.LLLLLLLL.LLLL..LLL.LLLLLLLLLLLLLLL.LLLLLL.L.LLLL.LL.LLLL.LLLLLLLLLL..LLLLLLL\nLLLLL..LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLL\nLLLLL.LLLLLLLLL.LLLL.LLLLLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.\nLLLLL.LLLLLLLLLLLLL.LLL.LLLLLL..LLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLL..LLLL.LLLL.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL\nLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL.LLL.L.LLLLLLLLL..LLLLL.LL..LLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nL..L.L.LLL.LL.......LL.LL..LL.L...L..LLL........L.LL....LL...LL.L.....LL......L.L.L...L.L.L..L..L.\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLL..LLLL.LL.LLLLLLLLL.LLLLLL.LLLLLLL\nLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLL.L.LLLLL\nLLLLL.LLLLLLL.L.L.L.LLL..LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLL.LLLLLLLLLLLLLL.LLLLL\nLL....LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL\nL.LLL.LLLLLLLLL.LL.LL.L.LLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL..LLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL..LLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL..LLLLLLLLLLLLLL.LLLL.LLLLLLLL..LLLLLLLLLLLLLLL..LLLLLL..L.LLLL\nLLLLL.LLL.LL.LL.L.LLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL\n..LL....L.LL...L.......L.L...L.L..LLL....L...L.L..L...L......L...LLL.L.L...L..L.LL...L..L..L.L...L\nLLLLL.LLLLLL....LLLLLLL.LLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLL..LLLLLL.LLLLLLL\nLLLLL..LLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LL.LLLLLLLLLL.LLLL..LLLLLLLLLLLLL.L.LLLLLLLLLLLLLLLL.LLLL.LL\nLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLL.L.LLLLLLL\nLLLLL.LLLLLLLLL.L.LLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLL.LLLLL..LLLLL.LLLLLLL\nLLLL..LLLLLLLLLLLLLLLLL.LLLLLL.LLLLL.LLL.LLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLL.L\nLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLL.LLLLLLLLLLLLL.LLLLLLLLLLLL.L\nLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLL.LLLLL..LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLLLLLLLLLLLLLLLL.LL.LLL.LLLLL.LLLLLLLLL.LLLL.LLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLL.LLLL.LL\nLLLLL.LLLLLLLLL.LLLLLLL..LLLLL.LLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL.L.LLLL.LLLL.LL\n.L.....L.LL.......L.L..L.L...LLL.L.LL..LLL..L.L....L........L..L.L...L....L...L.LL..L.....L..LL..L\nLLLLL.LLLLLLLLL.LLLLLLL.LLLL.LLL.LLLLLLLLLLLLL.LLLLLLLLL.LL.LLLLL.LLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLL\nLLLLLLLLLLLLLLL.LL.LLLL.LLLLLL.LLLLL.LL.LLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLLLLLL.LLLLL.LLLLLL.LLLLLLL\nLLLLL.LLLL..LLL.LLLLLLL.LL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLL..LL.LLL\nLLLLL.LLLLLLLLLLLLLLLLLLLLLL.L.LLLLLL.L.LLLLLLLLLLL.LLLL.LL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLL..LLLLLLL\nLLLLL.L.LLLLLL..LLLLL.L.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL..LLLLLLLL.LLLLLL..LLLLLLLLLLLLLLLLLLLLLLLL\nLL.LL.LLLLLLLL.LLLLLLL.LLL.LLL...LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LL.L.LLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLL.LLLLLLL.LLLLLL.LL.LLLLLL.LL.LLLL\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLL..LLLLL.LLL.LLLLLLLLL.LLLLL.LLLLLL.L.LLLLLLL.LLLLLL.LL.LLLLL.LLLLLLLL\nLLLLL.LLL.LLLLL.LLLLLLLLLL.LLL.LLLLL.LLLLLLLLLLLLL.LLLLLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n........LL..LLL.....L.L...LLL...L.........L.L...L..L...LL.L.....L....L....L.....L....L....L.LLL...\nLLLLL.LLLLL..LLLLLLLLLLLLL.LLL.LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLL.LLLLL.LLLLLLLLLLL.LLL.LL.LLLLLLL\nLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLLL.LLLL.LL.LL\nLL.LL.LLLLLLLL.LLLLL.LLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLL\nLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLL.LLLL.LLLLLL.LLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLL.LLLLL.L\nLLLLL..LLLLLLLL.LLLLLLL.LLLLLLLLL.LL.LLLLLLLLL.LLLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLLLLLLLL.LLLLLL..LLLLLL.LLLLL.LLLLLLLL.LL.LLLLLLL.LLLLL.LL.LLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL\nL.LL.....LL......LL.L.LL...LL..........LL.LLL...L..L..LL....L......L.....LL.....LL.L..L.LL.L.L....\nLLLLL.LLLLLLLLLL.LLLLLL.LLLLLL.LLLLLLLLLLLLLLL.LLLL.LL.L.LLLLL.LLLLLLLLLL.LLLLLLLLLLLLLL.L.LLLLLLL\nLLLLLLL.LLLLLLL.LLLLLLL.LLLLLL.LL.LLLLLLLLLLLL.LLLLLLLLL.L.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLL\nLLLLLLLLLLLLLLL.LL.LLLL.LLLLLL.LL.LL.LLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL\nLLLLL.LLLLLLLLL.LLLLLL..LLL.LL.LLLLL.LLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLL.LLLL.L.\nLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLL.LLLLLL.LLLL.LL\nLLLLL.LLLLLLLLL..LLLLLL.LLLLLL.LLLL.LLLLLL.LLL.LLLLL.LLL.LLLLLLL..LLLLLLLLLLLLL.LLLLLLLLLL.LLLLLLL"
  )
}
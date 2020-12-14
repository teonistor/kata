package io.github.teonistor.adventofcode

object _12 {

  private trait Facing {
    def f: (Int, Int)
    def r: Facing
    def l: Facing
  }

  private val e: Facing = new Facing {
    override def f: (Int, Int) = (1, 0)
    override def r: Facing = s
    override def l: Facing = n
  }

  private val s: Facing = new Facing {
    override def f: (Int, Int) = (0, -1)
    override def r: Facing = w
    override def l: Facing = e
  }

  private val w: Facing = new Facing {
    override def f: (Int, Int) = (-1, 0)
    override def r: Facing = n
    override def l: Facing = s
  }

  private val n: Facing = new Facing {
    override def f: (Int, Int) = (0, 1)
    override def r: Facing = e
    override def l: Facing = w
  }

  private def turnL(f: Facing, q: Int): Facing = if (q == 0) f else turnL(f.l, q - 1)
  private def turnR(f: Facing, q: Int): Facing = if (q == 0) f else turnR(f.r, q - 1)

  def _1(input: String): Int = {
    def nav(x: Int, y: Int, instructions: List[(Char, Int)], facing: Facing): (Int, Int) = {
      if (instructions.isEmpty) {
        return (x, y)
      }

      instructions.head._1 match {
        case 'N' => nav(x, y + instructions.head._2, instructions.tail, facing)
        case 'S' => nav(x, y - instructions.head._2, instructions.tail, facing)
        case 'E' => nav(x + instructions.head._2, y, instructions.tail, facing)
        case 'W' => nav(x - instructions.head._2, y, instructions.tail, facing)
        case 'L' => nav(x, y, instructions.tail, turnL(facing, instructions.head._2 / 90))
        case 'R' => nav(x, y, instructions.tail, turnR(facing, instructions.head._2 / 90))
        case 'F' => nav(x + facing.f._1 * instructions.head._2, y + facing.f._2 * instructions.head._2, instructions.tail, facing)
      }
    }

    val instructions = input.split('\n').to(List).map(line => (line(0), line.substring(1).toInt))
    val destination = nav(0, 0, instructions, e)
    math.abs(destination._1) + math.abs(destination._2)
  }

  def _2(input: String): Int = {
    def deTailTurn(instructions: List[(Char, Int)])={
      if (instructions.head._2 <91) {
        instructions.tail
      } else {
        instructions.tail.prepended((instructions.head._1, instructions.head._2 - 90))
      }
    }

    def nav(xBoat: Int, yBoat: Int, xWay: Int, yWay: Int, instructions: List[(Char, Int)]): (Int, Int) = {
      if (instructions.isEmpty) {
        return (xBoat, yBoat)
      }
      val delta = instructions.head._2

      instructions.head._1 match {
        case 'N' => nav(xBoat, yBoat, xWay, yWay + delta, instructions.tail)
        case 'S' => nav(xBoat, yBoat, xWay, yWay - delta, instructions.tail)
        case 'E' => nav(xBoat, yBoat, xWay + delta, yWay, instructions.tail)
        case 'W' => nav(xBoat, yBoat, xWay - delta, yWay, instructions.tail)
        case 'L' => nav(xBoat, yBoat, -yWay, xWay, deTailTurn(instructions))
        case 'R' => nav(xBoat, yBoat, yWay, -xWay, deTailTurn(instructions))
        case 'F' => nav(xBoat + xWay * delta, yBoat + yWay * delta, xWay, yWay, instructions.tail)
      }
    }

    val instructions = input.split('\n').to(List).map(line => (line(0), line.substring(1).toInt))
    val destination = nav(0, 0, 10, 1, instructions)
    math.abs(destination._1) + math.abs(destination._2)
  }
}

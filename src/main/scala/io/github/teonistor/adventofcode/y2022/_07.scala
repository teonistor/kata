package io.github.teonistor.adventofcode.y2022

import io.github.teonistor.adventofcode.StandardAdventOfCodeSolution

import scala.annotation.tailrec

object _07 extends StandardAdventOfCodeSolution[Int] {

  trait HasSize {
    def size: Int
  }
  case class Dir(path: List[String], dirs: Map[String, Dir], files: Map[String, File]) extends HasSize {
    lazy val size = LazyList(dirs.values, files.values).flatten.map(_.size).sum
  }
  case class File(name: String, size: Int) extends HasSize

  private val cd = "\\$ cd (\\w+|\\.\\.|/)".r
  private val ls = "\\$ ls".r
  private val dir = "dir (.+)".r
  private val file = "(\\d+) (.+)".r

  def _1(input: String): Int =
    Some(input.split("\n").toList)
      .map(newParseFs(_))
      .map(recursiveLs).get
      .map(_.size)
      .filter(_<=100000)
      .sum

  def _2(input: String): Int = {
    val maybeTuple = Some(input.split("\n").toList)
      .map(newParseFs(_))
      .map(fs => (fs.size - 40000000, recursiveLs(fs)))
    val option = maybeTuple
      .map(sf => {
//        val aaaa = 40000000 - sf._1
        sf._2.map(_.size)
          .filter(_ >= sf._1)
          .toList
      })
    option
      .get
      .min
  }

  @tailrec
  def newParseFs(input: List[String],
                 currentPath:List[String]          = List.empty,
//                 dirs:Vector[(List[String, ])]
                 files:Vector[(List[String],File)] = Vector.empty): Dir = {
    if (input.isEmpty)
      newReconstructFs(files.to(LazyList)
        .map(f => (f._1.reverse, f._2)))

    else
      input.head match {
        case cd("/") => newParseFs(input.tail, List.empty, files)
        case cd("..") => newParseFs(input.tail, currentPath.tail, files)
        case cd(dir) => newParseFs(input.tail, currentPath.prepended(dir), files)
        case ls() => newParseFs(input.tail, currentPath, files)
        case dir(_) => newParseFs(input.tail, currentPath, files)
        case file(size, name) => newParseFs(input.tail, currentPath, files.appended((currentPath, File(name, size.toInt))))
    }
  }

  def newReconstructFs(files: Seq[(List[String],File)],name: Option[String] = None): Dir ={
    val theseFiles = files.filter(_._1.isEmpty).map(f => (f._2.name, f._2))
    val theseDirs = files.filter(_._1.nonEmpty)
      .groupBy(_._1.head)
      .map(t => newReconstructFs(t._2.map(f => (f._1.tail, f._2)), Some(t._1)))
      .map(d => (d.path.head, d))
    Dir(name.toList, theseDirs.toMap, theseFiles.toMap)
  }

  @tailrec
  def parseFs(input: List[String],
              fs:Dir                  = Dir(List.empty, Map.empty, Map.empty),
              parentDirs:Map[Dir,Dir] = Map.empty,
              currentDir:Option[Dir]  = None): Dir = {
    if (currentDir.isEmpty)
      parseFs(input, fs, parentDirs, Some(fs))
    else if (input.isEmpty)
      fs
    else {
      input.head match {
        case cd("/") => parseFs(input.tail, fs, parentDirs, Some(fs))
        case cd("..") => parseFs(input.tail, fs, parentDirs, currentDir.flatMap(parentDirs.get))
        case cd(dir) => {
          parseFs(input.tail, fs, parentDirs, currentDir.flatMap(_.dirs.get(dir)))
        }
        case ls() => parseFs(input.tail, fs, parentDirs, currentDir)
        case dir(dir) =>
          val newDir = Dir(currentDir.get.path.prepended(dir), Map.empty, Map.empty)
          val (newFs, newParentDirs) = recursiveSet(newDir, currentDir, parentDirs)
          parseFs(input.tail, newFs, newParentDirs, currentDir.map(d => recursiveCd(newFs, d.path.reverse)))
        case file(size, name) =>
          val file = File(name, size.toInt)
          val newDir = Dir(currentDir.get.path, currentDir.get.dirs, currentDir.get.files.updated(name, file))
          val (newFs, newParentDirs) = recursiveSet(newDir, currentDir.flatMap(parentDirs.get), parentDirs.removed(currentDir.get))
          parseFs(input.tail, newFs, newParentDirs, currentDir.map(d => recursiveCd(newFs, d.path.reverse)))
      }
    }
  }

  def recursiveSet(dir: Dir, maybeParent: Option[Dir], parentDirs: Map[Dir, Dir]): (Dir, Map[Dir, Dir]) = {
    maybeParent.map(parent => {
//      println(dir)
//      println(maybeParent)
//      println(parentDirs.iterator.map(kv => s"${kv._1.path} -> ${kv._2.path}").toList)
      val newParent = Dir(parent.path, parent.dirs.updated(dir.path.head, dir), parent.files)
      val newParentDirs = parentDirs.updated(dir, newParent)
      recursiveSet(newParent, parentDirs.get(parent), newParentDirs.removed(parent))
    })
      .getOrElse((dir, parentDirs))
  }

  @tailrec
  private def recursiveCd(dir: Dir, path: List[String]): Dir =
    if (path.isEmpty)
      dir
    else
      recursiveCd(dir.dirs(path.head), path.tail)

  private def recursiveLs(dir: Dir): Seq[Dir] =
    dir.dirs.values.to(LazyList)
      .flatMap(recursiveLs)
      .prepended(dir)
}

package lectures.part3fp

import scala.annotation.tailrec

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val characters = List("a", "b", "c", "d")
  // print - List("a1", "a2",..."d4")

//  def allCombos(a: List[String], b: List[Int]) : List[String]  = {
//    @tailrec
//    def loop(x: List[String], y: List[Int], newList: List[String]): List[String] =
//      if (x.isEmpty || y.isEmpty) newList
//      else loop(x.tail, y,  newList :+ (x.head + y.head))
//    loop(a, b, List())
//  }

  // lks
  def allCombos(a: List[String], b: List[Int]) =
    a.flatMap((x: String) => b.map((y: Int) => x + y))

  println(allCombos(characters, numbers))

  // solution
  val combinations = numbers.flatMap(n => characters.map(c => "" + c + n))
  println(combinations)

  val colors = List("black", "white")
  val combinations1 = numbers.flatMap(n => characters.flatMap(c => colors.map(color => c + n + "-" + color)))
  val combinationsWithFilter = numbers.filter(_ % 2 == 0).flatMap(n => characters.flatMap(c => colors.map(color => c + n + "-" + color)))
  println(combinations1)
  println(combinationsWithFilter)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- characters
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

}


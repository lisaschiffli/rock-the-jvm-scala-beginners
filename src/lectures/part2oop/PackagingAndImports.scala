package lectures.part2oop

import lectures.part2oop.OOBasics.Writer
//import playground._ // {Cinderella, PrinceCharming}
import playground.{Cinderella => Princess, PrinceCharming}  //aliasing

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val princess = new Princess
//  val princess = new playground.Cinderella  // playground.Cinderella = fully qualified name

  // packages are in hierarchy
  // matching folder structure

  // package object - Scala specific
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  val date = new Date
  // use fully qualified names
//  val sqlDate = new java.sql.Date(2018, 5,4 )

  // use aliasing
  val sqlDate = new SqlDate(2018, 5, 4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}

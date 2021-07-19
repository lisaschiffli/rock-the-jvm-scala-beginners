package lectures.part2oop

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
//    def hangsOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_! : String = s"$name, what the heck?!"   // the space between ! and : is important
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie."
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times."
    def learns(topic: String) : String = s"$name learns $topic"
    def learnsScala: String = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  // infix notation = operator notation

  // "operators" in Scala
  val tom = new Person ("Tom", "Fight Club")
  println(mary + tom)

  println(1 + 2)
  println(1.+(2))
  // ALL OPERATORS ARE METHODS

  // prefix notation
  val x = -1
  val y = 1.unary_-
  // unary_prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // post-fix notation - takes no parameters
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())  // equivalent
  // When compiler sees an object called like a function, it looks for an apply() method and executes it

//  val maryNickname = mary + "the rockstar"
//  println(maryNickname.name)
  println((mary + "the Rockstar")())   // println(mary + "the Rockstar").apply())
//  val olderMary = +mary
//  println(olderMary.age)
  println((+mary).age)
  println(mary learns "Scala")
  println(mary learnsScala)
  println(mary(4))


}

package lectures.part2oop


object Objects {
 // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ( "static" )
 object Person { // type + its only instance
  // "static"/"class" - level functionality
  val N_EYES = 2

  def canFly: Boolean = false

  // factory method - sole purpose is to build person from some factors
  def apply(mother: Person, father: Person): Person = new Person("Bobbie")
 }


 class Person(val name: String) {
  // instance-level functionality
 }

 // COMPANIONS - object & class same name
 def main(args: Array[String]): Unit = {

  println(Person.N_EYES)
  println(Person.canFly)

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val bobbie = Person(mary, john)
  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit


 }


}

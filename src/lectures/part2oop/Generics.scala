package lectures.part2oop

object Generics extends App {
  class MyList[+A] {
    // if you have a list of cats and add a dog, the list becomes a list of animals
    def add[B >: A](element: B): MyList[B]= ???

  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???

  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) ??? HARD QUESTION. -> Answer: We return a list of Animals
  // if you have a list of cats and add a dog, the list becomes a list of animals
  //  def add[B >: A](element: B): MyList[B]= ???

  // 2. No, INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no. CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // <: is a subtype of
  // >: is a supertype of
  class Cage[A <: Animal] (animal: A)
  val cage = new Cage(new Dog)





}

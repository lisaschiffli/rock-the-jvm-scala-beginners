package lectures.part3fp

object WhatsAFunction extends App {
  //  def doubler(n: Int) = n * 2
  //  println(doubler(2))

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTION ARE OBJECTS

  //  val concat: ((String, String) => String) = new Function2[String, String, String] {
  //    override def apply(a: String, b: String): String = a + b
  //  }
  def concat: ((String, String) => String) = (a: String, b: String) => a + b

  println(concat("Hello ", "World"))

  // Define a function which takes an int and returns another function which takes an int and returns an int

  // Function1[Int, Function1[Int, Int]]
  //  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
  //    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
  //      override def apply(y: Int): Int = x + y
  //    }
  //  }

  val superAdder: (Int) => ((Int) => Int) = (x: Int) => (y: Int) => x + y
  val superAdd = (x: Int) => (y: Int) => x + y

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function, receives a parameter and returns a function that also receives a parameter
  println(superAdd(4)(5))

  trait MyFunction[A, B] {
    def apply(element: A): B
  }
}
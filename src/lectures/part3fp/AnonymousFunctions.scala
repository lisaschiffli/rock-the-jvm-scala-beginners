package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2
  val doubler1: Int => Int = (x: Int) => x * 2
  val doubler2: Int => Int = x => x * 2

  // multiple params in a lambda
  val adder = (a: Int, b: Int) => a + b
  val adder1: (Int, Int) => Int = (a: Int, b: Int) => a + b
  val adder2: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething = () => 3
  val justDoSomething1: () => Int = () => 3

  println(justDoSomething)     // returns the lambda function itself
  println(justDoSomething())   // returns 3 - actually calls the function

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE syntactic sugar
  val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer1: Int => Int = _ + 1    // equivalent to x => x + 1

  val niceAdder: (Int, Int) => Int = (a, b) => a + b
  val niceAdder1: (Int, Int) => Int = _ + _   // equivalent to (a,b) => a + b




}

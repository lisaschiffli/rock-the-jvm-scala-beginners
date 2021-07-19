package lectures.part2oop

object Exceptions extends App {
  // throwing and catching exceptions

//  val aWeirdValue = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  def getInt(withExceptions: Boolean):Int =
    if(withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(false)
  } catch {
    case e: RuntimeException=> 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects - like logging to a file, closing files
    println("finally")
  }

  println(potentialFail)

  // How to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  // 1. crash your program with OutOfMemoryError - OOM
//  val array = Array.ofDim(Int.MaxValue)

  // 2. crash your program with Stack Overflow Error
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  // 3. Pocket Calculator

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int)= {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0 ) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
//  println(PocketCalculator.)
//  println(PocketCalculator.)


}

package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  //  println(factorial(10))
  //  println(factorial(5000))  crashes with stackoverflow

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression

    factHelper(n, 1)
  }

  //  println(anotherFactorial(10))
  //  println(anotherFactorial(20000))

  // WHEN YOU NEED LOOPS, USE _TAIL_RECURSION

  /*
  anotherFactorial(5) = factHelper(5,1)
  = factHelper(4, 5 * 1)
  = factHelper(3, 4 * 5 * 1)
  = factHelper(2, 3 * 4 * 5 * 1)
  = factHelper(1, 2 * 3 * 4 * 5 * 1)
  = 2 * 3 * 4 * 5
   */

  def stringConcat(str: String, n: Int): String = {
    @tailrec
    def helper(y: Int, accumulator: String): String =
      if (y <= 1) accumulator
      else helper(y - 1, accumulator + str)

    helper(n, str)
  }

  println(stringConcat("hello", 5))

  @tailrec
  def concatenateTailRec(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenateTailRec(aString, n - 1, aString + accumulator)

  println(concatenateTailRec("hello", 5, ""))

  //  def isPrime(n: Int): Boolean = {
  //    def isPrimeUntil(t: Int): Boolean =
  //      if (t <= 1) true
  //      else n % t != 0 && isPrimeUntil(t - 1)
  //
  //    isPrimeUntil(n / 2)
  //  }


  //  def isPrime(n: Int): Boolean = {
  //    @tailrec
  //    def isPrimeUntil(t: Int, status: Boolean): Boolean = {
  //      if (!status) false
  //      else if (t <= 1 ) true
  //      else isPrimeUntil(t - 1, n % t != 0)
  //    }
  //
  //    isPrimeUntil(n / 2, true)
  //  }
  //
  //  println(isPrime(13))

  def isPrime1(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, true)
  }

  //  println(isPrime(2003))
  //  println(isPrime(629))

  //  def fibonacci(n: Int): Int =
  //    if (n <= 2) 1
  //    else (fibonacci(n-1) + fibonacci(n-2))

  //  def fibonacci(n: Int): Int = {
  //    @tailrec
  //    def fibHelper(x: Int, a1: Int, a2: Int): Int = {
  //      if (x <= 2) a2
  //      else fibHelper(x - 1, a2, a1 + a2)
  //
  //    }
  //    fibHelper(n, 1, 1)
  //  }
  //
  //  println(fibonacci(8))

  // Alt isPrime from students
  @tailrec
  def isPrime2(n: Int, x: Int): Boolean =
    if (x > n / 2) true
    else if (n % x == 0) false
    else isPrime2(n, x + 1)

  println(isPrime2(23, 2))

  def isPrime3(number: Int): Boolean = {
    @tailrec
    def dividesWithoutRest(number: Int, divisor: Int): Boolean =
      if (divisor == 1) true
      else if (number % divisor == 0) false
      else dividesWithoutRest(number, divisor - 1)

    if (number <= 2) true
    else dividesWithoutRest(number, number / 2)
  }


  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println(fibonacci(8))
}

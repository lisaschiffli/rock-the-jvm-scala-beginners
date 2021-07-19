package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random()
  val x = random.nextInt(10)
  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"   // _ = WILDCARD
  }
  println(x)
  println(description)

  // 1. Decompose values - can extract values out of a case class object
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)

  /*
  1. cases are matched in order
  2. what if no cases match? - MatchError - Be sure to use WILDCARD to cover all cases
  3. type of the Pattern Match expression? - the lowest common type to all the results (ie String & Int would be Any type)
      unified type of all the types in all the cases
  4. PM works really well with case classes
   */

  // PM on sealed hierarchies - when this is compiled, it will warn that match may not be exhaustive
  // because there's no default wildcard case
  // This warning is because class is SEALED. Without sealed, there is no warning.
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal
  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed.")
  }

  // don't match everything
  // Yes, this works, but it's overkill. Can be solved more simply.
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  val isEvenCond = if (x % 2 == 0) true else false // ???
  val isEvenNormal = x % 2 == 0

  trait Expr
  case class Number (n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod (e1: Expr, e2: Expr) extends Expr
//  def show (e: Expr): String = {
//    val expression = e match {
//      case Sum(e1, e2) => s"$e1 + $e2"
//      case Prod(e1, e2) => s"$e1 * $e2"
//    }
//    expression
//  }
//
  val myExpr: Expr = Prod(Sum(Number(42),Number(42)), Number(2))
//  println(show(myExpr))

    def show(e: Expr): String = e match {
      case Number(n) => s"$n"
      case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
      case Prod(e1, e2) => {
        def maybeShowParenthesis(exp: Expr) = exp match {
          case Prod(_, _) => show(exp)
          case Number(_) => show(exp)
          case _ => "(" + show(exp) + ")"
        }
        maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
      }
    }

    println(show(myExpr))

}

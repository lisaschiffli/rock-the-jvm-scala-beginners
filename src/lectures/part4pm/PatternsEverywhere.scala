package lectures.part4pm

object PatternsEverywhere extends App {
  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES
  /*
  try {
    // code
  } catch (e) {
    e match {
      case e: RuntimeException => "runtime"
      case npe: NullPointerException => "npe"
      case _ => "something else"
    }
  }
   */

  // big idea #2
  // generators are also based on PATTERN MATCHING
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  println(filterTuples)

  // case classes, :: operators, ...

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)

  // multiple value definitions based on PATTERN MATCHING

  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function literal - based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  }
  println(mappedList)

  // same as
  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }
  println(mappedList2)

  val foo = {
    println("foo Initialized")
    1
  }

  lazy val foo1 = {
    println("foo1 Initialized")
    1
  }

  println("foo and foo1 declared but not called yet")
  println(foo + foo1)
  println(foo * foo1)


}

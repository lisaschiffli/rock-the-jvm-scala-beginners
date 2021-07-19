package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {
  // 1. constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2. Match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => "anything"
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something."
  }

  // 3. tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1, 1) => "something"
    case (something, 2) => s"I've found $something."
  }
  //PMs can be NESTED
  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => s"I've found $v."
  }

  // 4. case classes - constructor pattern
  // PMs can be nested with Case Classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => "return something"
    case Cons(head, tail) => "return something else" // head = 1, tail = Cons(2, Empty)
    case Cons(head, Cons(subhead, subtail)) => "return" // head = 1, subhead = 3, subtail = 3

  }

  // 5. List patterns
  val sStandardList = List(1,2,3,42)
  val standardListMatching = sStandardList match {
    case List(1, _, _, _)  => "return a"  // extractor
    case List(1, _*)       => "return b"     // list of arbitrary length
    case 1 :: List(_)      => "return c"    // infix pattern
    case List(1,2,3) :+ 42 => "return d"// infix pattern
  }

  // 6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => 42   // explicit type specifier
    case _ => 99
  }

  // 7. name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_,_) => "return"  //name binding - use the name later(here)
    case Cons(1, rest @Cons(2, _)) => "return" // name binding inside nested patterns
  }

  // 8. multi-patterns
//  val multipattern = aList match {
//    case Empty | Cons(0, _) => "return"  // compound pattern (multi-pattern)
//  }

  // 9. if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => "return"  //if guard filters out
  }

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)   // RETURNS "a list of strings"  !!!!!
  // JVM trick question
  // Due to backward compatibility before Java supported generics - JVM now erases generic types
//  val numbersMatch = numbers match {
//    case listOfStrings: List => "a list of strings"
//    case listOfNumbers: List => "a list of numbers"
//    case _ => ""
//  }


}

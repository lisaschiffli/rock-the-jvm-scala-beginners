package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2
  println(x)

  // Instructions (DO) vs Expressions (VALUE)
  // Instructions - imperative lang like Java or Python
  // Expressions - something that has a value and/or type
  // Every single bit of Scala code will compute a value
  // IF expression  - IF EXPRESSION  not a if true then do something. Instead, if true, then value
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)

  //LOOPS
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  //NEVER WRITE THIS AGAIN
  // EVERYTHING in Scala is an Expression

  var aVariable = 2

  val aWeirdValue = aVariable = 3 // Unit === void
  println(aWeirdValue) //  ()   this is the only value Unit Type can hold

  // reassigning a variable is a side effect
  // Side effects in Scala are expressions returning Unit
  // Side effects are reminiscent of imperative programming
  // While loops are side-effects
  // side effects: println(), whiles, reassigning

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "Hello" else "Goodbye"
  }

  var y = 1
  while (y <= 5) {
    println(y)
    y += 1
  }

}

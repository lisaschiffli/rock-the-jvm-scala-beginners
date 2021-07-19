package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")

  //  val writer = new Writer("Mark", "Twain", 1835)
  //  println(writer.fullName())
  //  val myBook = new Novel("Tom Sawyer", 1865, writer)
  //  println(myBook.novelName)
  //  println(myBook.authorAge)
  //  println(myBook.isWrittenBy())
  //  val newBook = myBook.copy(2021)
  //  println(newBook.releaseYear)
  //
  //  val myCtr = new Counter(42)
  //  println(myCtr.currentCount())
  //  val newCtr = myCtr.increment(42)
  //  println(newCtr.currentCount())

  val counter = new Counter
  counter.inc.inc.inc.print
  counter.inc(10).print


  // constructor
  class Person(name: String, val age: Int = 0) {

    // method
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

    // overloading
    def greet(): Unit = println(s"Hi, I am $name")

    // multiple constructors
    def this(name: String) = this(name, 0)

    def this() = this("John Doe")
  }

  //class Writer(val firstName: String, val surName: String, val birthYear: Int) {
  //  def fullName(): String = firstName + " " + surName
  //}
  //
  //class Novel(val novelName: String, val releaseYear: Int, val author: Writer) {
  //  def authorAge(): Int = releaseYear - author.birthYear
  //  def isWrittenBy(): String = author.fullName()
  //  def copy(newPubDate: Int): Novel = new Novel(this.novelName,newPubDate, this.author )
  //}
  //
  //class Counter(val value : Int) {
  //  def currentCount(): Int = value
  //  def increment(incAmount: Int): Counter = new Counter(this.currentCount + incAmount)
  //  def decrement(decAmount: Int): Counter = new Counter(this.currentCount - decAmount)
  //  def increment(): Counter = new Counter(this.currentCount + 1)
  //  def decrement(): Counter = new Counter(this.currentCount - 1)
  //}

  // SOLUTIONS
  class Writer(firstName: String, surname: String, val year: Int) {
    def fullName(): String = firstName + " " + surname
  }

  class Novel(name: String, year: Int, author: Writer) {
    def authorAge = year - author.year

    def isWrittenBy(author: Writer) = author == this.author

    def copy(newYear: Int): Novel = new Novel(name, newYear, author)
  }

  class Counter(val count: Int = 0) {
    def inc = {
      println("incrementing")
      new Counter(count + 1) // immutability
    }

    def dec = {
      println("decrementing")
      new Counter(count - 1)
    }

    def inc(n: Int): Counter = {
      if (n <= 0) this
      else inc.inc(n - 1)
    }

    def dec(n: Int): Counter = {
      if (n <= 0) this
      else dec.dec(n - 1)
    }

    def print = println(count)
  }
}


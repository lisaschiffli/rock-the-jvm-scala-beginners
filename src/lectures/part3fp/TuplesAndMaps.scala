package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  //  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  // can group at most 22 elements of different types
  println(aTuple._1)
  println(aTuple._2)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps = keys -> values
  val aMap: Map[String, Int] = Map()
  val phonebook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 123).withDefaultValue(-1)  // .withDefaultValue guards against NoSuchElem error
  // a -> b is sugar for (a, b)

  println(phonebook)
  // map ops
  println(phonebook.contains(("Jim")))
  println(phonebook("Mary"))  // crashes with NoSuchElementException error

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))

  // mapValues
  println(phonebook.mapValues(number => "0245-" + number))

  // conversions
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b-> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

//  var myNetwork = Map("Mary" -> Set(""), "John" -> Set(""), "Sam" -> Set(""), "Aisha" -> Set(""))
  val empty: Map[String, Set[String]] = Map()
  var myNetwork = add(add(empty, "Bob"), "Mary")
  println(myNetwork)
  myNetwork = add(myNetwork, "Arnold")
  println(myNetwork)
  println(myNetwork("Mary"))
//  myNetwork = myNetwork + ("Mary"-> Set("John", "Aisha"))
  myNetwork = friend(myNetwork, "Bob", "Mary")
  println(myNetwork)
  myNetwork = remove(myNetwork,"Bob")
  println(myNetwork)
//  myNetwork = unfriend(myNetwork, "Mary", "Bob")
//  println(myNetwork)

  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"),"Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet,"Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
//    network.filterKeys(k => network(k).isEmpty).size
//    network.filter(pair => pair._2.isEmpty).size
//   network.count(pair => pair._2.isEmpty)
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(myNetwork,"Mary", "Bob"))
}

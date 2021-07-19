package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null
//  val result = Some(unsafeMethod())  // WRONG

  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult =  betterUnsafeMethod() orElse betterBackupMethod()

  println(chainedResult)
  println(betterChainedResult)

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)  // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

 // for-comprehensions
  val config: Map[String, String] = Map(
    // fetched from elsewhere - ie a config file, might be null values
    "host"-> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

//  for (_ <- 1 to 10)
//    println(Connection(config("host"), config("port")))

//  val host = config.get("host") match {
//    case Some(i) => i
//    case None => "host not found"
//  }
//  val port = config.get("port") match {
//    case Some(i) => i
//    case None => "port not found"
//  }
//  println(host)
//  println(port)

  val host = config.get("host")
  val port = config.get("port")
//    println(host)
//    println(port)

  /* equivalent imperative code to functional code below
    if (h != null)
      if (p != null)
        return Connection.apply(h,p)
    return nil
   */

  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))

  /*
    if (c != null)
      return c.connect
    return null
   */

  val connectionStatus = connection.map(c => c.connect)

  /*
    if (connectionStatus == null) println(None) else print (Some(connectionStatus.get))
   */
//  println(connectionStatus)

  /*
    if (status != null)
      println(Status)
   */
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

}

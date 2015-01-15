package se.jaklec.core

import akka.actor.{Props, ActorSystem}
import se.jaklec.core.greet.GreetingService

trait Core {
  implicit def system: ActorSystem
}

trait BootedCore extends Core {
  import scala.concurrent.duration._

  implicit lazy val system = ActorSystem("service-scala-akka-spray")

  sys.addShutdownHook {
    system.shutdown()
    system.awaitTermination(20 seconds)
  }
}

trait CoreActors {
  this: Core =>

  val greetingService = system.actorOf(Props[GreetingService], "greeting-service")
}
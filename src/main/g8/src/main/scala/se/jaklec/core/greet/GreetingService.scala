package se.jaklec.core.greet

import akka.actor.Actor

class GreetingService extends Actor {

  override def receive: Receive = {
    case Greeting => sender ! Greeting("Hello, World!")
  }
}
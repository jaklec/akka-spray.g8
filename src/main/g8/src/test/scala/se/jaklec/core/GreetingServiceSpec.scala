package se.jaklec.core

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, ShouldMatchers, WordSpecLike}
import se.jaklec.core.greet.{Greeting, GreetingService}

import scala.concurrent.duration._


class GreetingServiceSpec extends TestKit(ActorSystem("greeting-service-spec"))
  with ImplicitSender with WordSpecLike with ShouldMatchers with BeforeAndAfterAll {

  "The Greeter Service" should {

    val service = system.actorOf(Props[GreetingService], "greeting-service")

    "respond to Greeting with Hello World" in {
      service ! Greeting
      expectMsg(10 millis, Greeting("Hello, World!"))
    }
  }


  override protected def afterAll(): Unit = TestKit.shutdownActorSystem(system)
}
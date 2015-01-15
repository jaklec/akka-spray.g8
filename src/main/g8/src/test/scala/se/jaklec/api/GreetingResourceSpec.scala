package se.jaklec.api

import akka.actor.ActorRef
import akka.testkit.TestActor.AutoPilot
import akka.testkit.{TestActor, TestKit, TestProbe}
import org.json4s.{DefaultFormats, Extraction}
import org.scalatest.{BeforeAndAfterAll, ShouldMatchers, WordSpecLike}
import se.jaklec.core.greet.Greeting
import spray.testkit.ScalatestRouteTest

class GreetingResourceSpec extends WordSpecLike with ShouldMatchers with ScalatestRouteTest with BeforeAndAfterAll {

  implicit val json4sJacksonFormats = DefaultFormats

  "The Greetings api" should {

    Extraction

    val probe = new TestProbe(system)
    probe.setAutoPilot(new TestActor.AutoPilot {
      override def run(sender: ActorRef, msg: Any): AutoPilot = msg match {
        case Greeting =>
          sender ! Greeting("foo")
          this
      }
    })

    "respond with greeting" in new GreetingResource(probe.ref) {

      Get("/greet") ~> route ~> check {
        val resp = responseAs[Greeting]
        resp.greeting should be("foo")
      }
    }
  }

  override protected def afterAll(): Unit = TestKit.shutdownActorSystem(system)
}

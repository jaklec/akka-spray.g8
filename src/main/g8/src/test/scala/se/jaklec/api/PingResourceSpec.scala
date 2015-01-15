package se.jaklec.api

import org.scalatest.{ShouldMatchers, WordSpecLike}
import spray.testkit.ScalatestRouteTest

class PingResourceSpec extends WordSpecLike with ShouldMatchers with ScalatestRouteTest {

  "The Ping api" should {

    "pong to ping" in new PingResource {

      Get("/ping") ~> route ~> check {
        val r = responseAs[String]
        r should be("pong")
      }
    }
  }
}
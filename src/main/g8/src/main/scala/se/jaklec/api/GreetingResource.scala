package se.jaklec.api

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import org.json4s.DefaultFormats
import se.jaklec.core.greet.Greeting
import spray.http.MediaTypes
import spray.httpx.Json4sJacksonSupport
import spray.routing.Directives

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


class GreetingResource(service: ActorRef)(implicit ec: ExecutionContext) extends Directives with Json4sJacksonSupport {

  implicit val json4sJacksonFormats = DefaultFormats
  implicit val timeout = Timeout(500 millis)

  val route = pathPrefix("greet") {
    pathEndOrSingleSlash {
      respondWithMediaType(MediaTypes.`application/json`) {
        complete { (service ? Greeting).mapTo[Greeting] }
      }
    }
  }
}
package se.jaklec.api

import spray.http.MediaTypes
import spray.routing.{Directives, Route}

class PingResource extends Directives {

  val route: Route = pathPrefix("ping") {
    pathEndOrSingleSlash {
      respondWithMediaType(MediaTypes.`text/plain`) {
        complete("pong")
      }
    }
  }
}
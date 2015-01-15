package se.jaklec.api


import akka.actor.{Actor, ActorRefFactory, ActorSystem, Props}
import akka.io.IO
import se.jaklec.core.{Core, CoreActors}
import spray.can.Http
import spray.routing.{HttpService, Route, RouteConcatenation}

trait Api extends RouteConcatenation {
  this: Core with CoreActors =>

  implicit val ec = system.dispatcher

  lazy val routes: Route = new PingResource().route ~
    new GreetingResource(greetingService).route

  lazy val rootHttpService = system.actorOf(Props(new RootHttpServiceActor(routes)), "http-service")

  IO(Http) ! Http.Bind(rootHttpService, interface = "localhost", port = 8080)
}

class RootHttpServiceActor(routes: Route)(implicit as: ActorSystem) extends Actor with HttpService {
  override def receive: Receive = runRoute(routes)

  override implicit def actorRefFactory: ActorRefFactory = context
}

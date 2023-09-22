package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object MyRoutes {
  val route: Route = path("hello") {
    get {
      complete("Hello, world!")
    }
  }
}


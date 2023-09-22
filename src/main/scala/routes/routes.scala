package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
object MyRoutes {
  val createInvestiment: Route = path("investiment") {
    post {
      complete("Criar investimento")
    }
  }

  val getInvestiments: Route = path("investiment") {
    get {
      complete("Ver meus investimentos")
    }
  }

  val myRoutes = createInvestiment ~ getInvestiments
}


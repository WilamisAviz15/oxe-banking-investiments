package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import controller.BankController

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

  val getBanks: Route = path("banks") {
    get {
      val banks = BankController.getBanks()
      complete(banks)
    }
  }

  val myRoutes = createInvestiment ~ getInvestiments ~ getBanks
}


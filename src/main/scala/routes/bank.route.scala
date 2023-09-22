package routes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes

import controller.InvestmentTypesController
import controller.BankController

object RouteBank {
  private val getBank: Route = path("banks") {
    get {
      val banks = BankController.getBanks()
      complete(StatusCodes.OK, banks) 
    }
  }

  val routesBanks: Route = getBank
}

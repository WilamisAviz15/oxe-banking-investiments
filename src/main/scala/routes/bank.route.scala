package routes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

import controller.InvestmentTypesController
import controller.BankController

object RouteBank {
  private val getBank: Route = path("banks") {
    get {
      val banks = BankController.getBanks()
      val jsonResponse = HttpEntity(ContentTypes.`application/json`, banks.getOrElse(""))
      complete(StatusCodes.OK, jsonResponse)
    }
  }

  val routesBanks: Route = getBank
}

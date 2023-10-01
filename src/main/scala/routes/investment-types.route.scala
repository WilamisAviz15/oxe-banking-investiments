package routes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import controller.InvestmentTypesController
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

object RouteInvestmentTypes {
  private val getInvestmentTypes: Route = path("investment" / "types") {
    get {
      val data = InvestmentTypesController.get()
      val jsonResponse = HttpEntity(ContentTypes.`application/json`, data.getOrElse(""))
      complete(StatusCodes.OK, jsonResponse)

    }
  }

  val routesInvestmentTypes = getInvestmentTypes
}
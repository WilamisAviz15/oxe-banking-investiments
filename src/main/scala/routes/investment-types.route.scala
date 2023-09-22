package routes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import controller.InvestmentTypesController

object RouteInvestmentTypes {
  private val getInvestmentTypes: Route = path("investment" / "types") {
    get {
      val data = InvestmentTypesController.get()
      complete(data)
    }
  }

  val routesInvestmentTypes = getInvestmentTypes
}
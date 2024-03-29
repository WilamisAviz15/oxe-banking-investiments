package routes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import spray.json._
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

import controller.InvestmentController
import model.Investment
import utils.DateJsonProtocol._
import akka.http.scaladsl.marshalling.ToEntityMarshaller
import akka.http.scaladsl.marshalling.Marshaller

trait MyJSON extends SprayJsonSupport with DefaultJsonProtocol with NullOptions {
  implicit val investmentJsonFormat: RootJsonFormat[Investment] = jsonFormat4((id, investmentAmount, period, investmentType) => Investment(id, investmentAmount, period, investmentType))
}

object RouteInvestments extends MyJSON {
  private val simulate: Route = path("investment"/"simulation") {
    post {
      entity(as[Investment]) { req =>
      val result = InvestmentController.getSimulate(req.investmentType, req.investmentAmount, req.period)
      val jsonResponse = HttpEntity(ContentTypes.`application/json`, s"$result")
      complete(StatusCodes.OK, jsonResponse)
      }
    }
  }

  private val create: Route = path("investment") {
    post {
      complete("Criar investimento")
    }
  }

  private val getById: Route = path("investment" / IntNumber) { userId => 
    get {
      val userInvestments = InvestmentController.getInvestmentById(userId)
      complete(userInvestments)
    }
  }

  val routeInvestments = simulate ~ create ~ getById
}
package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import spray.json._
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import controller.BankController
import controller.InvestmentController
import model.Investment
import utils.DateJsonProtocol._

trait MyJSON extends SprayJsonSupport with DefaultJsonProtocol with NullOptions {
  implicit val investmentJsonFormat: RootJsonFormat[Investment] = jsonFormat6((id, investmentAmount, startDate, dueDate, interestRate, investmentType) => Investment(id, investmentAmount, startDate, dueDate, interestRate, investmentType))
}

object MyRoutes extends MyJSON{
  val simulateInvestment: Route = path("investment"/"simulation") {
    post {
      entity(as[Investment]) { req =>
      val result = InvestmentController.simulate(req.investmentType, req.investmentAmount, req.startDate, req.dueDate)
      complete(StatusCodes.OK, s"Simulation result: $result")
      }
    }
  }

  val createInvestment: Route = path("investment") {
    post {
      complete("Criar investimento")
    }
  }

  val getInvestmentsById: Route = path("investment" / IntNumber) { userId => 
    get {
      val userInvestments = InvestmentController.getInvestmentById(userId)
      complete(userInvestments)
    }
  }

  val getBanks: Route = path("banks") {
    get {
      val banks = BankController.getBanks()
      complete(banks)
    }
  }

  val myRoutes = createInvestment ~ getInvestmentsById ~ getBanks ~ simulateInvestment
}


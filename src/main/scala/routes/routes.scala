package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import routes.RouteInvestments._
import routes.RouteInvestmentTypes._
import routes.RouteBank._

object MyRoutes {
  val myRoutes = routeInvestments ~ routesBanks ~ routesInvestmentTypes
}


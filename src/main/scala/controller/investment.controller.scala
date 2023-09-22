package controller

import java.sql.{Connection, ResultSet, Statement}
import database.DBConnection
import play.api.libs.json._
import java.sql.Date


object InvestmentController {
  def getInvestmentById(id: Int): Option[String] = {
    DBConnection.getConnection match {
      case Some(connection) =>
        try {
          println("Connection to the database established successfully.")
          val statement: Statement = connection.createStatement()
          val query = s"SELECT * FROM investments WHERE id = $id"
          val resultSet: ResultSet = statement.executeQuery(query)

          if (resultSet.next()) {
            val id = resultSet.getInt("id")
            val investmentAmount = resultSet.getString("investment_amount")
            val startDate = resultSet.getString("start_date")
            val dueDate = resultSet.getString("due_date")
            val interestRate = resultSet.getString("interest_rate")
            val investmentsType = resultSet.getString("investments_type")

            val investmentJson = Json.obj(
              "id" -> id,
              "investmentAmount" -> investmentAmount,
              "startDate" -> startDate,
              "dueDate" -> dueDate,
              "interestRate" -> interestRate,
              "investmentsType" -> investmentsType
            )

            Some(Json.prettyPrint(investmentJson))
          } else {
            println(s"Bank with ID $id not found.")
            None
          }
        } catch {
          case e: Exception =>
            println(s"An error occurred: ${e.getMessage}")
            None
        } finally {
          // Close the resources
          if (connection != null) {
            connection.close()
          }
        }

      case None =>
        println("Failed to establish a connection to the database.")
        None
    }
  }

  def switch(selectedInvestment: String): Double = selectedInvestment match {
    case "Tesouro Direto" => 1.10
    case _ => 0.0
  }

  def simulate(investment_type: Int, amount: Double, startAt: Date, finishAt: Date): Double = {
    var selectedInvestment = ""
    DBConnection.getConnection match {
      case Some(connection) =>
        try {
          println("Connection to the database established successfully.")
          val statement: Statement = connection.createStatement()
          val query = s"SELECT * FROM investments_types where id = $investment_type"
          val resultSet: ResultSet = statement.executeQuery(query)

          var investment = Json.arr()
          while (resultSet.next()) {
            selectedInvestment = resultSet.getString("name")
          }
        } catch {
          case e: Exception =>
            println(s"An error occurred: ${e.getMessage}")
            None
        } finally {
          if (connection != null) {
            connection.close()
          }
        }

      case None =>
        println("Failed to establish a connection to the database.")
        None
    }

    val value = switch(selectedInvestment) 

    return value * amount
  }
}

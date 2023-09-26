package controller

import java.sql.{Connection, ResultSet, Statement}
import database.DBConnection
import play.api.libs.json._


object BankController {
  def getBanks(): Option[String] = {
    DBConnection.getConnection match {
      case Some(connection) =>
        try {
          println("Connection to the database established successfully.")
          val statement: Statement = connection.createStatement()
          val query = "SELECT * FROM banks"
          val resultSet: ResultSet = statement.executeQuery(query)

          var banksArray = Json.arr()
          while (resultSet.next()) {
            val bankId = resultSet.getInt("id")
            val bankName = resultSet.getString("name")

            val bankJson = Json.obj(
              "id" -> bankId,
              "name" -> bankName
            )

            banksArray = banksArray :+ bankJson
          }
          
          Some(Json.prettyPrint(banksArray))

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
}

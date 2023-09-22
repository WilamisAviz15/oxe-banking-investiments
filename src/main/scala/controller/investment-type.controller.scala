
package controller

import java.sql.{Connection, ResultSet, Statement}
import database.DBConnection
import play.api.libs.json._


object InvestmentTypesController {
  def get(): Option[String] = {
    DBConnection.getConnection match {
      case Some(connection) =>
        try {
          println("Connection to the database established successfully.")
          val statement: Statement = connection.createStatement()
          val query = "SELECT * FROM investments_types"
          val resultSet: ResultSet = statement.executeQuery(query)

          var array = Json.arr()
          while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val name = resultSet.getString("name")

            val json = Json.obj(
              "id" -> id,
              "name" -> name
            )

            array = array :+ json
          }

          Some(Json.prettyPrint(array))

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

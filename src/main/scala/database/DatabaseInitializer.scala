package database

import java.sql.Statement

import DatabaseSeeder.seedInvestmentType
import DatabaseSeeder.seedBank
import DatabaseSeeder.seedAccountType
object DatabaseInitializer {
  def initialize(): Unit = {
    DBConnection.getConnection match {
      case Some(connection) =>
        println("Connection to the database established successfully.")
        DBSetup.initializeDatabase()
        seedInvestmentType.foreach { 
          data =>
            connection.createStatement().execute(s"INSERT INTO investments_types(name) VALUES ('${data.name}')")
        }
        seedBank.foreach { 
          data =>
          connection.createStatement().execute(s"INSERT INTO banks(name) VALUES ('${data.name}')")
        }
        seedAccountType.foreach { 
          data =>
          connection.createStatement().execute(s"INSERT INTO accounts_type(name) VALUES ('${data.name}')")
        }
        connection.close()
      case None =>
        println("Failed to establish a connection to the database.")
    }
  }
}

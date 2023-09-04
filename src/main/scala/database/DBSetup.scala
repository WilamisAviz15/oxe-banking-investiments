package database
import slick.jdbc.MySQLProfile.api._
import scala.util.{Success, Failure}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

import model.ClientsTable
import model.BanksTable

object DBSetup {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  val db: Database = Database.forConfig("mysql")
  val clients = TableQuery[ClientsTable]
  val banks = TableQuery[BanksTable]

  // Define your tables and queries here
  val createTables = DBIO.seq(
    clients.schema.create,
    banks.schema.create,
  )

  def initializeDatabase(): Unit = {
    db.run(createTables).onComplete {
      case Success(_) =>
        println("Database tables created successfully.")
      case Failure(error) =>
        println(s"Error creating database tables: ${error.getMessage}")
    }
  }
}

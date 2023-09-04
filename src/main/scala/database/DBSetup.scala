package database
import slick.jdbc.MySQLProfile.api._
import scala.util.{Success, Failure}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

import model.ClientsTable
import model.BanksTable
import model.BankClientsTable

object DBSetup {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  val db: Database = Database.forConfig("mysql")
  val clients = TableQuery[ClientsTable]
  val banks = TableQuery[BanksTable]
  val bankClients = TableQuery[BankClientsTable]

  // Define your tables and queries here
  val createTables = DBIO.seq(
    clients.schema.create,
    banks.schema.create,
    bankClients.schema.create,
  )

  def initializeDatabase(): Unit = {
    val createTablesFuture = db.run(createTables)
    try {
      val result = Await.result(createTablesFuture, Duration.Inf)
      result match {
        case _ =>
          println("Database tables created successfully.")
      }
    } catch {
      case ex: Throwable =>
        println(s"Error creating database tables: ${ex.getMessage}")
    }
  }
}

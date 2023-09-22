package database
import slick.jdbc.MySQLProfile.api._
import scala.util.{Success, Failure}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

import model.ClientsTable
import model.BanksTable
import model.BankClientsTable
import model.AccountsTable
import model.AccountsTypeTable
import model.InvestmentTable
import model.InvestmentsTypeTable
import model.TransactionsTable
import model.TransactionsTypeTable
import model.ClientInvestimentsTable

object DBSetup {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  val db: Database = Database.forConfig("mysql")
  val clients = TableQuery[ClientsTable]
  val banks = TableQuery[BanksTable]
  val bankClients = TableQuery[BankClientsTable]
  val accounts = TableQuery[AccountsTable]
  val accountsType = TableQuery[AccountsTypeTable]
  val investments = TableQuery[InvestmentTable]
  val investmentsType = TableQuery[InvestmentsTypeTable]
  val transactionsType = TableQuery[TransactionsTypeTable]
  val transactions = TableQuery[TransactionsTable]
  val clientInvestiments = TableQuery[ClientInvestimentsTable]

  // Define your tables and queries here
  val createTables = DBIO.seq(
    clients.schema.create,
    banks.schema.create,
    bankClients.schema.create,
    accountsType.schema.create,
    accounts.schema.create,
    investmentsType.schema.create,
    investments.schema.create,
    transactionsType.schema.create,
    transactions.schema.create,
    clientInvestiments.schema.create,
  )

  val dropTables = DBIO.seq(
    clientInvestiments.schema.dropIfExists,
    transactions.schema.dropIfExists,
    transactionsType.schema.dropIfExists,
    investments.schema.dropIfExists,
    investmentsType.schema.dropIfExists,
    accounts.schema.dropIfExists,
    accountsType.schema.dropIfExists,
    bankClients.schema.dropIfExists,
    banks.schema.dropIfExists,
    clients.schema.dropIfExists
  )

  def initializeDatabase(): Unit = {
    // Drop tables first (if they exist), then create them
    val dropAndCreateTables = dropTables andThen createTables
    val dropAndCreateTablesFuture = db.run(dropAndCreateTables)
    try {
      val result = Await.result(dropAndCreateTablesFuture, Duration.Inf)
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

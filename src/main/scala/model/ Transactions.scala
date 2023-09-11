package model
import slick.jdbc.MySQLProfile.api._

case class Transactions(id: Option[Int], hourDate: String, transactionsType: Int, description: String, value: Double, recipient: Int)

class TransactionsTable(tag: Tag) extends Table[Transactions](tag, "transactions") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def hourDate = column[String]("hour_Date")
  def transactionsType = column[Int]("transactions_type")
  def description = column[String]("description")
  def value = column[Double]("value")
  def recipient = column[Int]("recipient")

  
  def * = (id.?, hourDate, transactionsType, description, value, recipient) <> (Transactions.tupled, Transactions.unapply)

   def fkITransactionsType = foreignKey("fk_Transactions_type_id", transactionsType, TableQuery[TransactionsTypeTable])(_.id)
}


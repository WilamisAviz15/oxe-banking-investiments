package model
import slick.jdbc.MySQLProfile.api._

case class TransactionsType(id: Option[Int], deposit: Int, withdraw: Int, transfer: Int)

class TransactionsTypeTable(tag: Tag) extends Table[TransactionsType](tag, "transactions_type") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def deposit = column[Int]("deposit")
  def withdraw = column[Int]("withdraw")
  def transfer = column[Int]("transfer")
  
  def * = (id.?, deposit, withdraw, transfer) <> (TransactionsType.tupled, TransactionsType.unapply)
}


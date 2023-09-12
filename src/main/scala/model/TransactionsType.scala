package model
import slick.jdbc.MySQLProfile.api._

case class TransactionsType(id: Option[Int], name: String)

class TransactionsTypeTable(tag: Tag) extends Table[TransactionsType](tag, "transactions_type") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
 
  
  def * = (id.?, name) <> (TransactionsType.tupled, TransactionsType.unapply)
}


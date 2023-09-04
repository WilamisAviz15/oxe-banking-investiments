package model
import slick.jdbc.MySQLProfile.api._

case class Account(id: Option[Int], number: String, balance: String) //falta tipo_conta (diagrama)

class AccountsTable(tag: Tag) extends Table[Account](tag, "accounts") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def number = column[String]("number")
  def balance = column[String]("balance")
 
  
  def * = (id.?, number, balance) <> (Account.tupled, Account.unapply)
}


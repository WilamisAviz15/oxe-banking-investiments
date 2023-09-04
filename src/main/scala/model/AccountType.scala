package model
import slick.jdbc.MySQLProfile.api._

case class AccountType(id: Option[Int], name: String)

class AccountsTypeTable(tag: Tag) extends Table[AccountType](tag, "accounts_type") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  
  def * = (id.?, name) <> (AccountType.tupled, AccountType.unapply)
}


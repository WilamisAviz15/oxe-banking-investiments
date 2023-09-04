package model
import slick.jdbc.MySQLProfile.api._

case class Account(id: Option[Int], number: String, balance: String, accountType: Int)

class AccountsTable(tag: Tag) extends Table[Account](tag, "accounts") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def number = column[String]("number")
  def balance = column[String]("balance")
  def accountType = column[Int]("accounts_type")
 
  
  def * = (id.?, number, balance, accountType) <> (Account.tupled, Account.unapply)

  def fkAccountType = foreignKey("fk_Account_type_id", accountType, TableQuery[AccountsTypeTable])(_.id)
}


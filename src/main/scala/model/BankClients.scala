package model
import slick.jdbc.MySQLProfile.api._

import model.Client
import model.Bank

case class BankClient(id: Option[Int], clientId: Int, BankId: Int)

class BankClientsTable(tag: Tag) extends Table[BankClient](tag, "bank_clients") {
  def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def clientId = column[Int]("client_id")
  def bankId = column[Int]("bank_id")

  def * = (id, clientId, bankId) <> (BankClient.tupled, BankClient.unapply)


  // Chaves estrangeiras
  def fkClient = foreignKey("fk_client_id", clientId, TableQuery[ClientsTable])(_.id)
  def fkBank = foreignKey("fk_bank_id", bankId, TableQuery[BanksTable])(_.id)
}
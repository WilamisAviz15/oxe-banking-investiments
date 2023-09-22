package model
import slick.jdbc.MySQLProfile.api._

import model.Client
import model.Investment

case class ClientInvestiment(id: Option[Int], clientId: Int, investimentId: Int)

class ClientInvestimentsTable(tag: Tag) extends Table[ClientInvestiment](tag, "client_investiments") {
  def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def clientId = column[Int]("client_id")
  def investimentId = column[Int]("investiment_id")

  def * = (id, clientId, investimentId) <> (ClientInvestiment.tupled, ClientInvestiment.unapply)

  def fkClient = foreignKey("fk_client_id", clientId, TableQuery[ClientsTable])(_.id)
  def fkInvestiment = foreignKey("fk_investiment_id", investimentId, TableQuery[InvestmentTable])(_.id)
}
package model
import slick.jdbc.MySQLProfile.api._

case class Bank(id: Option[Int], name: String)

class BanksTable(tag: Tag) extends Table[Bank](tag, "banks") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  
  def * = (id.?, name) <> (Bank.tupled, Bank.unapply)
}

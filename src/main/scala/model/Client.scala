package model
import slick.jdbc.MySQLProfile.api._

// Define a case class that represents the structure of the "clients" table
case class Client(id: Option[Int], name: String, email: String, cpf: String, address: String, born: String)

// Define a Slick table representing the "clients" table
class ClientsTable(tag: Tag) extends Table[Client](tag, "clients") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def email = column[String]("email")
  def cpf = column[String]("cpf")
  def address = column[String]("address")
  def born = column[String]("born")

  // Map the columns to the case class
  def * = (id.?, name, email, cpf, address, born) <> (Client.tupled, Client.unapply)
}

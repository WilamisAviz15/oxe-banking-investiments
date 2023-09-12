package model
import slick.jdbc.MySQLProfile.api._

case class InvestmentType(id: Option[Int], name: String)

class InvestmentsTypeTable(tag: Tag) extends Table[InvestmentType](tag, "investments_types") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")


 
  
  def * = (id.?, name) <> (InvestmentType.tupled, InvestmentType.unapply)
 
}


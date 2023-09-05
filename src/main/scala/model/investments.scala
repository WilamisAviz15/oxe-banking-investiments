package model
import slick.jdbc.MySQLProfile.api._

case class Investment(id: Option[Int], investmentAmount: String, startDate: String, dueDate: String, interestRate: Int, investmentType: Int)

class InvestmentTable(tag: Tag) extends Table[Investment](tag, "investments") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def investmentAmount = column[String]("investment_amount")
  def startDate = column[String]("start_date") 
  def dueDate = column[String]("due_date")
  def interestRate = column[Int]("interest_rate")
  def investmentType = column[Int]("investments_type")
 
  
  def * = (id.?, investmentAmount, startDate, dueDate, interestRate, investmentType) <> (Investment.tupled, Investment.unapply)

   def fkInvestmentType = foreignKey("fk_Investment_type_id", investmentType, TableQuery[InvestmentsTypeTable])(_.id)
 
}

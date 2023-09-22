package model
import slick.jdbc.MySQLProfile.api._
import java.sql.Date


case class Investment(id: Option[Int], investmentAmount: Double, startDate: Date, dueDate: Date, interestRate: Double, investmentType: Int)

class InvestmentTable(tag: Tag) extends Table[Investment](tag, "investments") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def investmentAmount = column[Double]("investment_amount")
  def startDate = column[Date]("start_date") 
  def dueDate = column[Date]("due_date")
  def interestRate = column[Double]("interest_rate")
  def investmentType = column[Int]("investments_type")
 
  
  def * = (id.?, investmentAmount, startDate, dueDate, interestRate, investmentType) <> (Investment.tupled, Investment.unapply)

   def fkInvestmentType = foreignKey("fk_Investment_type_id", investmentType, TableQuery[InvestmentsTypeTable])(_.id)
 
}

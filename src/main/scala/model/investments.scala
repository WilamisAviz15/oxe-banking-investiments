package model
import slick.jdbc.MySQLProfile.api._

case class Investment(id: Option[Int], investmentAmount: String, startDate: String, dueDate: String, interestRate: Int)

class InvestmentTable(tag: Tag) extends Table[Investment](tag, "investments") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def accountType = column[Int]("accounts_type")
  //falta tipo de investimento.
  def investmentAmount = column[String]("investment_amount")
  def startDate = column[String]("start_date") 
  def dueDate = column[String]("due_date")
  def interestRate = column[Int]("interest_rate")
 
  
  def * = (id.?, investmentAmount, startDate, dueDate, interestRate) <> (Investment.tupled, Investment.unapply)

  
}

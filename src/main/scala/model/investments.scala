package model
import slick.jdbc.MySQLProfile.api._
import java.sql.Date
import play.api.libs.json._


case class Investment(id: Option[Int], investmentAmount: Double, period: Int, investmentType: Int){}

class InvestmentTable(tag: Tag) extends Table[Investment](tag, "investments") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def investmentAmount = column[Double]("investment_amount")
  def period = column[Int]("period")
  def investmentType = column[Int]("investments_type")
 
  
  def * = (id.?, investmentAmount, period, investmentType) <> (Investment.tupled, Investment.unapply)

   def fkInvestmentType = foreignKey("fk_Investment_type_id", investmentType, TableQuery[InvestmentsTypeTable])(_.id)
 
}

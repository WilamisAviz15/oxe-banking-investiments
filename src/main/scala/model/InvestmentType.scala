package model
import slick.jdbc.MySQLProfile.api._

case class InvestmentType(id: Option[Int], poupanca: String, tesouroDireto: String, cdb: String)

class InvestmentsTypeTable(tag: Tag) extends Table[InvestmentType](tag, "investments_types") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def poupanca = column[String]("poupanca")
  def tesouroDireto = column[String]("tesouro_direto")
  def cdb = column[String]("cdb") 

 
  
  def * = (id.?, poupanca, tesouroDireto, cdb) <> (InvestmentType.tupled, InvestmentType.unapply)
 
}


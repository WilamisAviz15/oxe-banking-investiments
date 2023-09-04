package database
import java.sql.{Connection, DriverManager}
import database.DBConfig

object DBConnection {
  //val url = DBConfig.dbUrl
  val url = "jdbc:mysql://localhost:3306/oxe_baking_investiments?serverTimezone=UTC"
  val user = "root"
  val password = "root" 

  def getConnection: Option[Connection] = {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver")
      val connection = DriverManager.getConnection(url, user, password)
      Some(connection) 
    } catch {
      case e: Exception =>
        e.printStackTrace()
        None 
    }
  }
}
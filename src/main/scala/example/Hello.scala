package example
import database.DBConnection
import database.DBSetup

object Main {
  def main(args: Array[String]): Unit = {
    DBConnection.getConnection match {
      case Some(connection) =>
        println("Conexão com o banco de dados estabelecida com sucesso.")
        // Por exemplo: connection.createStatement().execute("SELECT * FROM sua_tabela")
        DBSetup.initializeDatabase()
        // connection.close()
      case None =>
        println("Falha ao estabelecer conexão com o banco de dados.")
    }
  }
}
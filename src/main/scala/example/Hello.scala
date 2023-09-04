package example
import database.DBConnection
import database.DBSetup

object Main {
  def main(args: Array[String]): Unit = {
    // Verifica a conexão com o banco de dados

    DBConnection.getConnection match {
      case Some(connection) =>
        println("Conexão com o banco de dados estabelecida com sucesso.")
        // Aqui você pode executar consultas no banco de dados ou fazer outras operações relacionadas ao banco de dados.
        // Por exemplo: connection.createStatement().execute("SELECT * FROM sua_tabela")

        // Não se esqueça de fechar a conexão quando terminar de usá-la.
        DBSetup.initializeDatabase()
        connection.close()

      case None =>
        println("Falha ao estabelecer conexão com o banco de dados.")
    }
  }
}
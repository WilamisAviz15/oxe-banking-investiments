package database

object DatabaseInitializer {
  def initialize(): Unit = {
    DBConnection.getConnection match {
      case Some(connection) =>
        println("Connection to the database established successfully.")
        // For example: connection.createStatement().execute("SELECT * FROM your_table")
        DBSetup.initializeDatabase()
        // connection.close()
      case None =>
        println("Failed to establish a connection to the database.")
    }
  }
}

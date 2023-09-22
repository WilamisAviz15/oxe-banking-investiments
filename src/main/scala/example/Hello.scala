package example
import database.DBConnection
import database.DBSetup
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object MyServer extends App {
  DBConnection.getConnection match {
      case Some(connection) =>
        println("Conexão com o banco de dados estabelecida com sucesso.")
        // Por exemplo: connection.createStatement().execute("SELECT * FROM sua_tabela")
        DBSetup.initializeDatabase()
        // connection.close()
      case None =>
        println("Falha ao estabelecer conexão com o banco de dados..")
  }
  // Create an Actor System and Materializer
  implicit val system: ActorSystem = ActorSystem("my-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  // Your routes go here
  val route = path("hello") {
    get {
      complete("Hello, world!")
    }
  }

  // Bind and start your server
  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/")

  // Keep the server running
  scala.io.StdIn.readLine("Press Enter to stop the server...\n")
  
  // Gracefully shut down the server and Actor System when done
  bindingFuture
    .flatMap(_.unbind())
    .onComplete { _ =>
      system.terminate()
      println("Server terminated.")
    }
}

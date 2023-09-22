package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContextExecutor

object MyServer extends App {
  // Initialize the database
  database.DatabaseInitializer.initialize()

  // Create an Actor System and Materializer
  implicit val system: ActorSystem = ActorSystem("my-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  // Your routes go here
  val route = routes.MyRoutes.route

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

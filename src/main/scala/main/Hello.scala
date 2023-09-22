package main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Route
import scala.concurrent.ExecutionContextExecutor


object MyServer extends App {
  database.DatabaseInitializer.initialize()

  implicit val system: ActorSystem = ActorSystem("my-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val myRoutes: Route = routes.MyRoutes.myRoutes

  val bindingFuture = Http().bindAndHandle(myRoutes, "localhost", 8080)

  println(s"Server online at http://localhost:8080/")

  scala.io.StdIn.readLine("Press Enter to stop the server...\n")

  bindingFuture
    .flatMap(_.unbind())
    .onComplete { _ =>
      system.terminate()
      println("Server terminated.")
    }
}

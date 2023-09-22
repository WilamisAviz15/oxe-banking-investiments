import Dependencies._

ThisBuild / scalaVersion     := "2.13.11"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "oxe-banking-investiments",
    libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.26",
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "ch.qos.logback" % "logback-classic" % "1.2.6",
  "com.typesafe.akka" %% "akka-http" % "10.2.6",
  "com.typesafe.akka" %% "akka-stream" % "2.6.16",
  "com.typesafe.play" %% "play-json" % "2.9.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.7",
  "io.spray" %% "spray-json" % "1.3.6",
  "org.scalameta" %% "munit" % "0.7.29" % Test
)
)


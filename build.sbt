name := "pitaya-finch"
version := "0.0.1"

lazy val root = (project in file("."))

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.13.1",
  "com.github.finagle" %% "finch-circe" % "0.13.1",
  "io.circe" %% "circe-generic" % "0.7.0",
  "com.typesafe" % "config" % "1.3.1"
)

name := "pitaya-finch"
version := "0.1.0"

lazy val root = (project in file("."))

scalaVersion := "2.12.8"

val finchVersion = "0.26.0"
val circeVersion = "0.11.0"
val configVersion = "1.3.3"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % finchVersion,
  "com.github.finagle" %% "finchx-circe" % finchVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "com.typesafe" % "config" % configVersion
)

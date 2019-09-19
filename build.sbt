name := "pitaya-finch"
version := "0.1.0"

lazy val root = (project in file("."))

scalaVersion := "2.12.8"

val finchVersion = "0.26.0"
val circeVersion = "0.11.0"
val configVersion = "1.3.3"
val scalatestVersion = "3.0.5"
val twitterServerVersion = "19.2.0"
val logbackVersion = "1.2.3"

val openNlpVersion = "1.9.1"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % finchVersion,
  "com.github.finagle" %% "finchx-circe" % finchVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-generic-extras" % circeVersion,
  "com.typesafe" % "config" % configVersion,
  "com.twitter" %% "twitter-server" % twitterServerVersion,
  "com.twitter" %% "finagle-stats" % twitterServerVersion,
  "com.twitter" %% "twitter-server-logback-classic" % twitterServerVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "org.scalatest" %% "scalatest" % scalatestVersion % "test",

  "org.apache.opennlp" % "opennlp-tools" % openNlpVersion
)

mainClass in Compile := Some("org.juanitodread.pitayafinch.App")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-eNDXEHLO")

// Docker stuff
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin)

dockerBaseImage := "openjdk:jre-alpine"

dockerExposedPorts ++= Seq(8080, 9990)
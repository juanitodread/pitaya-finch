name := "pitaya-finch"
version := "1.2.1"

lazy val root = (project in file("."))

scalaVersion := "2.13.6"

val finchVersion = "0.32.1"
val circeVersion = "0.13.0"
val configVersion = "1.4.1"
val scalatestVersion = "3.2.9"
val twitterServerVersion = "20.9.0"
val logbackVersion = "1.2.6"

val openNlpVersion = "1.9.3"

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
  "org.scalactic" %% "scalactic" % scalatestVersion,
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

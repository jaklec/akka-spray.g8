name := "$name$"

version := "$version$"

scalaVersion := "$scalaVersion$"

scalacOptions ++= Seq("-deprecation", "-feature", "-language", "postfixOps", "-encoding", "utf-8")

libraryDependencies ++= {
  val akkaVersion = "$akkaVersion$"
  val sprayVersion = "$sprayVersion$"
  val json4sVersion = "$json4sVersion$"
  val scalatestVersion = "$scalatestVersion$"
  Seq(
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-testkit" % sprayVersion % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % scalatestVersion % "test",
    "org.json4s" %% "json4s-jackson" % json4sVersion exclude("org.scala-lang", "scala-compiler"),
    "org.json4s" %% "json4s-ext" % json4sVersion exclude("org.scala-lang", "scala-compiler")
  )
}


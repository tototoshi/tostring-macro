lazy val macro = project.in(file("."))
  .settings(
    organization := "com.github.tototoshi",
    name := """tostring""",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.2",
    libraryDependencies ++= Seq(
      "org.scalameta" %% "scalameta" % "1.8.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M8" cross CrossVersion.full)
)

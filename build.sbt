import sbtassembly.AssemblyPlugin.defaultUniversalScript

ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "3.1.0"
ThisBuild / organization := "res"
ThisBuild / scalacOptions := Seq(
  "-language:implicitConversions", // allow implicit conversions
  "-deprecation", // emit warning and location for usages of deprecated APIs
  "-explain", // explain errors in more detail
  "-explain-types", // explain type errors in more detail
  "-feature", // emit warning and location for usages of features that should be imported explicitly
  "-unchecked", // enable additional warnings where generated code depends on assumptions
)
ThisBuild / javacOptions ++= Seq(
  "-encoding",
  "UTF-8",
)

// automatic reload build.sbt
Global / onChangedBuildSource := ReloadOnSourceChanges

// assembly setting
ThisBuild / assemblyPrependShellScript :=
  Some(defaultUniversalScript(shebang = false))

// Akka
val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.8"

// project root
lazy val res = project
  .in(file("."))
  .settings(
    name := "res",

    // libraries
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1",
      "org.scalatest" %% "scalatest" % "3.2.11" % Test,
      "org.apache.commons" % "commons-text" % "1.9",
      "org.jsoup" % "jsoup" % "1.14.3",
      ("org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2")
        .cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion)
        .cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka" %% "akka-stream" % AkkaVersion)
        .cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion)
        .cross(CrossVersion.for3Use2_13),
      ("ch.megard" %% "akka-http-cors" % "1.1.2")
        .cross(CrossVersion.for3Use2_13), // cors
    ),

    // Copy all managed dependencies to <build-root>/lib_managed/ This is
    // essentially a project-local cache.  There is only one lib_managed/ in
    // the build root (not per-project).
    retrieveManaged := true,

    // set the main class for 'sbt run'
    Compile / mainClass := Some("res.Res"),
    Compile / unmanagedSourceDirectories += baseDirectory.value / "esmeta" / "src" / "main" / "scala",

    // test setting
    Test / testOptions += Tests
      .Argument("-fDG", baseDirectory.value + "/tests/detail"),
    Test / parallelExecution := true,

    // assembly setting
    assembly / test := {},
    assembly / assemblyOutputPath := file("bin/res"),
  )
  .dependsOn(root)

// esmeta
lazy val root = project
  .in(file("esmeta"))

// format all files
lazy val format = taskKey[Unit]("format all files")
format := {
  (Compile / scalafmtAll).value
  (Compile / scalafmtSbt).value
}

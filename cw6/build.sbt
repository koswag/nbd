ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "cw6",
    idePackagePrefix := Some("pl.edu.pja.s15165")
  )

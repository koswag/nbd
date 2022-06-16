ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.12"

lazy val root = (project in file("."))
  .settings(
    name := "cw6",
    idePackagePrefix := Some("pl.edu.pja.s15165.cw6")
  )

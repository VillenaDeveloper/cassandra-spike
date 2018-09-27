name := "cassandra-spike"

version := "0.1"

scalaVersion := "2.12.6"


libraryDependencies ++= Seq(
  "com.outworkers" %% "phantom-dsl" % "2.24.10",
  "com.outworkers" %% "phantom-streams" % "2.24.10",
  "com.outworkers" %% "util-testing" % "0.45.0",
  "com.github.pureconfig" %% "pureconfig" % "0.9.2"
  
)
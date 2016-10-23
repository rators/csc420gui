name := "GUI"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.3"
libraryDependencies += "com.miglayout" % "miglayout" % "3.7.4"

val akkaV = "2.4.11"
val sprayV = "1.3.4"

libraryDependencies ++= {
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "io.spray"            %%  "spray-json"    % "1.3.2",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.mongodb" %% "casbah" % "3.1.1",
    "org.slf4j" % "slf4j-simple" % "1.6.4"
  )
}
name := "GUI"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.3"
libraryDependencies += "com.miglayout" % "miglayout" % "3.7.4"

val akkaV = "2.4.11"
val sprayV = "1.3.x"

libraryDependencies ++= {
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test"
  )
}
name := "stuff-feed"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick" % "0.5.0.8",
  "mysql" % "mysql-connector-java" % "5.1.27",
  "com.github.tototoshi" %% "slick-joda-mapper" % "0.4.0",
  "org.ocpsoft.prettytime" % "prettytime" % "3.2.1.Final"
)

play.Project.playScalaSettings

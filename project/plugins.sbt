addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.9")
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.16")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.2")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")

addSbtPlugin("com.lihaoyi" % "scalatex-sbt-plugin" % "0.3.11")
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.6.3")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"

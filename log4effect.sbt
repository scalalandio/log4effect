import sbt._
import Settings._

lazy val root = project.root
  .setName("log4effect")
  .setDescription("Build of log4effect library")
  .configureRoot
  .aggregate(common)

lazy val common = project.from("core")
  .setName("log4effect")
  .setDescription("Logging with Cats Effect")
  .setInitialImport()
  .configureModule
  .configureTests()
  .settings(Compile / resourceGenerators += task[Seq[File]] {
    val file = (Compile / resourceManaged).value / "log4effect-version.conf"
    IO.write(file, s"version=${version.value}")
    Seq(file)
  })

addCommandAlias("fullTest", ";test;scalastyle")
addCommandAlias("fullCoverageTest", ";coverage;test;coverageReport;coverageAggregate;scalastyle")
addCommandAlias("relock", ";unlock;reload;update;lock")

import sbt._
import Settings._

lazy val root = project.root
  .setName("log4effect")
  .setDescription("Build of log4effect library")
  .configureRoot
  .noPublish
  .aggregate(core)

lazy val core = project.from("core")
  .setName("log4effect")
  .setDescription("Logging with Cats Effect")
  .setInitialImport()
  .configureModule
  .configureTests()
  .publish
  .settings(Compile / resourceGenerators += task[Seq[File]] {
    val file = (Compile / resourceManaged).value / "log4effect-version.conf"
    IO.write(file, s"version=${version.value}")
    Seq(file)
  })

lazy val readme = scalatex.ScalatexReadme(
  projectId = "readme",
  wd        = file(""),
  url       = "https://github.com/scalalandio/log4effect/tree/master",
  source    = "Readme"
)
  .configureModule
  .noPublish
  .enablePlugins(GhpagesPlugin)
  .settings(
    siteSourceDirectory := target.value / "scalatex",
    git.remoteRepo := "git@github.com:scalalandio/log4effect.git",
    Jekyll / makeSite / includeFilter := new FileFilter { def accept(p: File) = true }
  )

addCommandAlias("fullTest", ";test;scalastyle")
addCommandAlias("fullCoverageTest", ";coverage;test;coverageReport;coverageAggregate;scalastyle")

name := "addItemsToRSSXML"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies := {
  libraryDependencies.value ++ Seq(
    "org.scala-lang.modules" %% "scala-xml" % "1.1.1",
  )
}
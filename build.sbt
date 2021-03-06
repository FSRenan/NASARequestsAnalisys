name := "DesafioSparkSemantix"

version := "0.1"

scalaVersion := "2.12.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-scala" % "11.0"

val sparkVersion = "2.4.4"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion
)
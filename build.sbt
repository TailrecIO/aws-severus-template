name := "sbt-aws-fn-template"

version := "1.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  "com.amazonaws" % "aws-lambda-java-events" % "1.1.0"
)

assemblyJarName in assembly := {
  name.value + "-" + version.value + ".jar"
}

retrieveManaged := true

enablePlugins(AwsFnPlugin)

awsLambdaHandlers := Seq(
  "function1"                 -> "io.tailrec.example.Lambda::handleRequest1",
  "function2"                 -> "io.tailrec.example.Lambda::handleRequest2",
  "function3"                 -> "io.tailrec.example.Lambda::handleRequest3"
)

awsS3Bucket := Some("lambda-scala")

awsLambdaMemorySize := Some(192)

awsLambdaTimeout := Some(30)

fork in run := true
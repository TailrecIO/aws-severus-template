name := "aws-severus-template"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val awsSdkVersion = "1.11.26"
  val akkaVersion = "2.4.8"
  val akkaHttpVersion   = "3.0.0-RC1"
  Seq(
    "com.typesafe" % "config" % "1.3.1",
    "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
    "com.amazonaws" % "aws-lambda-java-events" % "1.1.0",
    "org.apache.commons" % "commons-lang3" % "3.5",
//    "com.amazonaws" % "aws-java-sdk" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-s3" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-lambda" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-dynamodb" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-sqs" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-ses" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-sns" % awsSdkVersion,

    "com.amazonaws" % "aws-java-sdk-rds" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-emr" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-ec2" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-cloudwatch" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-logs" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-events" % awsSdkVersion,
    "com.amazonaws" % "aws-java-sdk-kinesis" % awsSdkVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.4",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.8.5",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}

assemblyJarName in assembly := {
  name.value + "-" + version.value + ".jar"
}

retrieveManaged := true

enablePlugins(AwsSeverusPlugin)

severusConfig := "severus.conf"

fork in run := true
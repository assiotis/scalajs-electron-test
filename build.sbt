

name := "grpc-client"

version := "0.1"

scalaVersion := "2.11.11"

enablePlugins(ScalaJSPlugin)

mainClass in Compile := Some("grpc.client.App")

// turns your Scala.js project into an “application” rather than a “library”
scalaJSUseMainModuleInitializer := true
scalaJSUseMainModuleInitializer in Test := false


//artifactPath in (Compile, fastOptJS) :=
//  ((crossTarget in (Compile, fastOptJS)).value /
//    ((moduleName in (Compile, fastOptJS)).value + ".js"))
//
//artifactPath in (Compile, fullOptJS) := (artifactPath in (Compile, fastOptJS)).value

//packageScalaJSLauncher in Compile <<= Def.task {
//  (mainClass in Compile).value map { mainCl =>
//    val log = streams.value.log
//    val file: sbt.File = (artifactPath in (Compile, packageScalaJSLauncher)).value
//    val code = s"""'use strict';
//require("source-map-support").install();
//require('./${name.value}-jsdeps');
//require('./${name.value}');
//${mainCl}(__dirname, require).main();
//"""
//
//    log.info(s"Creating launcher ${file}")
//    IO.write(file, code, Charset.forName("UTF-8"))
//
//    // Attach the name of the main class used, (ab?)using the name key
//    Attributed(file)(AttributeMap.empty.put(name.key, mainCl))
//  } getOrElse {
//    sys.error("Cannot write launcher file, since there is no or multiple mainClasses")
//  }
//}

artifactPath in(Compile, fastOptJS) := baseDirectory.value / "app" / "index.js"
artifactPath in(Compile, packageJSDependencies) := baseDirectory.value / "app" / "jsdeps.js"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
resolvers += Resolver.sonatypeRepo("public")


libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalatags" % "0.6.2",
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "com.mscharley" %%% "scalajs-electron" % "0.1.2"
)



name := "grpc-client"

version := "0.1"

scalaVersion := "2.11.11"

enablePlugins(ScalaJSPlugin)

mainClass in Compile := Some("grpc.client.App")

// turns your Scala.js project into an “application” rather than a “library”
scalaJSUseMainModuleInitializer := true
scalaJSUseMainModuleInitializer in Test := false

// see https://gitter.im/scala-js/scala-js/archives/2015/04/25
scalaJSOutputWrapper := (
  """var addGlobalProps = function(obj) {
        obj.require = require;
        obj.__dirname = __dirname;
      }
      if((typeof __ScalaJSEnv === "object") && typeof __ScalaJSEnv.global === "object") {
        addGlobalProps(__ScalaJSEnv.global);
      } else if(typeof  global === "object") {
        addGlobalProps(global);
      } else if(typeof __ScalaJSEnv === "object") {
        __ScalaJSEnv.global = {};
        addGlobalProps(__ScalaJSEnv.global);
      } else {
        var __ScalaJSEnv = { global: {} };
        addGlobalProps(__ScalaJSEnv.global)
      }

  """.stripMargin,
  ""
)


artifactPath in(Compile, fastOptJS) := baseDirectory.value / "app" / "index.js"
artifactPath in(Compile, packageJSDependencies) := baseDirectory.value / "app" / "jsdeps.js"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
resolvers += Resolver.sonatypeRepo("public")

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalatags" % "0.6.2",
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "com.mscharley" %%% "scalajs-electron" % "0.1.2"
)

package grpc.client

import electron.raw.{Electron => RawElectron}
import electron.{BrowserWindow, ElectronApp}

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global, literal}
import scala.scalajs.js.annotation.JSExportTopLevel

class App(dirName: String, require: js.Function1[String, js.Any]) extends ElectronApp(require) {
  var mainWindow: Option[BrowserWindow] = None

}

@JSExportTopLevel("GrpcClient.App")
object App {


  def main(args: Array[String]): Unit = {
    println("Starting up Electron!!!")

    val dirName = global.__dirname
    val require = global.require

    val app = new App(dirName.toString, (mod: String) => require(mod))
    implicit val electron = app.electron

    app.electronApp onceReady { () =>
      app.mainWindow = Some(BrowserWindow(literal(width = 800, height = 600)))
      app.mainWindow foreach { window =>
        // and load the index.html of the app.
        window.loadURL("file://" + dirName + "/index.html")

        // Open the DevTools.
        window.webContents.openDevTools()

        // Emitted when the window is closed.
        // Dereference the window object, usually you would store windows
        // in an array if your app supports multi windows, this is the time
        // when you should delete the corresponding element.
        window.on("closed"){ () => app.mainWindow = None }
      }
    }
  }
}

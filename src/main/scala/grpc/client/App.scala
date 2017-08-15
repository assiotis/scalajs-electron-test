package grpc.client

import electron.raw.{Electron => RawElectron}
import electron.{BrowserWindow, Electron}

import scala.scalajs.js.Dynamic.{global, literal}
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("GrpcClient.App")
object App {
  var mainWindow: Option[BrowserWindow] = None


  def main(args: Array[String]): Unit = {
    println("Starting up Electron!")

    val dirName = global.__dirname
    val require = global.require

    val rawElectron = require("electron").asInstanceOf[RawElectron]
    val electron = new Electron(rawElectron)
    val electronApp = electron.app

    electronApp onceReady { () =>
      mainWindow = Some(BrowserWindow(literal(width = 800, height = 600)))
      mainWindow foreach { window =>
        // and load the index.html of the app.
        window.loadURL("file://" + dirName + "/index.html")

        // Open the DevTools.
        window.webContents.openDevTools()

        // Emitted when the window is closed.
        // Dereference the window object, usually you would store windows
        // in an array if your app supports multi windows, this is the time
        // when you should delete the corresponding element.
        window.on("closed"){ () => mainWindow = None }
      }
    }
  }
}

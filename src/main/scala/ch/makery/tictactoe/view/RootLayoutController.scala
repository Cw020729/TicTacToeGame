package ch.makery.tictactoe.view

import ch.makery.tictactoe.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController(){
  def handleClose(): Unit = {
    System.exit(0)
  }
  def handleAbout(): Unit = {
    MainApp.showAboutPage()
  }

}
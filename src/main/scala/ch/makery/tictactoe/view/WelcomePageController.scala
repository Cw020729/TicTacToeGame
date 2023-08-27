package ch.makery.tictactoe.view

import ch.makery.tictactoe.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class WelcomePageController(){
  def getStarted(): Unit = {
    MainApp.showPlayerPage()
  }
  def handleHistory(): Unit = {
    MainApp.showHistoryPage()
  }
  def handleHelp(): Unit = {
    MainApp.showInstructionPage()
  }
  def handleQuit(): Unit = {
    System.exit(0)
  }
}
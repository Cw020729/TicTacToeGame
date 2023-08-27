package ch.makery.tictactoe.view

import ch.makery.tictactoe.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class RulesController(){

  def backMainPage(): Unit = {
    MainApp.showWelcomePage()
  }
}
package ch.makery.tictactoe.view


import scalafx.stage.Stage
import scalafxml.core.macros.sfxml

@sfxml
class AboutController(){
  var dialogStage : Stage  = null
  var returnClicked : Boolean = false

  def backMainPage(): Unit = {
    returnClicked = true;
    dialogStage.close()
  }

}
package ch.makery.tictactoe.view


import ch.makery.tictactoe.MainApp
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType, TextField}
import scalafxml.core.macros.sfxml

@sfxml
class PlayerController(private val textField1: TextField,private val textField2: TextField){
  private val maxNameLength: Int = 10
  def getStart(): Unit = {
    if (textField1.text.value == "" || textField2.text.value == "" ) {
      showMissingName()
    }else if(textField1.text.value.length > maxNameLength || textField2.text.value.length > maxNameLength){
      showMaxLengthExceededAlert()
    }else{
      val player1Name = textField1.text.value
      val player2Name = textField2.text.value
      MainApp.addPlayerName(player1Name, player2Name)
      MainApp.showNewGamePage()
    }
  }

  def showMaxLengthExceededAlert(): Unit = {
      Platform.runLater {
        val alert = new Alert(AlertType.Information) {
          title = "Name Length Exceeded"
          headerText = s"Name should not exceed $maxNameLength characters"
          contentText = s"Please enter a name with $maxNameLength or fewer characters."
        }
        val okButton = new ButtonType("OK")
        alert.buttonTypes = Seq(okButton)
        val result = alert.showAndWait()
        result match {
          case Some(`okButton`) =>
          case _ =>
        }
      }
    }

  def back(): Unit = {
    MainApp.showWelcomePage()
  }

  def showMissingName(): Unit = {
    Platform.runLater {
      val alert = new Alert(AlertType.Information) {
        title = "Missing Field"
        headerText = "Name not Found"
        contentText = "Please Enter Your Name"

      }
      val okButton = new ButtonType("OK")

      alert.buttonTypes = Seq(okButton)

      val result = alert.showAndWait()
      result match {
        case Some(`okButton`) =>
        case _ =>

      }
    }
  }
}
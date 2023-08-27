package ch.makery.tictactoe.view

import ch.makery.tictactoe.MainApp
import scalafx.application.Platform
import scalafx.scene.control.ButtonType
import scalafx.scene.control.{Alert, Button}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml
import ch.makery.tictactoe.model.{History, Player}



@sfxml
class TicTacToeController(
                           private val button1: Button,
                           private val button2: Button,
                           private val button3: Button,
                           private val button4: Button,
                           private val button5: Button,
                           private val button6: Button,
                           private val button7: Button,
                           private val button8: Button,
                           private val button9: Button,
                           private var userText: Text,
                           private var nameText: Text,
                           private var scoreText: Text
                         ) {

  private var isPlayer1Turn: Boolean = true
  private var isPlayer1First : Boolean = true
  private val buttons = Array(button1, button2, button3, button4, button5, button6, button7, button8, button9)
  private val player1: Player= new Player(MainApp.player1Name)
  private val player2: Player = new Player(MainApp.player2Name)
  private val history: History = new History(player1, player2)
  updateUI()


  def onButton1Click(): Unit = {
    val buttonNumber: Int = 0
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton2Click(): Unit = {
    val buttonNumber: Int = 1
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton3Click(): Unit = {
    val buttonNumber: Int = 2
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton4Click(): Unit = {
    val buttonNumber: Int = 3
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton5Click(): Unit = {
    val buttonNumber: Int = 4
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton6Click(): Unit = {
    val buttonNumber: Int = 5
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton7Click(): Unit = {
    val buttonNumber: Int = 6
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def onButton8Click(): Unit = {
    val buttonNumber: Int = 7
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))

  }

  def onButton9Click(): Unit = {
    val buttonNumber: Int = 8
    updateButton(buttonNumber)
    checkWinner().foreach(winner => announceWinner(winner))
  }

  def checkWinner(): Option[String] = {
    val winPatterns = List(
      (0, 1, 2), // Rows
      (3, 4, 5),
      (6, 7, 8),
      (0, 3, 6), // Columns
      (1, 4, 7),
      (2, 5, 8),
      (0, 4, 8), // Diagonals
      (2, 4, 6)
    )

    for ((a, b, c) <- winPatterns) {
      if (buttons(a).getText == "X" && buttons(b).getText == "X" && buttons(c).getText == "X" ) {
        buttons.foreach{button =>
          button.disable = true
        }
        player1.result.winningTimes +=1
        return Some(s"Player ${player1.name} wins!")
      } else if (buttons(a).getText == "O" && buttons(b).getText == "O" && buttons(c).getText == "O") {
        buttons.foreach { button =>
          button.disable = true
        }
        player2.result.winningTimes +=1
        return Some(s"Player ${player2.name} wins!")

      }
    }
    if (buttons.forall(_.disable.value)) {
      return Some("It is draw!")
    }
    updateUI()
    None

  }

  def announceWinner(winner: String): Unit = {
    isPlayer1First = !isPlayer1First
    updateUI()
    checkWinning()
    userText.setText("Game End")
    Platform.runLater {
      val alert = new Alert(AlertType.Information) {
        title = "Game Over"
        headerText = winner
        contentText = "What would you like to do next?"

      }
      val replayButton = new ButtonType("Replay")
      val ReturnToMainPage =  new ButtonType("Return to Main Page")
      alert.buttonTypes = Seq(replayButton,ReturnToMainPage)


      val result = alert.showAndWait()
      result match {
        case Some(`replayButton`) => resetGame()
        case Some(`ReturnToMainPage`) => getStarted()
        case _ =>
      }
    }
  }

  def resetGame(): Unit = {
    buttons.foreach { button =>
      button.disable = false
      button.text = ""
      button.style = ""
    }
    if(isPlayer1First){
      isPlayer1Turn = true
    }else{
      isPlayer1Turn = false
    }
    updateUI()

  }

  def updateUI(): Unit = {
    checkWinning()
    if(isPlayer1Turn){
      userText.setText(s"It's ${player1.name} turn")
    }else{
      userText.setText(s"It's ${player2.name} turn")
    }
    nameText.setText(s"${player1.name} vs ${player2.name}")
    scoreText.setText(s"${player1.result.winningTimes} (${player1.result.result}) : ${player2.result.winningTimes} (${player2.result.result})")
  }

  def updateButton(buttonNumber: Int): Unit = {
    if (isPlayer1Turn) {
      buttons(buttonNumber).text = "X"
      isPlayer1Turn = !isPlayer1Turn
      buttons(buttonNumber).disable = true
      buttons(buttonNumber).style = "-fx-background-color: chartreuse;"
    } else {
      buttons(buttonNumber).text = "O"
      isPlayer1Turn = !isPlayer1Turn
      buttons(buttonNumber).disable = true
      buttons(buttonNumber).style = "-fx-background-color: crimson;"
    }
  }
  def checkWinning(): Unit= {
    if (player1.result.winningTimes > player2.result.winningTimes){
      player1.result.result = "Winning"
      player2.result.result = "Losing"
    } else if (player1.result.winningTimes < player2.result.winningTimes) {
      player1.result.result = "Losing"
      player2.result.result = "Winning"
    }else {
      player1.result.result = "Draw"
      player2.result.result = "Draw"
    }
  }

  def getStarted(): Unit = {
    MainApp.addHistory(history)
    MainApp.showWelcomePage()
  }

  def onSurrenderButtonClick(): Unit = {
    if (isPlayer1Turn) {
      val winner = s"Player ${player2.name} wins!"
      player2.result.winningTimes += 1
      announceWinner(winner)
    } else {
      val winner = s"Player ${player1.name} wins!"
      player1.result.winningTimes += 1
      announceWinner(winner)
    }
  }
}


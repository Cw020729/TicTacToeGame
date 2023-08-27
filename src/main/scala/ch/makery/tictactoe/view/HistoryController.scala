package ch.makery.tictactoe.view


import ch.makery.tictactoe.MainApp
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml

@sfxml
class HistoryController(private var historyText: Text){

  def showLatest5History(): Unit = {
    val histories = MainApp.histories
    val lastest5histories = histories.takeRight(5)
    val historyString = lastest5histories.zipWithIndex.map { case (history, index) =>
      s"${index + 1}) ${history.getHistory()}"
    }.mkString("\n")
    if (historyString == "") {
      historyText.setText("No history Found")
    } else {
      historyText.setText(historyString)
    }
  }

  def backMainPage(): Unit = {
    MainApp.showWelcomePage()
  }
  showLatest5History()
}
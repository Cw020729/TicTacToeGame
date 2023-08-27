package ch.makery.tictactoe

import ch.makery.tictactoe.model.{History}
import ch.makery.tictactoe.view.AboutController
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.stage.{Modality, Stage}

import scala.collection.mutable.ListBuffer

object MainApp extends JFXApp {
  var player1Name: String = null
  var player2Name: String = null
  var histories: ListBuffer[History] = ListBuffer()

  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  stage = new PrimaryStage {
    title = "Tic-Tac-Toe"
    scene = new Scene {
      root = roots
    }
  }

  def showWelcomePage() = {
    val resource = getClass.getResource("view/WelcomePage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  showWelcomePage()

  def showNewGamePage() = {
    val resource = getClass.getResource("view/Tictactoe.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  def showPlayerPage(): Unit = {
    val resource = getClass.getResource("view/Player.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showAboutPage(): Unit = {
    val resource = getClass.getResourceAsStream("view/About.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val control = loader.getController[AboutController#Controller]
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]

    val dialog = new Stage() {
      initModality(Modality.APPLICATION_MODAL)
      initOwner(stage)
      scene = new Scene {
        stylesheets += getClass.getResource("view/theme.css").toString
        root = roots2
      }
    }

    control.dialogStage = dialog
    dialog.showAndWait()
    control.returnClicked

  }

  def showHistoryPage(): Unit = {
    val resource = getClass.getResource("view/History.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showInstructionPage(): Unit = {
    val resource = getClass.getResource("view/Help.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def addHistory(history: History): Unit = {
    histories += history
  }

  def addPlayerName(player1Name: String, player2Name: String): Unit = {
    this.player1Name = player1Name
    this.player2Name = player2Name
  }

}




package ch.makery.tictactoe.model


class History(val player1: Player, val player2 : Player){

  def getHistory(): String = {
    s"${player1.name} (${player1.result.result}) ${player1.result.winningTimes} : ${player2.result.winningTimes} (${player2.result.result}) ${player2.name}\n"
  }

}
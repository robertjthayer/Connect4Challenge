package com.grasell

class Connect4GameRunner(val player1: Player, val player2: Player, val width: Int = 4, val height: Int = 4) {

    var board = initiateBoard(width, height)


    /**
     * Run a game by executing each players turn until a winner is found, a player forfeits by throwing an exception,
     * or the board fills up and draws.
     * Returns the player who won, or null for a draw.
     */
    fun run(): Player? {
        var player1Turn = true

        // Give each player a turn until the game ends by a win or draw
        while (true) {
            val currentPlayer = if (player1Turn) player1 else player2
            val waitingPlayer = if (player1Turn) player2 else player1

            // Execute the player's turn.
            // If they throw an exception, they forfeit.
            try {
                var playersChosenColumn: Int? = null
                currentPlayer.takeTurn(board) {
                   playersChosenColumn = it
                }

                if (playersChosenColumn != null) {
                    board = board.withMove(playersChosenColumn!!, currentPlayer)
                } else {
                    throw IllegalMoveException("${currentPlayer.name} didn't make a move!")
                }
            } catch (e: Exception) {
                // Forfeit!
                println("${currentPlayer.name} threw an exception: $e")
                return waitingPlayer
            }

            // Check for endgame conditions
            //TODO: check for a winner or tie
        }
    }

}
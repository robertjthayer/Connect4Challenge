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
        var numMovesMade = 0

        // Give each player a turn until the game ends by a win or draw
        while (true) {
            val currentPlayer = if (player1Turn) player1 else player2
            val waitingPlayer = if (player1Turn) player2 else player1
            var playersChosenColumn: Int? = null
            // Execute the player's turn.
            // If they throw an exception, they forfeit.
            try {
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

            numMovesMade++
            // Check for endgame conditions

            val newPieceYCoord = board.columns[playersChosenColumn!!].size


            // Check for 4 in a row downward
            if(newPieceYCoord >= 3) {
                var x = 0
                /* this loop checks the piece below the new piece which was just inserted. If the colors are different
                 * break out of the loop. If the colors match, check the next piece under. If the loop finishes without
                 * breaking then return the player who just placed their piece
                 */
                while (x < 4) {
                    x++
                }
            }

            // Check to see if the new piece created a 4 in a row horizontally or diagonally

            /* make an array of 6 integers to track how many consecutive same color pieces are in any direction from the
             * newly placed piece. consecutivePieces[0] may be the number of consecutive pieces in the up and to the
             * right diagonal direction and consecutivePieces[1] could be horizontally to the right, etc.
             * Make a variable representing the new piece's color
             *
             * create a for loop to fill out the array one direction at a time
             * set up a switch statement to assign directional modifiers ie: int colModifier = 1, int rowModifier = -1
             * would correspond to traveling down and to the right
             * create a loop which will run at most 3 times
             * if board[currentX + (colModifier * i)][currentY + (rowModifier * i)] is NOT a legal location
             * OR the piece at this location (if any) is NOT the right color
             * set the value for the consecutivePieces array of the current direction equal to i - 1 and break the loop
             * and continue on to the next direction
             * if the loop runs the full 3 times without breaking return currentplayer
             *
             * After the consecutivePieces array is filled out check to see if
             * consecutivePieces[0] + consecutivePieces[3] >= 3
             *  OR consecutivePieces[1] + consecutivePieces[4] >= 3
             *  OR consecutivePieces[2] + consecutivePieces[5] >= 3
             *  if any of these are true return the player who just placed their piece.
             */

            // If the board is full and nobody won on the last move then the result is a draw
            if(numMovesMade == width * height){
                return null
            }
        }

    }
}
package com.grasell

import kotlinx.collections.immutable.*

/**
 * This represents the state of a Connect 4 "board".
 * It is immutable, but will return a new game board with 1 move applied.  Call withMove().
 */
class Connect4Board(val width: Int, val height: Int, val columns: ImmutableList<ImmutableList<Piece?>>) {

    /**
     * Returns an independent copy of the this board, but with 1 move applied to it.
     * Throws IllegalMoveException if a column is full or if a nonexistent column is selected.
     */
    fun withMove(x: Int, player: Player): Connect4Board {
        if (x >= width) throw IllegalMoveException("Column $x doesn't exist.")
        val newColumns =  columns.asSequence().zip((0 until columns.size).asSequence())
                .map { (column, index) ->
                    if (index == x) {
                        val newPieceIndex = column.indexOf(null)
                        if (newPieceIndex == -1) throw IllegalMoveException("Column $x is full.")
                        column.set(newPieceIndex, Piece(player))
                    } else {
                        column
                    }
                }.toImmutableList()

        return Connect4Board(width, height, newColumns)
    }

    /**
     * This method allows easy retrieval of game piece: board[x][y]
     */
    operator fun get(x: Int) = columns[x]
}

/**
 * Creates an empty game board with the specified dimensions.
 */
fun initiateBoard(width: Int, height: Int): Connect4Board {
    val backingLists = (0 until width).asSequence()
            .map { (0 until height).asSequence().map { null as Piece? }.toImmutableList() }
            .toImmutableList()

    return Connect4Board(width, height, backingLists)
}

data class Piece(val owner: Player)

class IllegalMoveException(explanation: String) : Exception(explanation)


package com.grasell

import org.junit.Test

import org.junit.Assert.*

class Connect4BoardTest {

    val testPlayer = object : Player {
        override fun takeTurn(board: Connect4Board, turnCallback: (Int) -> Unit) = TODO("not neccessary for these tests") //To change body of created functions use File | Settings | File Templates.

        override val name = "testPlayer"

    }

    @Test
    fun initialiateBoard() {
        val board = initiateBoard(5, 10)

        assertEquals(5, board.columns.size)

        board.columns.forEach {
            assertEquals(10, it.size)
        }
    }

    @Test
    fun withMove() {
        val board = initiateBoard(5, 5)
                .withMove(0, testPlayer)
                .withMove(0, testPlayer)
                .withMove(1, testPlayer)

        assertEquals(testPlayer, board[0][0]?.owner)
        assertEquals(testPlayer, board[0][1]?.owner)
        assertEquals(testPlayer, board[1][0]?.owner)
    }

    @Test(expected = IllegalMoveException::class)
    fun withMove_NoSuchColumn() {
        val board = initiateBoard(5, 5)

        board.withMove(5, testPlayer)
    }

    @Test(expected = IllegalMoveException::class)
    fun withMove_FullColumn() {
        initiateBoard(5, 5)
                .withMove(0, testPlayer)
                .withMove(0, testPlayer)
                .withMove(0, testPlayer)
                .withMove(0, testPlayer)
                .withMove(0, testPlayer)
                .withMove(0, testPlayer)
    }

}
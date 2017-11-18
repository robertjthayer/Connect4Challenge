package com.grasell

interface Player {
    fun takeTurn(board: Connect4Board, turnCallback: (Int) -> Unit)
    val name: String
}
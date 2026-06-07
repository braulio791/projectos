package com.braulio791.checkers.model

import kotlin.math.abs

/**
 * Implementação de IA usando algoritmo Minimax com Alpha-Beta Pruning
 */
class AI(private val difficulty: Int = 4) {

    companion object {
        const val AI_PLAYER = GameLogic.BLACK_PIECE
        const val HUMAN_PLAYER = GameLogic.RED_PIECE
        const val MAX_DEPTH = 6
        const val WINNING_SCORE = 10000
        const val PIECE_VALUE = 100
        const val KING_VALUE = 150
        const val CENTER_BONUS = 10
    }

    fun getBestMove(gameLogic: GameLogic): Pair<Pair<Int, Int>, Pair<Int, Int>>? {
        val board = gameLogic.getBoard()
        var bestMove: Pair<Pair<Int, Int>, Pair<Int, Int>>? = null
        var bestScore = Int.MIN_VALUE

        for (row in 0 until GameLogic.BOARD_SIZE) {
            for (col in 0 until GameLogic.BOARD_SIZE) {
                val piece = board[row][col]
                if (piece == AI_PLAYER || piece == GameLogic.BLACK_KING) {
                    val moves = gameLogic.getValidMoves(row, col)
                    for (move in moves) {
                        val score = minimax(
                            gameLogic,
                            0,
                            Int.MIN_VALUE,
                            Int.MAX_VALUE,
                            false,
                            Pair(row, col),
                            move
                        )
                        if (score > bestScore) {
                            bestScore = score
                            bestMove = Pair(Pair(row, col), move)
                        }
                    }
                }
            }
        }

        return bestMove
    }

    private fun minimax(
        gameLogic: GameLogic,
        depth: Int,
        alpha: Int,
        beta: Int,
        isMaximizing: Boolean,
        from: Pair<Int, Int>,
        to: Pair<Int, Int>
    ): Int {
        if (depth >= MAX_DEPTH) {
            return evaluateBoard(gameLogic)
        }

        val boardCopy = gameLogic.getBoard().map { it.copyOf() }.toTypedArray()

        return if (isMaximizing) {
            var maxScore = Int.MIN_VALUE
            var newAlpha = alpha

            for (row in 0 until GameLogic.BOARD_SIZE) {
                for (col in 0 until GameLogic.BOARD_SIZE) {
                    val piece = boardCopy[row][col]
                    if (piece == AI_PLAYER || piece == GameLogic.BLACK_KING) {
                        val moves = getValidMovesFromBoard(boardCopy, row, col)
                        for (move in moves) {
                            val score = minimax(
                                gameLogic,
                                depth + 1,
                                newAlpha,
                                beta,
                                false,
                                Pair(row, col),
                                move
                            )
                            maxScore = maxOf(maxScore, score)
                            newAlpha = maxOf(newAlpha, score)
                            if (beta <= newAlpha) return maxScore
                        }
                    }
                }
            }
            maxScore
        } else {
            var minScore = Int.MAX_VALUE
            var newBeta = beta

            for (row in 0 until GameLogic.BOARD_SIZE) {
                for (col in 0 until GameLogic.BOARD_SIZE) {
                    val piece = boardCopy[row][col]
                    if (piece == HUMAN_PLAYER || piece == GameLogic.RED_KING) {
                        val moves = getValidMovesFromBoard(boardCopy, row, col)
                        for (move in moves) {
                            val score = minimax(
                                gameLogic,
                                depth + 1,
                                alpha,
                                newBeta,
                                true,
                                Pair(row, col),
                                move
                            )
                            minScore = minOf(minScore, score)
                            newBeta = minOf(newBeta, score)
                            if (beta <= newAlpha) return minScore
                        }
                    }
                }
            }
            minScore
        }
    }

    private fun evaluateBoard(gameLogic: GameLogic): Int {
        val board = gameLogic.getBoard()
        var score = 0

        for (row in 0 until GameLogic.BOARD_SIZE) {
            for (col in 0 until GameLogic.BOARD_SIZE) {
                when (board[row][col]) {
                    GameLogic.BLACK_PIECE -> {
                        score += PIECE_VALUE
                        score += getCenterBonus(row, col)
                    }
                    GameLogic.BLACK_KING -> {
                        score += KING_VALUE
                        score += getCenterBonus(row, col)
                    }
                    GameLogic.RED_PIECE -> {
                        score -= PIECE_VALUE
                        score -= getCenterBonus(row, col)
                    }
                    GameLogic.RED_KING -> {
                        score -= KING_VALUE
                        score -= getCenterBonus(row, col)
                    }
                }
            }
        }

        return score
    }

    private fun getCenterBonus(row: Int, col: Int): Int {
        val distFromCenter = abs(row - 3.5) + abs(col - 3.5)
        return (7 - distFromCenter).toInt() * CENTER_BONUS
    }

    private fun getValidMovesFromBoard(board: Array<IntArray>, row: Int, col: Int): List<Pair<Int, Int>> {
        val moves = mutableListOf<Pair<Int, Int>>()
        val piece = board[row][col]

        val isKing = piece == GameLogic.RED_KING || piece == GameLogic.BLACK_KING
        val directions = if (isKing) {
            listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1)
        } else if (piece == GameLogic.BLACK_PIECE) {
            listOf(-1 to -1, -1 to 1)
        } else {
            listOf(1 to -1, 1 to 1)
        }

        for ((dRow, dCol) in directions) {
            val newRow = row + dRow
            val newCol = col + dCol
            if (isValidPosition(newRow, newCol) && board[newRow][newCol] == GameLogic.EMPTY) {
                moves.add(Pair(newRow, newCol))
            }
        }

        for ((dRow, dCol) in directions) {
            val captureRow = row + 2 * dRow
            val captureCol = col + 2 * dCol
            val middleRow = row + dRow
            val middleCol = col + dCol

            if (isValidPosition(captureRow, captureCol) &&
                board[captureRow][captureCol] == GameLogic.EMPTY &&
                isOpponentPiece(board[middleRow][middleCol], piece)) {
                moves.add(Pair(captureRow, captureCol))
            }
        }

        return moves
    }

    private fun isOpponentPiece(piece: Int, myPiece: Int): Boolean {
        return (myPiece == AI_PLAYER || myPiece == GameLogic.BLACK_KING) &&
               (piece == HUMAN_PLAYER || piece == GameLogic.RED_KING)
    }

    private fun isValidPosition(row: Int, col: Int): Boolean {
        return row in 0 until GameLogic.BOARD_SIZE &&
               col in 0 until GameLogic.BOARD_SIZE &&
               (row + col) % 2 == 1
    }
}

package com.braulio791.checkers.model

/**
 * Lógica principal do jogo de dama
 * Responsável por validar movimentos e gerenciar o estado do jogo
 */
class GameLogic {
    
    companion object {
        const val BOARD_SIZE = 8
        const val EMPTY = 0
        const val RED_PIECE = 1
        const val RED_KING = 2
        const val BLACK_PIECE = 3
        const val BLACK_KING = 4
    }

    private val board = Array(BOARD_SIZE) { IntArray(BOARD_SIZE) }
    private var currentPlayer = RED_PIECE
    private var selectedPiece: Pair<Int, Int>? = null
    private var validMoves = mutableListOf<Pair<Int, Int>>()

    init {
        initializeBoard()
    }

    private fun initializeBoard() {
        // Limpar tabuleiro
        for (i in 0 until BOARD_SIZE) {
            for (j in 0 until BOARD_SIZE) {
                board[i][j] = EMPTY
            }
        }

        // Colocar peças vermelhas (topo)
        for (i in 0 until 3) {
            for (j in 0 until BOARD_SIZE) {
                if ((i + j) % 2 == 1) {
                    board[i][j] = RED_PIECE
                }
            }
        }

        // Colocar peças pretas (fundo)
        for (i in 5 until BOARD_SIZE) {
            for (j in 0 until BOARD_SIZE) {
                if ((i + j) % 2 == 1) {
                    board[i][j] = BLACK_PIECE
                }
            }
        }
    }

    fun getBoard(): Array<IntArray> = board

    fun getCurrentPlayer(): Int = currentPlayer

    fun selectPiece(row: Int, col: Int): Boolean {
        val piece = board[row][col]
        
        if (piece == EMPTY || !belongsToCurrentPlayer(piece)) {
            return false
        }

        selectedPiece = Pair(row, col)
        validMoves = getValidMoves(row, col).toMutableList()
        return true
    }

    fun getValidMoves(row: Int, col: Int): List<Pair<Int, Int>> {
        val moves = mutableListOf<Pair<Int, Int>>()
        val piece = board[row][col]

        if (piece == EMPTY) return moves

        val isKing = piece == RED_KING || piece == BLACK_KING
        val directions = if (isKing) {
            listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1)
        } else if (piece == RED_PIECE) {
            listOf(1 to -1, 1 to 1)
        } else {
            listOf(-1 to -1, -1 to 1)
        }

        // Movimentos simples
        for ((dRow, dCol) in directions) {
            val newRow = row + dRow
            val newCol = col + dCol
            if (isValidPosition(newRow, newCol) && board[newRow][newCol] == EMPTY) {
                moves.add(Pair(newRow, newCol))
            }
        }

        // Capturas
        for ((dRow, dCol) in directions) {
            val captureRow = row + 2 * dRow
            val captureCol = col + 2 * dCol
            val middleRow = row + dRow
            val middleCol = col + dCol

            if (isValidPosition(captureRow, captureCol) && 
                board[captureRow][captureCol] == EMPTY &&
                isOpponentPiece(board[middleRow][middleCol])) {
                moves.add(Pair(captureRow, captureCol))
            }
        }

        return moves
    }

    fun movePiece(toRow: Int, toCol: Int): Boolean {
        val from = selectedPiece ?: return false

        if (!validMoves.contains(Pair(toRow, toCol))) {
            return false
        }

        val piece = board[from.first][from.second]
        board[from.first][from.second] = EMPTY
        board[toRow][toCol] = piece

        // Verificar captura
        val rowDiff = toRow - from.first
        val colDiff = toCol - from.second
        
        if (kotlin.math.abs(rowDiff) == 2) {
            val captureRow = from.first + rowDiff / 2
            val captureCol = from.second + colDiff / 2
            board[captureRow][captureCol] = EMPTY
        }

        // Promover a dama
        if ((piece == RED_PIECE && toRow == BOARD_SIZE - 1) ||
            (piece == BLACK_PIECE && toRow == 0)) {
            board[toRow][toCol] = if (piece == RED_PIECE) RED_KING else BLACK_KING
        }

        switchPlayer()
        selectedPiece = null
        validMoves.clear()

        return true
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == RED_PIECE) BLACK_PIECE else RED_PIECE
    }

    private fun belongsToCurrentPlayer(piece: Int): Boolean {
        return (currentPlayer == RED_PIECE && (piece == RED_PIECE || piece == RED_KING)) ||
               (currentPlayer == BLACK_PIECE && (piece == BLACK_PIECE || piece == BLACK_KING))
    }

    private fun isOpponentPiece(piece: Int): Boolean {
        return (currentPlayer == RED_PIECE && (piece == BLACK_PIECE || piece == BLACK_KING)) ||
               (currentPlayer == BLACK_PIECE && (piece == RED_PIECE || piece == RED_KING))
    }

    private fun isValidPosition(row: Int, col: Int): Boolean {
        return row in 0 until BOARD_SIZE && col in 0 until BOARD_SIZE && (row + col) % 2 == 1
    }

    fun reset() {
        initializeBoard()
        currentPlayer = RED_PIECE
        selectedPiece = null
        validMoves.clear()
    }
}

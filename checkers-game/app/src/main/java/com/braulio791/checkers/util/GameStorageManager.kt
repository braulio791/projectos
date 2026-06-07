package com.braulio791.checkers.util

import android.content.Context
import android.content.SharedPreferences
import com.braulio791.checkers.model.GameState

/**
 * Gerenciador de persistência de dados do jogo
 */
class GameStorageManager(context: Context) {
    
    private val preferences: SharedPreferences = 
        context.getSharedPreferences("checkers_game", Context.MODE_PRIVATE)
    
    companion object {
        private const val BOARD_STATE = "board_state"
        private const val CURRENT_PLAYER = "current_player"
        private const val GAME_MODE = "game_mode"
        private const val RED_SCORE = "red_score"
        private const val BLACK_SCORE = "black_score"
        private const val TIMESTAMP = "timestamp"
        private const val SOUND_ENABLED = "sound_enabled"
        private const val DIFFICULTY = "difficulty"
    }

    fun saveGame(gameState: GameState) {
        preferences.edit().apply {
            putString(BOARD_STATE, serializeBoard(gameState.board))
            putInt(CURRENT_PLAYER, gameState.currentPlayer)
            putString(GAME_MODE, gameState.gameMode)
            putInt(RED_SCORE, gameState.redScore)
            putInt(BLACK_SCORE, gameState.blackScore)
            putLong(TIMESTAMP, System.currentTimeMillis())
            apply()
        }
    }

    fun loadGame(): GameState? {
        if (!hasGame()) return null

        val board = deserializeBoard(preferences.getString(BOARD_STATE, "") ?: "")
        return GameState(
            board = board,
            currentPlayer = preferences.getInt(CURRENT_PLAYER, 1),
            gameMode = preferences.getString(GAME_MODE, "ai") ?: "ai",
            redScore = preferences.getInt(RED_SCORE, 0),
            blackScore = preferences.getInt(BLACK_SCORE, 0)
        )
    }

    fun hasGame(): Boolean {
        return preferences.contains(BOARD_STATE)
    }

    fun deleteGame() {
        preferences.edit().apply {
            remove(BOARD_STATE)
            remove(CURRENT_PLAYER)
            remove(GAME_MODE)
            remove(RED_SCORE)
            remove(BLACK_SCORE)
            remove(TIMESTAMP)
            apply()
        }
    }

    fun setSoundEnabled(enabled: Boolean) {
        preferences.edit().putBoolean(SOUND_ENABLED, enabled).apply()
    }

    fun isSoundEnabled(): Boolean {
        return preferences.getBoolean(SOUND_ENABLED, true)
    }

    fun setDifficulty(difficulty: Int) {
        preferences.edit().putInt(DIFFICULTY, difficulty).apply()
    }

    fun getDifficulty(): Int {
        return preferences.getInt(DIFFICULTY, 4)
    }

    private fun serializeBoard(board: Array<IntArray>): String {
        return board.joinToString(";") { row ->
            row.joinToString(",")
        }
    }

    private fun deserializeBoard(data: String): Array<IntArray> {
        val board = Array(8) { IntArray(8) }
        if (data.isEmpty()) return board

        val rows = data.split(";")
        rows.forEachIndexed { rowIndex, row ->
            val cells = row.split(",")
            cells.forEachIndexed { colIndex, cell ->
                if (rowIndex < 8 && colIndex < 8) {
                    board[rowIndex][colIndex] = cell.toIntOrNull() ?: 0
                }
            }
        }
        return board
    }
}

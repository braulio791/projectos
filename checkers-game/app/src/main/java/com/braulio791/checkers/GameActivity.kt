package com.braulio791.checkers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braulio791.checkers.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityGameBinding
    private lateinit var gameBoard: GameBoard
    private lateinit var gameLogic: GameLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameMode = intent.getStringExtra("gameMode") ?: "ai"
        
        gameLogic = GameLogic()
        gameBoard = GameBoard(binding.gameBoardView, gameLogic)
        
        setupGame(gameMode)
    }

    private fun setupGame(gameMode: String) {
        gameBoard.initialize()
        
        if (gameMode == "ai") {
            // Inicializar IA
        }
    }
}

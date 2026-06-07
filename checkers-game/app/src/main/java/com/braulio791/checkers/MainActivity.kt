package com.braulio791.checkers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.braulio791.checkers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.btnPlayVsAI.setOnClickListener {
            startGame(gameMode = "ai")
        }

        binding.btnPlayLocal.setOnClickListener {
            startGame(gameMode = "local")
        }
    }

    private fun startGame(gameMode: String) {
        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra("gameMode", gameMode)
        }
        startActivity(intent)
    }
}

package com.braulio791.checkers.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.braulio791.checkers.model.GameLogic

class GameBoard(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    
    private lateinit var gameLogic: GameLogic
    private var cellSize = 0f
    private var boardPadding = 20f
    
    private val lightSquarePaint = Paint().apply {
        color = Color.parseColor("#F0D9B5")
    }
    
    private val darkSquarePaint = Paint().apply {
        color = Color.parseColor("#B58863")
    }
    
    private val redPiecePaint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }
    
    private val blackPiecePaint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
    }
    
    private val kingStrokePaint = Paint().apply {
        color = Color.YELLOW
        strokeWidth = 3f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }
    
    private val selectedPaint = Paint().apply {
        color = Color.parseColor("#7FFF00")
        alpha = 100
    }
    
    private val validMovePaint = Paint().apply {
        color = Color.BLUE
        alpha = 150
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        
        val size = minOf(measuredWidth, measuredHeight).toFloat()
        cellSize = (size - boardPadding * 2) / GameLogic.BOARD_SIZE
        setMeasuredDimension(size.toInt(), size.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        drawBoard(canvas)
        drawPieces(canvas)
        drawValidMoves(canvas)
    }

    private fun drawBoard(canvas: Canvas) {
        for (i in 0 until GameLogic.BOARD_SIZE) {
            for (j in 0 until GameLogic.BOARD_SIZE) {
                val x = boardPadding + j * cellSize
                val y = boardPadding + i * cellSize
                
                val paint = if ((i + j) % 2 == 0) lightSquarePaint else darkSquarePaint
                canvas.drawRect(x, y, x + cellSize, y + cellSize, paint)
            }
        }
    }

    private fun drawPieces(canvas: Canvas) {
        val board = gameLogic.getBoard()
        val radius = cellSize / 2 * 0.8f
        
        for (i in 0 until GameLogic.BOARD_SIZE) {
            for (j in 0 until GameLogic.BOARD_SIZE) {
                val piece = board[i][j]
                if (piece != GameLogic.EMPTY) {
                    val cx = boardPadding + j * cellSize + cellSize / 2
                    val cy = boardPadding + i * cellSize + cellSize / 2
                    
                    val paint = when (piece) {
                        GameLogic.RED_PIECE, GameLogic.RED_KING -> redPiecePaint
                        else -> blackPiecePaint
                    }
                    
                    canvas.drawCircle(cx, cy, radius, paint)
                    
                    // Desenhar coroa para damas
                    if (piece == GameLogic.RED_KING || piece == GameLogic.BLACK_KING) {
                        canvas.drawCircle(cx, cy, radius, kingStrokePaint)
                    }
                }
            }
        }
    }

    private fun drawValidMoves(canvas: Canvas) {
        // Implementar desenho de movimentos válidos
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val col = ((event.x - boardPadding) / cellSize).toInt()
            val row = ((event.y - boardPadding) / cellSize).toInt()
            
            if (row in 0 until GameLogic.BOARD_SIZE && col in 0 until GameLogic.BOARD_SIZE) {
                gameLogic.selectPiece(row, col)
                invalidate()
            }
        }
        return true
    }

    fun setGameLogic(logic: GameLogic) {
        gameLogic = logic
        invalidate()
    }
}

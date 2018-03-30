package com.ivantha.playtolearn.model

import com.ivantha.playtolearn.common.Session
import java.io.Serializable
import java.util.*

class Board : Serializable {
    private val currentX = 0
    private val currentY = 0

    var tileGrid = ArrayList<ArrayList<BoardTile>>()

    init {
        // Initialize board
        for (col in 0 until Session.COLUMN_COUNT) {
            val tileColumn = ArrayList<BoardTile>()
            for (row in 0 until Session.ROW_COUNT) {
                val boardTile = BoardTile()
                boardTile.row = row
                boardTile.column = col

                if (col == currentX && row == currentY) {
                    boardTile.boardTileState = BoardTile.BoardTileState.CURRENT
//                    boardTile.setQuestion(QuestionCache.getQuestionArrayList().get(1));
                }
                tileColumn.add(boardTile)
            }
            tileGrid.add(tileColumn)
        }
    }

    fun getBoardTile(row: Int, column: Int): BoardTile {
        return tileGrid[column][row]
    }
}

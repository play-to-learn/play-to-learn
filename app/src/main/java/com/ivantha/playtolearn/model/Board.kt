package com.ivantha.playtolearn.model

import java.util.*

class Board(var rowCount: Int, var colCount: Int) {

    var tileGrid = ArrayList<ArrayList<Tile>>()
    var currentX = 0
    var currentY = 0

    init {
        // Initialize board
        for (col in 0 until colCount) {
            val tileColumn = ArrayList<Tile>()
            for (row in 0 until rowCount) {
                val boardTile = Tile()
                boardTile.row = row
                boardTile.column = col
                tileColumn.add(boardTile)
            }
            tileGrid.add(tileColumn)
        }
    }
}

package com.ivantha.playtolearn.model

import com.ivantha.playtolearn.common.Session.COLUMN_COUNT
import com.ivantha.playtolearn.common.Session.ROW_COUNT

class Board {

    var tileGrid = ArrayList<ArrayList<Tile>>()
    var currentX = 0
    var currentY = 0

    constructor()

    init {
        for (col in 0 until COLUMN_COUNT) {
            val tileColumn = ArrayList<Tile>()
            for (row in 0 until ROW_COUNT) {
                val tile = Tile()
                tile.row = row
                tile.column = col

                var q = Question()
                q.description = "asdasd"
                q.title = "asdasd"
                q.answer = 4
                tile.question = q

                tileColumn.add(tile)
            }
            tileGrid.add(tileColumn)
        }

        // Set (0, 0) as the current tile
        tileGrid[0][0].boardTileState = Tile.BoardTileState.CURRENT
    }
}

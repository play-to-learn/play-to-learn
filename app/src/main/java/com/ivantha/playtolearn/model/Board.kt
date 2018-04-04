package com.ivantha.playtolearn.model

class Board {

    companion object {
        const val COLUMN_COUNT = 6
        const val ROW_COUNT = 10
    }

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

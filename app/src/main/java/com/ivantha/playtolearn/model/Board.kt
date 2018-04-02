package com.ivantha.playtolearn.model

class Board(rowCount: Int, colCount: Int) {

    var tileGrid = ArrayList<ArrayList<Tile>>()
    var tileList = ArrayList<Tile>()
    var currentX = 0
    var currentY = 0

    init {
        for (col in 0 until colCount) {
            val tileColumn = ArrayList<Tile>()
            for (row in 0 until rowCount) {
                val tile = Tile()
                tile.row = row
                tile.column = col

                tileColumn.add(tile)
            }
            tileGrid.add(tileColumn)
        }

        for(row in 0 until rowCount){
            for(col in 0 until colCount){
                tileList.add(tileGrid[col][row])
            }
        }

        // Set (0, 0) as the current tile
        tileGrid[0][0].boardTileState = Tile.BoardTileState.CURRENT
    }
}

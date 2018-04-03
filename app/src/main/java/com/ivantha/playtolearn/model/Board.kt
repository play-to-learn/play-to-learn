package com.ivantha.playtolearn.model

class Board {

    var rowCount = 10
    var colCount = 6
    var tileGrid = ArrayList<ArrayList<Tile>>()
    var tileList = ArrayList<Tile>()
    var currentX = 0
    var currentY = 0

    constructor()

    constructor(rowCount: Int, colCount: Int) {
        this.rowCount = rowCount
        this.colCount = colCount
    }

    init {
        for (col in 0 until colCount) {
            val tileColumn = ArrayList<Tile>()
            for (row in 0 until rowCount) {
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

        refreshTileList()

        // Set (0, 0) as the current tile
        tileGrid[0][0].boardTileState = Tile.BoardTileState.CURRENT
    }

    fun refreshTileList(){
        tileList.clear()

        for(row in 0 until rowCount){
            for(col in 0 until colCount){
                tileList.add(tileGrid[col][row])
            }
        }
    }
}

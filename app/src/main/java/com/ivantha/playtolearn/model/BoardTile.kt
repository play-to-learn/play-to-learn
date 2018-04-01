package com.ivantha.playtolearn.model

class BoardTile {
    var row: Int = 0
    var column: Int = 0
    var boardTileState: BoardTileState? = BoardTileState.NOT_VISITED
    var question: Question? = null

    enum class BoardTileState {
        NOT_VISITED,
        VISITED,
        CURRENT,
        CORRECT_ANSWER,
        WRONG_ANSWER
    }
}

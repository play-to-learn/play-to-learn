package com.ivantha.playtolearn.model

import com.ivantha.playtolearn.game.QuestionLogic

class Tile {
    var row: Int = 0
    var column: Int = 0
    var boardTileState: BoardTileState? = BoardTileState.NOT_VISITED
    var question: Question? = null

    constructor()

    fun generateQuestion() {
        question = QuestionLogic.getNextQuestion()
    }

    enum class BoardTileState {
        NOT_VISITED,
        VISITED,
        CURRENT,
        CORRECT_ANSWER,
        WRONG_ANSWER
    }
}

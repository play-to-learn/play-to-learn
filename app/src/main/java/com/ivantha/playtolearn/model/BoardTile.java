package com.ivantha.playtolearn.model;

import java.io.Serializable;

public class BoardTile implements Serializable{
    private int row;
    private int column;
    private BoardTileState boardTileState;
    private Question question;

    public BoardTile() {
        this.row = 0;
        this.column = 0;
        this.boardTileState = BoardTileState.NOT_VISITED;
        this.question = null;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public BoardTileState getBoardTileState() {
        return boardTileState;
    }

    public void setBoardTileState(BoardTileState boardTileState) {
        this.boardTileState = boardTileState;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

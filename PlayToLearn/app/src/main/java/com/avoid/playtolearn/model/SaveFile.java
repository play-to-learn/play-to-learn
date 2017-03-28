package com.avoid.playtolearn.model;

import android.content.Context;

public class SaveFile {
    private Board board = null;

    public SaveFile(Context context) {
        this.board = new Board(context);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

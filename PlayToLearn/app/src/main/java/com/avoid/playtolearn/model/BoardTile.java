package com.avoid.playtolearn.model;

import com.avoid.playtolearn.widget.BoardTileLayout;

import java.io.Serializable;

public class BoardTile implements Serializable{
    private BoardTileLayout boardTileLayout = null;

    public BoardTileLayout getBoardTileLayout() {
        return boardTileLayout;
    }

    public void setBoardTileLayout(BoardTileLayout boardTileLayout) {
        this.boardTileLayout = boardTileLayout;
    }
}

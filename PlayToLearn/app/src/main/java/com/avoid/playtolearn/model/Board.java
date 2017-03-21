package com.avoid.playtolearn.model;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<BoardTile>> tileColumns = new ArrayList<>();

    public Board() {
        for(int col = 0; col < 6; col++){
            ArrayList<BoardTile> tileColumn = new ArrayList<>();
            for(int row = 0; row < 10; row++){
                tileColumn.add(new BoardTile());
            }
        }
    }

    public ArrayList<ArrayList<BoardTile>> getTileColumns() {
        return tileColumns;
    }

    public void setTileColumns(ArrayList<ArrayList<BoardTile>> tileColumns) {
        this.tileColumns = tileColumns;
    }
}

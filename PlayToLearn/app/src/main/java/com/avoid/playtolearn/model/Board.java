package com.avoid.playtolearn.model;

import android.content.Context;

import com.avoid.playtolearn.common.Global;
import com.avoid.playtolearn.game.GameLogic;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{
    private int currentX = 0;
    private int currentY = 0;

    private ArrayList<ArrayList<BoardTile>> tileGrid = new ArrayList<>();

    public Board(Context context) {
        initializeBoard();
    }

    public void initializeBoard(){
        for(int col = 0; col < Global.COLUMN_COUNT; col++){
            ArrayList<BoardTile> tileColumn = new ArrayList<>();
            for(int row = 0; row < Global.ROW_COUNT; row++){
                BoardTile boardTile = new BoardTile();
                boardTile.setRow(row);
                boardTile.setColumn(col);

                if(col == currentX && row == currentY){
                    boardTile.setBoardTileState(BoardTileState.CURRENT);
                    boardTile.setQuestion(GameLogic.getRandomQuestion());
                }
                tileColumn.add(boardTile);
            }
            tileGrid.add(tileColumn);
        }
    }

    public ArrayList<ArrayList<BoardTile>> getTileGrid() {
        return tileGrid;
    }

    public void setTileGrid(ArrayList<ArrayList<BoardTile>> tileGrid) {
        this.tileGrid = tileGrid;
    }

    public BoardTile getBoardTile(int row, int column){
        return getTileGrid().get(column).get(row);
    }
}

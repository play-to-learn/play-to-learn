package com.avoid.playtolearn.model;

import android.content.Context;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.widget.BoardTileButton;
import com.avoid.playtolearn.widget.BoardTileLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{
    private ArrayList<ArrayList<BoardTile>> tileGrid = new ArrayList<>();

    public Board(Context context) {
        for(int col = 0; col < 10; col++){
            ArrayList<BoardTile> tileColumn = new ArrayList<>();
            for(int row = 0; row < 10; row++){
                BoardTile boardTile = new BoardTile();
                BoardTileLayout boardTileLayout = new BoardTileLayout(context);
                boardTile.setBoardTileLayout(boardTileLayout);

                if(col == 0 && row == 0){
                    BoardTileButton boardTileButton = new BoardTileButton(context);
                    boardTileButton.setIcon(R.drawable.pointer);
                    boardTileLayout.addView(boardTileButton);
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
}

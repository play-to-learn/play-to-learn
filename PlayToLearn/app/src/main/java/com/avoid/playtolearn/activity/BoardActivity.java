package com.avoid.playtolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.model.BoardTile;
import com.avoid.playtolearn.widget.BoardTileButton;
import com.avoid.playtolearn.widget.BoardTileLayout;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    private LinearLayout tileGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        tileGridLayout = (LinearLayout) findViewById(R.id.tile_grid_layout);

        createBoard();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Session.currentContext = BoardActivity.this;
    }

    @Override
    protected void onPause() {
        super.onPause();

        Session.saveFileHandler.saveGame();
    }

    private void createBoard() {
        for (ArrayList<BoardTile> tileArrayList : Session.currentSaveFile.getBoard().getTileGrid()) {
            LinearLayout tileColumnLayout = new LinearLayout(BoardActivity.this);
            tileColumnLayout.setOrientation(LinearLayout.VERTICAL);
            for (BoardTile boardTile : tileArrayList) {
                BoardTileLayout boardTileLayout = new BoardTileLayout(BoardActivity.this);
                boardTileLayout.setRow(boardTile.getRow());
                boardTileLayout.setColumn(boardTile.getColumn());

                if(boardTile.isHasPointer()){
                    boardTileLayout.addView(new BoardTileButton(BoardActivity.this));
                }
                tileColumnLayout.addView(boardTileLayout);
            }
            tileGridLayout.addView(tileColumnLayout);
        }
    }
}

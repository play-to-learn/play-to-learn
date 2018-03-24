package com.ivantha.playtolearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.model.BoardTile;
import com.ivantha.playtolearn.widget.BoardTileLayout;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    private LinearLayout tileGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        tileGridLayout = findViewById(R.id.tile_grid_layout);

        createBoard();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Session.saveHelper.saveGame();
    }

    private void createBoard() {
        for (ArrayList<BoardTile> tileArrayList : Session.currentSaveFile.getBoard().getTileGrid()) {
            ArrayList<BoardTileLayout> layoutArrayList = new ArrayList<>();
            LinearLayout tileColumnLayout = new LinearLayout(BoardActivity.this);
            tileColumnLayout.setOrientation(LinearLayout.VERTICAL);
            for (BoardTile boardTile : tileArrayList) {
                BoardTileLayout boardTileLayout = new BoardTileLayout(BoardActivity.this, boardTile);
                tileColumnLayout.addView(boardTileLayout);
                layoutArrayList.add(boardTileLayout);
            }
            tileGridLayout.addView(tileColumnLayout);
            Session.boardLayoutGrid.add(layoutArrayList);
        }
    }
}

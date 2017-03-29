package com.avoid.playtolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.global.Session;
import com.avoid.playtolearn.model.BoardTile;
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
        Log.d("main", ">>>>>>>>>");
        for (ArrayList<BoardTile> tileArrayList : Session.currentSaveFile.getBoard().getTileGrid()) {
            Log.d("sub", "------------------------");
            LinearLayout tileColumnLayout = new LinearLayout(BoardActivity.this);
            tileColumnLayout.setOrientation(LinearLayout.VERTICAL);
            for (BoardTile boardTile : tileArrayList) {
                Log.d("x", "");
                    tileColumnLayout.addView(boardTile.getBoardTileLayout());
            }
            tileGridLayout.addView(tileColumnLayout);
        }
    }
}

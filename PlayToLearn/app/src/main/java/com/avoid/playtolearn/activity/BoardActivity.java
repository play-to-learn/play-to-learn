package com.avoid.playtolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.global.Session;
import com.avoid.playtolearn.model.BoardTile;
import com.avoid.playtolearn.widget.BoardTileButton;
import com.avoid.playtolearn.widget.BoardTileLayout;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
    }

    private void createBoard() {
        for (ArrayList<BoardTile> tileColumn : Session.currentSaveFile.getBoard().getTileColumns()) {
            for (BoardTile boardTile : tileColumn) {
                BoardTileLayout boardTileLayout = new BoardTileLayout(BoardActivity.this);

                if (!boardTile.isEmpty()) {
                    BoardTileButton boardTileButton = new BoardTileButton(BoardActivity.this);
//                    try {
//                        itemButton.setIcon(tile.getItem().getImage());
//                    } catch (NonExistingObjectCallException e) {
//                        e.printStackTrace();
//                    }
                    boardTileLayout.addView(boardTileButton);
                }
//                col1LinearLayout.addView(tileLayout);
            }
        }
    }
}

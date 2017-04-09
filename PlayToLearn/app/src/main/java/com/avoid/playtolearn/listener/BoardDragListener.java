package com.avoid.playtolearn.listener;

import android.view.DragEvent;
import android.view.View;

import com.avoid.playtolearn.global.Session;
import com.avoid.playtolearn.widget.BoardTileButton;
import com.avoid.playtolearn.widget.BoardTileLayout;

import java.io.Serializable;

public class BoardDragListener implements View.OnDragListener, Serializable {
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                if (v instanceof BoardTileLayout) {
                    BoardTileLayout newBoardTileLayout = ((BoardTileLayout) v);

                    if (newBoardTileLayout.getChildCount() == 0) {
                        BoardTileButton boardTileButton = (BoardTileButton) event.getLocalState();

                        BoardTileLayout previousBoardTileLayout = (BoardTileLayout) boardTileButton.getParent();

                        int preRow = previousBoardTileLayout.getRow();
                        int preColumn = previousBoardTileLayout.getColumn();
                        Session.currentSaveFile.getBoard().getBoardTile(preRow, preColumn).setHasPointer(false);
                        previousBoardTileLayout.removeView(boardTileButton);

                        int newRow = newBoardTileLayout.getRow();
                        int newColumn = newBoardTileLayout.getColumn();
                        Session.currentSaveFile.getBoard().getBoardTile(newRow, newColumn).setHasPointer(true);
                        newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                    }
                }

                Session.saveFileHandler.saveGame();
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }

        return true;
    }
}

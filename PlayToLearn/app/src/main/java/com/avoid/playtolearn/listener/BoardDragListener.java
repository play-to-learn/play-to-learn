package com.avoid.playtolearn.listener;

import android.view.DragEvent;
import android.view.View;

import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.model.BoardTileState;
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
                        previousBoardTileLayout.setBoardTileState(BoardTileState.VISITED);
                        previousBoardTileLayout.removeView(boardTileButton);

                        newBoardTileLayout.setBoardTileState(BoardTileState.CURRENT);
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

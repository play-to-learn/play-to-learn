package com.avoid.playtolearn.listener;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

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
                    ViewGroup viewGroup = ((BoardTileLayout) v);

                    if (viewGroup.getChildCount() == 0) {
                        BoardTileButton boardTileButton = (BoardTileButton) event.getLocalState();

                        ViewGroup owner = (ViewGroup) boardTileButton.getParent();
                        owner.removeView(boardTileButton);

                        viewGroup.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
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

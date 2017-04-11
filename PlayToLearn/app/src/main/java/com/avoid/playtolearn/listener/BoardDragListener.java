package com.avoid.playtolearn.listener;

import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.game.BoardLogic;
import com.avoid.playtolearn.model.BoardTileState;
import com.avoid.playtolearn.model.Result;
import com.avoid.playtolearn.util.MyApp;
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
                    BoardTileButton boardTileButton = (BoardTileButton) event.getLocalState();

                    BoardTileLayout previousBoardTileLayout = (BoardTileLayout) boardTileButton.getParent();
                    BoardTileLayout newBoardTileLayout = ((BoardTileLayout) v);

                    Result result = BoardLogic.verifyMove(previousBoardTileLayout.getBoardTile(), newBoardTileLayout.getBoardTile());

                    switch (result) {
                        case CORRECT:
                            previousBoardTileLayout.setBoardTileState(BoardTileState.CORRECT_ANSWER);
                            previousBoardTileLayout.removeView(boardTileButton);
                            Session.currentSaveFile.getProfile().setScore(Session.currentSaveFile.getProfile().getScore()
                                    + previousBoardTileLayout.getScore());

                            newBoardTileLayout.setBoardTileState(BoardTileState.CURRENT);
                            newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                            newBoardTileLayout.generateQuestion();
                            break;
                        case WRONG:
                            previousBoardTileLayout.setBoardTileState(BoardTileState.WRONG_ANSWER);
                            previousBoardTileLayout.removeView(boardTileButton);
                            Session.currentSaveFile.getProfile().setScore(Session.currentSaveFile.getProfile().getScore()
                                    - previousBoardTileLayout.getScore());

                            newBoardTileLayout.setBoardTileState(BoardTileState.CURRENT);
                            newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                            newBoardTileLayout.generateQuestion();
                            break;
                        case INVALID:
                            Toast toast = Toast.makeText(MyApp.context, "Invalid move", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            break;
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

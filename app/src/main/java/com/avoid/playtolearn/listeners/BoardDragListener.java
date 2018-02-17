package com.avoid.playtolearn.listeners;

import android.view.DragEvent;
import android.view.View;

import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.game.BoardLogic;
import com.avoid.playtolearn.models.BoardTileState;
import com.avoid.playtolearn.models.Result;
import com.avoid.playtolearn.models.Tuple;
import com.avoid.playtolearn.widgets.BoardTileButton;
import com.avoid.playtolearn.widgets.BoardTileLayout;

import java.io.Serializable;
import java.util.ArrayList;

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

                    Tuple<Result, ArrayList<Tuple<Integer, Integer>>> ret =
                            BoardLogic.verifyMove(previousBoardTileLayout.getBoardTile(), newBoardTileLayout.getBoardTile());
                    Result result = ret.x;

                    switch (result) {
                        case CORRECT:
                            previousBoardTileLayout.setBoardTileState(BoardTileState.CORRECT_ANSWER);
                            previousBoardTileLayout.removeView(boardTileButton);
                            Session.currentSaveFile.getProfile().setScore(Session.currentSaveFile.getProfile().getScore()
                                    + previousBoardTileLayout.getScore());

                            newBoardTileLayout.setBoardTileState(BoardTileState.CURRENT);
                            newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                            newBoardTileLayout.generateQuestion();

                            for(Tuple<Integer, Integer> tile: ret.y){
                                Session.boardLayoutGrid.get(tile.x).get(tile.y).setBoardTileState(BoardTileState.VISITED);
                            }

                            break;
                        case WRONG:
                            previousBoardTileLayout.setBoardTileState(BoardTileState.WRONG_ANSWER);
                            previousBoardTileLayout.removeView(boardTileButton);
                            Session.currentSaveFile.getProfile().setScore(Session.currentSaveFile.getProfile().getScore()
                                    - previousBoardTileLayout.getScore());

                            newBoardTileLayout.setBoardTileState(BoardTileState.CURRENT);
                            newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                            newBoardTileLayout.generateQuestion();

                            for(Tuple<Integer, Integer> tile: ret.y){
                                Session.boardLayoutGrid.get(tile.x).get(tile.y).setBoardTileState(BoardTileState.VISITED);
                            }

                            break;
                        case INVALID:
                            // Invalid move
                            break;
                    }
                }

                Session.saveHelper.saveGame();
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }

        return true;
    }
}

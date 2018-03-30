package com.ivantha.playtolearn.listener;

import android.view.DragEvent;
import android.view.View;

import com.ivantha.playtolearn.activity.BoardActivity;
import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.model.BoardTile;
import com.ivantha.playtolearn.model.Question;
import com.ivantha.playtolearn.common.Tuple;

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
                if (v instanceof BoardActivity.BoardTileLayout) {
                    BoardActivity.BoardTileButton boardTileButton = (BoardActivity.BoardTileButton) event.getLocalState();

                    BoardActivity.BoardTileLayout previousBoardTileLayout = (BoardActivity.BoardTileLayout) boardTileButton.getParent();
                    BoardActivity.BoardTileLayout newBoardTileLayout = ((BoardActivity.BoardTileLayout) v);

                    Tuple<Question.Result, ArrayList<Tuple<Integer, Integer>>> ret =
                            BoardActivity.verifyMove(previousBoardTileLayout.getBoardTile(), newBoardTileLayout.getBoardTile());
                    Question.Result result = ret.x;

                    switch (result) {
                        case CORRECT:
                            previousBoardTileLayout.setBoardTileState(BoardTile.BoardTileState.CORRECT_ANSWER);
                            previousBoardTileLayout.removeView(boardTileButton);
                            Session.currentSaveFile.getProfile().setScore(Session.currentSaveFile.getProfile().getScore()
                                    + previousBoardTileLayout.getScore());

                            newBoardTileLayout.setBoardTileState(BoardTile.BoardTileState.CURRENT);
                            newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                            newBoardTileLayout.generateQuestion();

                            for(Tuple<Integer, Integer> tile: ret.y){
                                Session.boardLayoutGrid.get(tile.x).get(tile.y).setBoardTileState(BoardTile.BoardTileState.VISITED);
                            }

                            break;
                        case WRONG:
                            previousBoardTileLayout.setBoardTileState(BoardTile.BoardTileState.WRONG_ANSWER);
                            previousBoardTileLayout.removeView(boardTileButton);
                            Session.currentSaveFile.getProfile().setScore(Session.currentSaveFile.getProfile().getScore()
                                    - previousBoardTileLayout.getScore());

                            newBoardTileLayout.setBoardTileState(BoardTile.BoardTileState.CURRENT);
                            newBoardTileLayout.addView(boardTileButton, boardTileButton.getWidth(), boardTileButton.getHeight());
                            newBoardTileLayout.generateQuestion();

                            for(Tuple<Integer, Integer> tile: ret.y){
                                Session.boardLayoutGrid.get(tile.x).get(tile.y).setBoardTileState(BoardTile.BoardTileState.VISITED);
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

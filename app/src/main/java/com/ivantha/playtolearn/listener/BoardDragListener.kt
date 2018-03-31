package com.ivantha.playtolearn.listener

import android.view.DragEvent
import android.view.View
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.game.MovementLogic
import com.ivantha.playtolearn.model.BoardTile
import com.ivantha.playtolearn.model.Result
import com.ivantha.playtolearn.widget.BoardTileButton
import com.ivantha.playtolearn.widget.BoardTileLayout
import java.io.Serializable

class BoardDragListener : View.OnDragListener, Serializable {
    override fun onDrag(v: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
            }
            DragEvent.ACTION_DRAG_EXITED -> {
            }
            DragEvent.ACTION_DROP -> {
                if (v is BoardTileLayout) {
                    val boardTileButton = event.localState as BoardTileButton

                    val previousBoardTileLayout = boardTileButton.parent as BoardTileLayout

                    val ret = MovementLogic.verifyMove(previousBoardTileLayout.boardTile, v.boardTile)
                    val result = ret.x

                    when (result) {
                        Result.CORRECT -> {
                            previousBoardTileLayout.setBoardTileState(BoardTile.BoardTileState.CORRECT_ANSWER)
                            previousBoardTileLayout.removeView(boardTileButton)
                            Session.currentSaveFile!!.profile!!.score = Session.currentSaveFile!!.profile!!.score + previousBoardTileLayout.score

                            v.setBoardTileState(BoardTile.BoardTileState.CURRENT)
                            v.addView(boardTileButton, boardTileButton.width, boardTileButton.height)
                            v.generateQuestion()

                            for (tile in ret.y) {
                                Session.boardLayoutGrid[tile.x][tile.y].setBoardTileState(BoardTile.BoardTileState.VISITED)
                            }
                        }
                        Result.WRONG -> {
                            previousBoardTileLayout.setBoardTileState(BoardTile.BoardTileState.WRONG_ANSWER)
                            previousBoardTileLayout.removeView(boardTileButton)
                            Session.currentSaveFile!!.profile!!.score = Session.currentSaveFile!!.profile!!.score - previousBoardTileLayout.score

                            v.setBoardTileState(BoardTile.BoardTileState.CURRENT)
                            v.addView(boardTileButton, boardTileButton.width, boardTileButton.height)
                            v.generateQuestion()

                            for (tile in ret.y) {
                                Session.boardLayoutGrid[tile.x][tile.y].setBoardTileState(BoardTile.BoardTileState.VISITED)
                            }
                        }
                        Result.INVALID ->
                            println("Invalid move. Better check this out!")
                    }
                }

                Session.saveHelper!!.saveGame(v.context)
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
            }
        }

        return true
    }
}

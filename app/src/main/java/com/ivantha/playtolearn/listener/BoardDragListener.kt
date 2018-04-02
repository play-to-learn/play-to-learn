package com.ivantha.playtolearn.listener

import android.view.DragEvent
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.game.MovementLogic
import com.ivantha.playtolearn.model.Tile
import com.ivantha.playtolearn.model.Result
import com.ivantha.playtolearn.widget.BoardTileButton
import com.ivantha.playtolearn.widget.BoardTileLayout
import java.io.Serializable

class BoardDragListener : View.OnDragListener, Serializable {

    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

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

                    val ret = MovementLogic.verifyMove(previousBoardTileLayout.tile, v.tile)
                    val result = ret.x

                    when (result) {
                        Result.CORRECT -> {
                            previousBoardTileLayout.setBoardTileState(Tile.BoardTileState.CORRECT_ANSWER)
                            previousBoardTileLayout.removeView(boardTileButton)
                            Session.score = Session.score + previousBoardTileLayout.score

                            v.setBoardTileState(Tile.BoardTileState.CURRENT)
                            v.addView(boardTileButton, boardTileButton.width, boardTileButton.height)
                            v.generateQuestion()

                            for (tile in ret.y!!) {
                                Session.boardLayoutGrid[tile.x][tile.y!!].setBoardTileState(Tile.BoardTileState.VISITED)
                            }
                        }
                        Result.WRONG -> {
                            previousBoardTileLayout.setBoardTileState(Tile.BoardTileState.WRONG_ANSWER)
                            previousBoardTileLayout.removeView(boardTileButton)
                            Session.score = Session.score - previousBoardTileLayout.score

                            v.setBoardTileState(Tile.BoardTileState.CURRENT)
                            v.addView(boardTileButton, boardTileButton.width, boardTileButton.height)
                            v.generateQuestion()

                            for (tile in ret.y!!) {
                                Session.boardLayoutGrid[tile.x][tile.y!!].setBoardTileState(Tile.BoardTileState.VISITED)
                            }
                        }
                        Result.INVALID ->
                            println("Invalid move. Better check this out!")
                    }
                }

                FirebaseSaveHelper.saveGame(currentUser!!.uid)
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
            }
        }

        return true
    }
}

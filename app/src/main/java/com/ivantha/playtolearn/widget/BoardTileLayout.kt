package com.ivantha.playtolearn.widget

import android.content.Context
import android.content.res.Resources
import android.widget.FrameLayout
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.activity.BoardActivity
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.listener.BoardDragListener
import com.ivantha.playtolearn.listener.BoardTouchListener
import com.ivantha.playtolearn.model.BoardTile

class BoardTileLayout(context: Context, val boardTile: BoardTile) : FrameLayout(context) {

    val row: Int
        get() = this.boardTile.row

    val column: Int
        get() = this.boardTile.column

    //Should fix
    val score: Int
        get() = 1

    init {

        this.setOnTouchListener(BoardTouchListener(context))
        this.setOnDragListener(BoardDragListener())

        applyCustomDesign()

        if (boardTile.boardTileState === BoardTile.BoardTileState.CURRENT) {
            this.addView(BoardTileButton(context))
        }
    }

    fun setBoardTileState(boardTileState: BoardTile.BoardTileState) {
        this.boardTile.boardTileState = boardTileState
        this.refreshStateChanges()
    }

    fun generateQuestion() {
        println("Generate question>>>>>>>>>>>>>>>>>>")
    }

    private fun refreshStateChanges() {
        val boardTileState = boardTile.boardTileState
        when (boardTileState) {
            BoardTile.BoardTileState.NOT_VISITED -> setBackgroundResource(R.drawable.board_tile_background_not_visited0)
            BoardTile.BoardTileState.VISITED -> setBackgroundResource(R.drawable.board_tile_background_visited)
            BoardTile.BoardTileState.CURRENT -> setBackgroundResource(R.color.colorTransparent)
            BoardTile.BoardTileState.CORRECT_ANSWER -> setBackgroundResource(R.drawable.board_tile_background_correct)
            BoardTile.BoardTileState.WRONG_ANSWER -> setBackgroundResource(R.drawable.board_tile_background_wrong)
            else -> setBackgroundResource(R.drawable.board_tile_background_not_visited0)
        }
    }

    private fun applyCustomDesign() {
        val effectiveWidth = Resources.getSystem().displayMetrics.widthPixels
        val sizePx = effectiveWidth / (Session.COLUMN_COUNT + 1)
        val marginPx = (effectiveWidth - sizePx * Session.COLUMN_COUNT) / (Session.COLUMN_COUNT * 2)

        val params = FrameLayout.LayoutParams(sizePx, sizePx)
        params.setMargins(marginPx, marginPx, marginPx, marginPx)
        this.layoutParams = params

        refreshStateChanges()
    }
}

package com.ivantha.playtolearn.widget

import android.content.Context
import android.content.res.Resources
import android.widget.FrameLayout
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.listener.BoardDragListener
import com.ivantha.playtolearn.listener.BoardTouchListener
import com.ivantha.playtolearn.model.Tile

class BoardTileLayout(context: Context, val tile: Tile) : FrameLayout(context) {

    //Should fix
    val score: Int
        get() = 1

    init {

        this.setOnTouchListener(BoardTouchListener(context))
        this.setOnDragListener(BoardDragListener())

        applyCustomDesign()

        if (tile.boardTileState === Tile.BoardTileState.CURRENT) {
            this.addView(BoardTileButton(context))
        }
    }

    fun setBoardTileState(tileState: Tile.BoardTileState) {
        this.tile.boardTileState = tileState
        this.refreshStateChanges()
    }

    fun generateQuestion() {
        println("Generate question>>>>>>>>>>>>>>>>>>")
    }

    private fun refreshStateChanges() {
        val boardTileState = tile.boardTileState
        when (boardTileState) {
            Tile.BoardTileState.NOT_VISITED -> setBackgroundResource(R.drawable.board_tile_background_not_visited0)
            Tile.BoardTileState.VISITED -> setBackgroundResource(R.drawable.board_tile_background_visited)
            Tile.BoardTileState.CURRENT -> setBackgroundResource(R.color.colorTransparent)
            Tile.BoardTileState.CORRECT_ANSWER -> setBackgroundResource(R.drawable.board_tile_background_correct)
            Tile.BoardTileState.WRONG_ANSWER -> setBackgroundResource(R.drawable.board_tile_background_wrong)
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

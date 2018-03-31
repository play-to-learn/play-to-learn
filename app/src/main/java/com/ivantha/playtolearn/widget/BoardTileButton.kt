package com.ivantha.playtolearn.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageButton

import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.listener.BoardDragListener
import com.ivantha.playtolearn.listener.BoardTouchListener

class BoardTileButton(context: Context) : AppCompatImageButton(context) {

    init {
        this.setOnTouchListener(BoardTouchListener(context))
        this.setOnDragListener(BoardDragListener())
        applyCustomShape()
    }

    private fun applyCustomShape() {
        setBackgroundResource(R.drawable.sprite)
    }
}

package com.ivantha.playtolearn.listener

import android.content.Context
import android.view.MotionEvent
import android.view.View

import com.ivantha.playtolearn.widget.BoardTileButton
import com.ivantha.playtolearn.widget.BoardTileLayout
import com.ivantha.playtolearn.widget.ProblemDialog

import java.io.Serializable

class BoardTouchListener(private val context: Context) : View.OnTouchListener, Serializable {

    private var mDownX: Float = 0.toFloat()
    private var mDownY: Float = 0.toFloat()
    private val scrollThreshold = 10f
    private var isOnClick: Boolean = false

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x
                mDownY = event.y
                isOnClick = true
            }
            MotionEvent.ACTION_UP -> if (isOnClick) {
                val boardTileLayout: BoardTileLayout
                if (v is BoardTileLayout) {
                    boardTileLayout = v
                    if (boardTileLayout.tile.question != null) {
                        showQuestionDialog(context, boardTileLayout.tile.question!!.title,
                                boardTileLayout.tile.question!!.description)
                    }
                } else if (v is BoardTileButton) {
                    boardTileLayout = v.getParent() as BoardTileLayout
                    showQuestionDialog(context, boardTileLayout.tile.question!!.title,
                            boardTileLayout.tile.question!!.description)
                }
            }
            MotionEvent.ACTION_MOVE -> if (v is BoardTileButton) {
                if (isOnClick && (Math.abs(mDownX - event.x) > scrollThreshold || Math.abs(mDownY - event.y) > scrollThreshold)) {
                    val shadowBuilder = View.DragShadowBuilder(v)
                    v.startDrag(null, shadowBuilder, v, 0)
                    isOnClick = false
                }
            }
        }

        return true
    }

    companion object {
        fun showQuestionDialog(context: Context, title: String?, description: String?) {
            val problemDialog = ProblemDialog(context)
            problemDialog.setTitle(title!!)
            problemDialog.setDescription(description!!)
            problemDialog.show()
        }
    }
}

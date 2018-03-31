package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.widget.BoardTileLayout
import kotlinx.android.synthetic.main.activity_board.*
import java.util.*

class BoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        createBoard()
    }

    override fun onPause() {
        super.onPause()

        Session.saveHelper!!.saveGame(applicationContext)
    }

    private fun createBoard() {
        for (tileArrayList in Session.currentSaveFile!!.board!!.tileGrid) {
            val layoutArrayList = ArrayList<BoardTileLayout>()
            val tileColumnLayout = LinearLayout(this@BoardActivity)
            tileColumnLayout.orientation = LinearLayout.VERTICAL
            for (boardTile in tileArrayList) {
                val boardTileLayout = BoardTileLayout(this@BoardActivity, boardTile)
                tileColumnLayout.addView(boardTileLayout)
                layoutArrayList.add(boardTileLayout)
            }
            tileGridLayout.addView(tileColumnLayout)
            Session.boardLayoutGrid.add(layoutArrayList)
        }
    }
}

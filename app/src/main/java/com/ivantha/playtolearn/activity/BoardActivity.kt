package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.widget.BoardTileLayout
import kotlinx.android.synthetic.main.activity_board.*
import java.util.*

class BoardActivity : AppCompatActivity() {

    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        createBoard()
    }

    override fun onPause() {
        super.onPause()

        FirebaseSaveHelper.saveGame(currentUser!!.uid)
    }

    private fun createBoard() {
        for (tileArrayList in Session.board!!.tileGrid) {
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

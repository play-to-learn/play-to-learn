package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.adapter.TileRecyclerAdapter
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Board
import com.ivantha.playtolearn.widget.ProblemDialog
import kotlinx.android.synthetic.main.activity_board.*

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
        Session.currentBoard = Board(Session.ROW_COUNT, Session.COLUMN_COUNT)

        var gridLayoutManager = GridLayoutManager(this, Session.COLUMN_COUNT, LinearLayoutManager.VERTICAL, false)

        tileRecyclerView.layoutManager = gridLayoutManager
        tileRecyclerView.setHasFixedSize(true)

        var tileRecyclerAdapter = TileRecyclerAdapter(Session.currentBoard!!, this::showQuestionDialog, this::updateGoldStatus)
        tileRecyclerView.adapter = tileRecyclerAdapter
    }

    private fun updateGoldStatus(){
        goldStatusTextView.text = Session.currentLevel!!.score.toString()
    }

    private fun showQuestionDialog(title: String?, description: String?) {
        val problemDialog = ProblemDialog(this@BoardActivity)
        problemDialog.setTitle(title!!)
        problemDialog.setDescription(description!!)
        problemDialog.show()
    }
}

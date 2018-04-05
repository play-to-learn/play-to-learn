package com.ivantha.playtolearn.activity

import android.content.Intent
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
import com.ivantha.playtolearn.model.Board.Companion.COLUMN_COUNT
import com.ivantha.playtolearn.widget.ProblemDialog
import kotlinx.android.synthetic.main.activity_board.*

class BoardActivity : AppCompatActivity() {

    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        var gridLayoutManager = GridLayoutManager(this, COLUMN_COUNT, LinearLayoutManager.VERTICAL, false)

        tileRecyclerView.layoutManager = gridLayoutManager
        tileRecyclerView.setHasFixedSize(true)

        var tileRecyclerAdapter = TileRecyclerAdapter(Session.saveFile!!.currentBoard, this::showQuestionDialog, this::updateGoldStatus)
        tileRecyclerView.adapter = tileRecyclerAdapter

        boardBackButton.setOnClickListener({
        })

        boardMenuButton.setOnClickListener({
            startActivity(Intent(this@BoardActivity, LevelsActivity::class.java))
        })

        boardRestartButton.setOnClickListener({
            FirebaseSaveHelper.newLevel(currentUser!!.uid, Session.saveFile!!.currentLevel.id, {
                startActivity(Intent(this@BoardActivity, BoardActivity::class.java))
            })
        })

        boardSettingsButton.setOnClickListener({
            startActivity(Intent(this@BoardActivity, SettingsActivity::class.java))
        })
    }

    override fun onPause() {
        super.onPause()

        FirebaseSaveHelper.saveCurrentLevel(currentUser!!.uid)
    }

    private fun updateGoldStatus(){
        goldStatusTextView.text = Session.saveFile!!.currentLevel.score.toString()
    }

    private fun showQuestionDialog(title: String?, description: String?) {
        val problemDialog = ProblemDialog(this@BoardActivity)
        problemDialog.setTitle(title!!)
        problemDialog.setDescription(description!!)
        problemDialog.show()
    }
}

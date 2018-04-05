package com.ivantha.playtolearn.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
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

    private val customHandler = Handler()

    private var startTime = 0L
    private var timeInMilliseconds = 0L
    private var timeSwapBuff = 0L
    private var updatedTime = 0L

    private val updateTimerThread = object : Runnable {

        override fun run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime

            updatedTime = timeSwapBuff + timeInMilliseconds

            var secs = (updatedTime / 1000).toInt()
            var mins = secs / 60
            secs %= 60
            var hrs = mins / 60
            mins %= 60

            timeStatusTextView!!.text = String.format("%02d:%02d:%02d", hrs, mins, secs)
            customHandler.postDelayed(this, 1000)
        }

    }

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

        // Start timer
        Session.saveFile!!.currentLevel.startTime = SystemClock.uptimeMillis()
        startTime = Session.saveFile!!.currentLevel.startTime
        customHandler.postDelayed(updateTimerThread, 1000)
    }

    override fun onPause() {
        super.onPause()

//        timeSwapBuff += timeInMilliseconds
//        customHandler.removeCallbacks(updateTimerThread)
//
//        Session.saveFile!!.currentLevel.elapsedTime += timeSwapBuff
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

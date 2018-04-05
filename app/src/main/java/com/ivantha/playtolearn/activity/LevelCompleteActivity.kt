package com.ivantha.playtolearn.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import kotlinx.android.synthetic.main.activity_level_complete.*

class LevelCompleteActivity : AppCompatActivity() {

    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    init {
        FirebaseSaveHelper.saveCurrentLevel(currentUser!!.uid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_complete)

        var level = intent.getIntExtra("level", 0)
        var score = intent.getIntExtra("score", 0)
        var time = intent.getStringExtra("time")

        winLevelTextView.text = "Level $level"
        winScoreTextView.text = score.toString()
        winTimeTextView.text = time

        winMenuButton.setOnClickListener({
            startActivity(Intent(this@LevelCompleteActivity, LevelsActivity::class.java))
        })

        winPlayButton.setOnClickListener({
            FirebaseSaveHelper.nextLevel(currentUser!!.uid, {
                startActivity(Intent(this@LevelCompleteActivity, BoardActivity::class.java))
            })
        })

        winRestartButton.setOnClickListener({
            FirebaseSaveHelper.newLevel(currentUser!!.uid, Session.saveFile!!.currentLevel.id, {
                startActivity(Intent(this@LevelCompleteActivity, BoardActivity::class.java))
            })
        })
    }
}

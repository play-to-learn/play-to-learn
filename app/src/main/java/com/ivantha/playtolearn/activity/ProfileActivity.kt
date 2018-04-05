package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Show Current Level
        var level: Int = Session.saveFile!!.currentLevel.id
        levelTextView.text = level.toString()

        // Show Total Score
        // TODO: Get total score

        // Show Current Rank
        // TODO: Get current rank
    }


}

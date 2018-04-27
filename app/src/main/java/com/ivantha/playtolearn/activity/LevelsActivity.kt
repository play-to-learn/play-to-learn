package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.adapter.LevelRecyclerAdapter
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Level
import kotlinx.android.synthetic.main.activity_levels.*
import java.util.*


class LevelsActivity : AppCompatActivity() {

    private val levels = ArrayList<Level>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        var gridLayoutManager = GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false)

        levelsRecyclerView.layoutManager = gridLayoutManager
        levelsRecyclerView.setHasFixedSize(true)

        var levelRecyclerAdapter = LevelRecyclerAdapter(levels)

        // Set levels
        for (i in 1..Session.levelCount) {
            var level = Level(i)
            levels.add(level)
        }

        // Set enabled levels
        for (i in 0..(Session.enabledLevelCount - 1)) {
            levels[i].enabled = true
        }

        levelsRecyclerView.adapter = levelRecyclerAdapter
    }
}

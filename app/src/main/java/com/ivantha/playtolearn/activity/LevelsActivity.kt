package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.adapter.LevelRecyclerAdaper
import com.ivantha.playtolearn.model.Level
import kotlinx.android.synthetic.main.activity_levels.*
import java.util.*


class LevelsActivity : AppCompatActivity() {
    private var gridLayoutManager: GridLayoutManager? = null
    private var levelRecyclerAdaper: LevelRecyclerAdaper? = null

    private val levels = ArrayList<Level>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        // Initialize GridLayoutManager
        gridLayoutManager = GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false)

        levelsRecyclerView.layoutManager = gridLayoutManager
        levelsRecyclerView.setHasFixedSize(true)

        levels.add(Level(1))
        levels.add(Level(2))
        levels.add(Level(3))
        levels.add(Level(4))
        levels.add(Level(5))
        levels.add(Level(6))
        levels.add(Level(7))
        levels.add(Level(8))
        levels.add(Level(9))
        levels.add(Level(10))
        levels.add(Level(11))

        levelRecyclerAdaper = LevelRecyclerAdaper(levels)
        levelsRecyclerView.adapter = levelRecyclerAdaper
    }
}

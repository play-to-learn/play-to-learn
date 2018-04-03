package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

        var firebaseDatabase = FirebaseDatabase.getInstance()

        // Get levels
        firebaseDatabase.getReference("level_info/count").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                var levelCount = dataSnapshot!!.value.toString().toInt()

                for (i in 1..levelCount) {
                    var level = Level(i)
                    levels.add(level)
                }

                for(i in 0..(Session.saveFile!!.currentLevel.id - 1)){
                    levels[i].enabled = true
                }

                levelRecyclerAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LevelsActivity, "Level count retrieval error", Toast.LENGTH_SHORT).show()
            }
        })

        levelsRecyclerView.adapter = levelRecyclerAdapter
    }
}

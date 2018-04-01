package com.ivantha.playtolearn.activity

import android.media.MediaCas
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.adapter.LevelRecyclerAdapter
import com.ivantha.playtolearn.model.Level
import kotlinx.android.synthetic.main.activity_levels.*
import java.util.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.common.Session
import org.json.JSONObject


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
        var levelCount: Int
        firebaseDatabase.getReference("level_info/count").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                var x = dataSnapshot!!.value
                if(x is String){
                    levelCount = x.toInt()

                    for (i in 1..levelCount) {
                        var level = Level(i)
                        level.enabled = false
                        levels.add(level)
                    }
                    levelRecyclerAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LevelsActivity, "Level count retrieval error", Toast.LENGTH_SHORT).show()
            }
        })

        firebaseDatabase.getReference("players/${Session.profile!!.uid}/current_level").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                var x = dataSnapshot!!.value
                if(x is String){
                    var playerCurrentLevel = x.toInt()

                    for(i in 0..(playerCurrentLevel - 1)){
                        levels[i].enabled = true
                    }
                    levelRecyclerAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                Toast.makeText(this@LevelsActivity, "Player current level retrieval error", Toast.LENGTH_SHORT).show()
            }
        })

        levelsRecyclerView.adapter = levelRecyclerAdapter
    }
}

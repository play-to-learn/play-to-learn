package com.ivantha.playtolearn.activity

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
import org.json.JSONObject


class LevelsActivity : AppCompatActivity() {
    private var gridLayoutManager: GridLayoutManager? = null
    private var levelRecyclerAdapter: LevelRecyclerAdapter? = null

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val levels = ArrayList<Level>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        // Initialize GridLayoutManager
        gridLayoutManager = GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false)

        levelsRecyclerView.layoutManager = gridLayoutManager
        levelsRecyclerView.setHasFixedSize(true)

        levelRecyclerAdapter = LevelRecyclerAdapter(levels)

        var databaseReference = firebaseDatabase.getReference("levels")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Possible optimizations may exist when retrieving the value
                for(child in dataSnapshot.children){
                    levels.add(Level(JSONObject(child.value.toString()).get("id") as Int))
                }

                levelRecyclerAdapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LevelsActivity, "Level retrieval error", Toast.LENGTH_SHORT).show()
            }
        })

        levelsRecyclerView.adapter = levelRecyclerAdapter
    }
}

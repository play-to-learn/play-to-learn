package com.ivantha.playtolearn.adapter

import android.content.Intent
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.activity.BoardActivity
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Level
import com.ivantha.playtolearn.model.Question

class LevelRecyclerAdapter(private val levels: List<Level>) : RecyclerView.Adapter<LevelRecyclerAdapter.LevelViewHolder>() {

    var viewGroup: ViewGroup? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_level, parent, false)
        viewGroup = parent

        return LevelViewHolder(v)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.id = levels[position].id
        holder.appCompatButton.isEnabled = levels[position].enabled
        if(levels[position].enabled){
            holder.appCompatButton.text = levels[position].id.toString()
        }
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val appCompatButton: AppCompatButton = itemView.findViewById(R.id.levelButton)
        var id: Int = 0

        init {
            appCompatButton.setOnClickListener({
                Session.saveFile!!.currentLevel.id = id
                Session.saveFile!!.currentLevel.score = 0

                FirebaseDatabase.getInstance().getReference("levels/$id/questions").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        for (child in dataSnapshot!!.children){
                            var question = child.getValue(Question::class.java)
                            Session.saveFile!!.currentLevel.questions.add(question!!)
                        }
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        Toast.makeText(viewGroup!!.context, "Player current level retrieval error", Toast.LENGTH_SHORT).show()
                    }
                })

                FirebaseSaveHelper.saveGame(FirebaseAuth.getInstance().currentUser!!.uid)

                viewGroup!!.context.startActivity(Intent(viewGroup!!.context, BoardActivity::class.java))
            })
        }
    }
}
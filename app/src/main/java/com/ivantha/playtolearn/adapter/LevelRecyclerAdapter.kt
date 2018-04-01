package com.ivantha.playtolearn.adapter

import android.content.Intent
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.activity.BoardActivity
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Level

class LevelRecyclerAdapter(private val levels: List<Level>) : RecyclerView.Adapter<LevelRecyclerAdapter.LevelViewHolder>() {

    var viewGroup: ViewGroup? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_level, parent, false)
        viewGroup = parent

        return LevelViewHolder(v)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.appCompatButton.text = levels[position].id.toString()
        holder.id = levels[position].id
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val appCompatButton: AppCompatButton = itemView.findViewById(R.id.levelButton)
        var id: Int = 0

        init {
            appCompatButton.setOnClickListener({
                Session.currentLevel = id
                viewGroup!!.context.startActivity(Intent(viewGroup!!.context, BoardActivity::class.java))
            })
        }
    }
}
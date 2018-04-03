package com.ivantha.playtolearn.common

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.common.Session.COLUMN_COUNT
import com.ivantha.playtolearn.common.Session.ROW_COUNT
import com.ivantha.playtolearn.model.Board
import com.ivantha.playtolearn.model.Level
import kotlin.reflect.KFunction0

object FirebaseSaveHelper {
    private var firebaseDatabase = FirebaseDatabase.getInstance()

    fun newGame(uid: String){
        Session.currentLevel = Level(1)
        firebaseDatabase.getReference("players/$uid").child("current_level").setValue(Session.currentLevel)

        Session.currentBoard = Board(ROW_COUNT, COLUMN_COUNT)
        firebaseDatabase.getReference("players/$uid").child("current_board").setValue(Session.currentBoard)
    }

    fun loadGame(uid: String, kFunction0: KFunction0<Unit>){
        FirebaseDatabase.getInstance().getReference("players/$uid/current_level").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Session.currentLevel = dataSnapshot!!.getValue(Level::class.java)
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("Not implemented")
            }
        })

        FirebaseDatabase.getInstance().getReference("players/$uid/current_board").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Session.currentBoard = dataSnapshot!!.getValue(Board::class.java)
                kFunction0()
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("Not implemented")
            }
        })
    }

    fun saveGame(uid: String){
        firebaseDatabase.getReference("players/$uid").child("current_level").setValue(Session.currentLevel)
        firebaseDatabase.getReference("players/$uid").child("current_board").setValue(Session.currentBoard)
    }

}

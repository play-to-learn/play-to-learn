package com.ivantha.playtolearn.common

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.model.Category
import com.ivantha.playtolearn.model.Question
import com.ivantha.playtolearn.model.SaveFile

object Session {

    var questions: ArrayList<Question> = ArrayList()
    var levelCount: Int = 0

    var currentUser : FirebaseUser? = null
    var saveFile: SaveFile? = null
    var enabledLevelCount: Int = 0

    private var firebaseDatabase = FirebaseDatabase.getInstance()

    init {
        // Enable Firebase database persistence
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener {
            if (it.isSuccessful){
                currentUser = FirebaseAuth.getInstance().currentUser
                setEnabledLevelCount()
            }
        }

        setLevelCount()
    }

    private fun setLevelCount(){
        firebaseDatabase.getReference("level_info/count").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                if (dataSnapshot!!.value != null) {
                    levelCount = dataSnapshot.value.toString().toInt()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not implemented")
            }
        })
    }

    private fun setEnabledLevelCount(){
        firebaseDatabase.getReference("players/${currentUser!!.uid}/enabled_level_Count").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                if (dataSnapshot!!.value != null) {
                    enabledLevelCount = dataSnapshot.value.toString().toInt()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not implemented")
            }
        })
    }

}
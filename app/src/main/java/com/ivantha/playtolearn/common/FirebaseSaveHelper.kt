package com.ivantha.playtolearn.common

import com.google.firebase.database.FirebaseDatabase
import java.util.*

object FirebaseSaveHelper {

    fun newGame(uid: String){
        // Set profiles
        var firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.getReference("players/$uid").child("start_time").setValue(Date().time)
        firebaseDatabase.getReference("players/$uid").child("current_level").setValue("1")
    }

    fun loadGame(uid: String){

    }

    fun saveGame(uid: String){

    }

    fun saveExists(uid: String): Boolean{
        return false
    }
}

package com.ivantha.playtolearn.common

import com.google.firebase.database.FirebaseDatabase
import java.util.*

object FirebaseSaveHelper {

    fun newGame(uid: String){
        FirebaseDatabase.getInstance().getReference("/users").child(uid).child("timestamp").setValue(Calendar.getInstance())
    }

    fun loadGame(uid: String){

    }

    fun saveGame(uid: String){

    }

    fun saveExists(uid: String): Boolean{
        return false
    }
}

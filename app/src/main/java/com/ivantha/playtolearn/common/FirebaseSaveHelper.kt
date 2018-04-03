package com.ivantha.playtolearn.common

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.model.SaveFile

object FirebaseSaveHelper {
    private var firebaseDatabase = FirebaseDatabase.getInstance()

    fun newGame(uid: String){
        Session.saveFile = SaveFile()
        Session.saveFile!!.currentLevel.id = 1
        firebaseDatabase.getReference("players/$uid").child("save_file").setValue(Session.saveFile)
    }

    fun loadGame(uid: String){
        TODO("This method does not work correctly")
        FirebaseDatabase.getInstance().getReference("players/$uid/save_file").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Session.saveFile = dataSnapshot!!.getValue(SaveFile::class.java)
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("Not implemented")
            }
        })
    }

    fun saveGame(uid: String){
        firebaseDatabase.getReference("players/$uid").child("save_file").setValue(Session.saveFile)
    }

}

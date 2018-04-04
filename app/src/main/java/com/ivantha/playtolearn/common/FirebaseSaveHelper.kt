package com.ivantha.playtolearn.common

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.model.Question
import com.ivantha.playtolearn.model.SaveFile

object FirebaseSaveHelper {
    private var firebaseDatabase = FirebaseDatabase.getInstance()

    fun newGame(uid: String){
        Session.saveFile = SaveFile()
        Session.saveFile!!.currentLevel.id = 1
        firebaseDatabase.getReference("players/$uid").child("save_file").setValue(Session.saveFile)
    }

    fun saveGame(uid: String){
        firebaseDatabase.getReference("players/$uid").child("save_file").setValue(Session.saveFile)
    }

    fun restartLevel(uid: String){
        var tempId = Session.saveFile!!.currentLevel.id

        Session.saveFile = SaveFile()
        setLevel(tempId)
        firebaseDatabase.getReference("players/$uid").child("save_file").setValue(Session.saveFile)
    }

    fun loadNextLevel(uid: String){
        var tempId = Session.saveFile!!.currentLevel.id

        Session.saveFile = SaveFile()
        setLevel(tempId + 1)
        firebaseDatabase.getReference("players/$uid").child("save_file").setValue(Session.saveFile)
    }

    fun setLevel(id: Int){
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
                TODO("Not implemented")
            }
        })
    }

}

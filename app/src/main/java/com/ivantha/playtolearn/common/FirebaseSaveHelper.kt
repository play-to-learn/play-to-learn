package com.ivantha.playtolearn.common

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.model.Question
import com.ivantha.playtolearn.model.SaveFile

object FirebaseSaveHelper {
    private var firebaseDatabase = FirebaseDatabase.getInstance()

    fun newLevel(uid: String, levelId: Int){
        // Create save file
        Session.saveFile = SaveFile()
        Session.saveFile!!.currentLevel.id = levelId

        // Save
        firebaseDatabase.getReference("players/$uid/save_data").child(levelId.toString()).setValue(Session.saveFile)
        firebaseDatabase.getReference("players/$uid").child("enabled_level_Count").setValue(1)

        // Retrieve questions
        FirebaseDatabase.getInstance().getReference("levels/$levelId/questions").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                for (child in dataSnapshot!!.children){
                    var question = child.getValue(Question::class.java)
                    Session.questions.add(question!!)
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("Not implemented")
            }
        })
    }

    fun nextLevel(uid: String){
        newLevel(uid, Session.saveFile!!.currentLevel.id + 1)
        firebaseDatabase.getReference("players/$uid").child("enabled_level_Count").setValue(Session.saveFile!!.currentLevel.id)
    }

    fun continueLevel(uid: String, levelId: Int, onCompleteTask: () -> Unit){
        FirebaseDatabase.getInstance().getReference("players/$uid/save_data/$levelId").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Session.saveFile = dataSnapshot!!.getValue(SaveFile::class.java)
                onCompleteTask()
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("Not implemented")
            }
        })
    }

    fun clearSaveData(uid: String){
        firebaseDatabase.getReference("players/$uid").child("save_data").setValue(null)
    }

    fun saveCurrentLevel(uid: String){
        firebaseDatabase.getReference("players/$uid/save_data").child(Session.saveFile!!.currentLevel.id.toString()).setValue(Session.saveFile)
    }

}

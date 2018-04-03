package com.ivantha.playtolearn.common

import com.google.firebase.database.FirebaseDatabase
import com.ivantha.playtolearn.model.Board
import com.ivantha.playtolearn.model.Level

object FirebaseSaveHelper {

    fun newGame(uid: String){
        var firebaseDatabase = FirebaseDatabase.getInstance()

        Session.currentLevel = Level(1)
        firebaseDatabase.getReference("players/$uid").child("current_level").setValue(Session.currentLevel)

        Session.currentBoard = Board(Session.ROW_COUNT, Session.COLUMN_COUNT)
        firebaseDatabase.getReference("players/$uid").child("current_board").setValue(Session.currentBoard)
    }

    fun loadGame(uid: String){

    }

    fun saveGame(uid: String){

    }

}

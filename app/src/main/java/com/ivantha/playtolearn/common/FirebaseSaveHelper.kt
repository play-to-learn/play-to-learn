package com.ivantha.playtolearn.common

import com.google.firebase.database.FirebaseDatabase
import com.ivantha.playtolearn.common.Session.COLUMN_COUNT
import com.ivantha.playtolearn.common.Session.ROW_COUNT
import com.ivantha.playtolearn.model.Board
import com.ivantha.playtolearn.model.Level

object FirebaseSaveHelper {
    private var firebaseDatabase = FirebaseDatabase.getInstance()

    fun newGame(uid: String){
        Session.currentLevel = Level(1)
        firebaseDatabase.getReference("players/$uid").child("current_level").setValue(Session.currentLevel)

        Session.currentBoard = Board(ROW_COUNT, COLUMN_COUNT)
        firebaseDatabase.getReference("players/$uid").child("current_board").setValue(Session.currentBoard)
    }

    fun saveGame(uid: String){
        firebaseDatabase.getReference("players/$uid").child("current_level").setValue(Session.currentLevel)
        firebaseDatabase.getReference("players/$uid").child("current_board").setValue(Session.currentBoard)
    }

}

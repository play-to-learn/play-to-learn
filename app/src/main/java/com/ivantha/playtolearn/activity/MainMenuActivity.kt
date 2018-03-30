package com.ivantha.playtolearn.activity

import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.firebase.database.FirebaseDatabase
import com.ivantha.playtolearn.LevelsActivity
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Question
import com.ivantha.playtolearn.common.SaveHelper
import com.ivantha.playtolearn.common.SettingsHelper

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        this.initializeFirebase()
        this.initializeSession()
    }

    public override fun onStart() {
        super.onStart()
    }

    fun onClickContinueButton(view: View) {
        Session.saveHelper.loadGame()
        startActivity(Intent(this@MainMenuActivity, LevelsActivity::class.java))
    }

    fun onClickNewGameButton(view: View) {
        Session.saveHelper.newGame()
        startActivity(Intent(this@MainMenuActivity, LevelsActivity::class.java))
    }

    fun onClickOptionsButton(view: View) {
        startActivity(Intent(this@MainMenuActivity, SettingsActivity::class.java))
    }

    fun onClickProfileButton(view: View) {
        startActivity(Intent(this@MainMenuActivity, ProfileActivity::class.java))
    }

    fun onClickLeaderboardButton(view: View) {
        startActivity(Intent(this@MainMenuActivity, LeaderboardActivity::class.java))
    }

    fun onClickHelpButton(view: View) {
        startActivity(Intent(this@MainMenuActivity, HelpActivity::class.java))
    }

    fun initializeFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    private fun initializeSession() {
        Session.saveHelper = SaveHelper(applicationContext)

        Session.settingsHelper = SettingsHelper(applicationContext)
        if (Session.settingsHelper.settingsExists()) {
            Session.settingsHelper.loadSettings()
        } else {
            Session.settingsHelper.newSettings()
            Session.settingsHelper.saveSettings()
        }

        Session.SCREEN_WIDTH = Resources.getSystem().displayMetrics.widthPixels
        Session.SCREEN_HEIGHT = Resources.getSystem().displayMetrics.heightPixels

        Session.database = FirebaseDatabase.getInstance()

        this.addFirebaseData()
    }

    private fun addFirebaseData() {
        Session.database.getReference("/").setValue(null)

        // Sample questions
        val q1 = Question()
        q1.title = "One"
        q1.description = "X = 1\n" +
                "Y = 2\n" +
                "Z = X + Y\n" +
                "GO Z steps forward"
        q1.answer = 3

        val q2 = Question()
        q2.title = "Two"
        q2.description = "A = 3\n" +
                "B = 4\n" +
                "C = 5\n" +
                "X = A - B + C\n" +
                "GO X steps forward"
        q2.answer = 4

        val q3 = Question()
        q3.title = "Three"
        q3.description = "X = 10\n" +
                "Y = 17\n" +
                "Z = (X+Y)%X\n" +
                "GO Z steps forward"
        q3.answer = 7

        val q4 = Question()
        q4.title = "Four"
        q4.description = "A = 25\n" +
                "B = 13\n" +
                "C = 11\n" +
                "IF (B+C < A) THEN\n" +
                "X = 7\n" +
                "ELSE\n" +
                "X = 9\n" +
                "GO X steps forward"
        q4.answer = 7

        val q5 = Question()
        q5.title = "Five"
        q5.description = "X = 2\n" +
                "WHILE X < 10\n" +
                "\tX = X + 1\n" +
                "\tGO 1 step forward"
        q5.answer = 8

        val q6 = Question()
        q6.title = "Six"
        q6.description = "X = 5\n" +
                "Y = 6\n" +
                "WHILE X > 3\n" +
                "\tX = X - 1\n" +
                "\tY = Y + 1\n" +
                "GO Y steps forward"
        q6.answer = 8

        // Creating the categories
        Session.database.getReference("categories/conditional_structures").child("name").setValue("Conditional Structures")
        Session.database.getReference("categories/loops").child("name").setValue("Loops")
        Session.database.getReference("categories/syntax").child("name").setValue("Syntax")
        Session.database.getReference("categories/semantics").child("name").setValue("Semantics")
        Session.database.getReference("categories/variables").child("name").setValue("Variables")
        Session.database.getReference("categories/error_handling").child("name").setValue("Error Handling")
        Session.database.getReference("categories/threads").child("name").setValue("Threads")

        // Creating the levels
        Session.database.getReference("levels").push().child("id").setValue("1")
        Session.database.getReference("levels").push().child("id").setValue("2")
        Session.database.getReference("levels").push().child("id").setValue("3")
        Session.database.getReference("levels").push().child("id").setValue("4")
        Session.database.getReference("levels").push().child("id").setValue("5")
        Session.database.getReference("levels").push().child("id").setValue("6")
        Session.database.getReference("levels").push().child("id").setValue("7")

        // Set the questions
        Session.database.getReference("questions/level/1/category/conditional_structures").push().setValue(q1)
        Session.database.getReference("questions/level/1/category/conditional_structures").push().setValue(q2)
        Session.database.getReference("questions/level/1/category/conditional_structures").push().setValue(q3)
        Session.database.getReference("questions/level/1/category/conditional_structures").push().setValue(q4)
        Session.database.getReference("questions/level/1/category/loops").push().setValue(q5)
        Session.database.getReference("questions/level/1/category/loops").push().setValue(q6)
    }
}

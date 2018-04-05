package com.ivantha.playtolearn.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.common.SettingsHelper
import com.ivantha.playtolearn.model.Category
import com.ivantha.playtolearn.model.Question
import kotlinx.android.synthetic.main.activity_main_menu.*


class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        continueButton.setOnClickListener({
            startActivity(Intent(this@MainMenuActivity, LevelsActivity::class.java))
        })

        newGameButton.setOnClickListener({
            FirebaseSaveHelper.clearSaveData(Session.currentUser!!.uid)
            FirebaseSaveHelper.newLevel(Session.currentUser!!.uid, 1, {
                startActivity(Intent(this@MainMenuActivity, BoardActivity::class.java))
            })
        })

        optionsButton.setOnClickListener({
            startActivity(Intent(this@MainMenuActivity, SettingsActivity::class.java))
        })

        profileButton.setOnClickListener({
            startActivity(Intent(this@MainMenuActivity, ProfileActivity::class.java))
        })

        leaderboardButton.setOnClickListener({
            startActivity(Intent(this@MainMenuActivity, LeaderboardActivity::class.java))
        })

        helpButton.setOnClickListener({
//            startActivity(Intent(this@MainMenuActivity, HelpActivity::class.java))
            addFirebaseData()
        })

        // Disable continue button if no save exists
        continueButton.isEnabled = (Session.enabledLevelCount >= 2)

        initializeSettingsHelper()
    }

    private fun initializeSettingsHelper() {
        if (SettingsHelper.settingsExists(applicationContext)) {
            SettingsHelper.loadSettings(applicationContext)
        } else {
            SettingsHelper.newSettings()
            SettingsHelper.saveSettings(applicationContext)
        }
    }

    private fun addFirebaseData() {
        var firebaseDatabase = FirebaseDatabase.getInstance()

        // Clear all previous data
        firebaseDatabase.getReference("/").setValue(null)

        // Categories
        var c1 = Category("conditional_structures", "Conditional Structures")
        var c2 = Category("loops", "Loops")
        var c3 = Category("syntax", "Syntax")
        var c4 = Category("semantics","Semantics")
        var c5 = Category("variables","Variables")
        var c6 = Category("error_handling","Error Handling")
        var c7 = Category("threads","Threads")

        // Sample questions
        val q1 = Question()
        q1.title = "One"
        q1.description = "X = 1\n" +
                "Y = 2\n" +
                "Z = X + Y\n" +
                "GO Z steps forward"
        q1.answer = 3
        q1.category = c1

        val q2 = Question()
        q2.title = "Two"
        q2.description = "A = 3\n" +
                "B = 4\n" +
                "C = 5\n" +
                "X = A - B + C\n" +
                "GO X steps forward"
        q2.answer = 4
        q2.category = c1

        val q3 = Question()
        q3.title = "Three"
        q3.description = "X = 10\n" +
                "Y = 17\n" +
                "Z = (X+Y)%X\n" +
                "GO Z steps forward"
        q3.answer = 7
        q3.category = c1

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
        q4.category = c1

        val q5 = Question()
        q5.title = "Five"
        q5.description = "X = 2\n" +
                "WHILE X < 10\n" +
                "\tX = X + 1\n" +
                "\tGO 1 step forward"
        q5.answer = 8
        q5.category = c2

        val q6 = Question()
        q6.title = "Six"
        q6.description = "X = 5\n" +
                "Y = 6\n" +
                "WHILE X > 3\n" +
                "\tX = X - 1\n" +
                "\tY = Y + 1\n" +
                "GO Y steps forward"
        q6.answer = 8
        q6.category = c2

        // Creating the categories
        firebaseDatabase.getReference("categories").push().setValue(c1)
        firebaseDatabase.getReference("categories").push().setValue(c2)
        firebaseDatabase.getReference("categories").push().setValue(c3)
        firebaseDatabase.getReference("categories").push().setValue(c4)
        firebaseDatabase.getReference("categories").push().setValue(c5)
        firebaseDatabase.getReference("categories").push().setValue(c6)
        firebaseDatabase.getReference("categories").push().setValue(c7)

        // Set level info
        firebaseDatabase.getReference("level_info").child("count").setValue(5)

        // Creating the levels
        firebaseDatabase.getReference("levels/1/questions").push().setValue(q1)
        firebaseDatabase.getReference("levels/1/questions").push().setValue(q2)
        firebaseDatabase.getReference("levels/2/questions").push().setValue(q3)
        firebaseDatabase.getReference("levels/2/questions").push().setValue(q4)
        firebaseDatabase.getReference("levels/1/questions").push().setValue(q5)
        firebaseDatabase.getReference("levels/2/questions").push().setValue(q6)

        // Set the questions
        firebaseDatabase.getReference("questions/levels/1/categories/conditional_structures").push().setValue(q1)
        firebaseDatabase.getReference("questions/levels/1/categories/conditional_structures").push().setValue(q2)
        firebaseDatabase.getReference("questions/levels/1/categories/conditional_structures").push().setValue(q3)
        firebaseDatabase.getReference("questions/levels/1/categories/conditional_structures").push().setValue(q4)
        firebaseDatabase.getReference("questions/levels/1/categories/loops").push().setValue(q5)
        firebaseDatabase.getReference("questions/levels/1/categories/loops").push().setValue(q6)
    }

}
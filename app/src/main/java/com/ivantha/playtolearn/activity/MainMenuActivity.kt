package com.ivantha.playtolearn.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Question
import kotlinx.android.synthetic.main.activity_main_menu.*


class MainMenuActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private var firebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        continueButton.setOnClickListener({
            FirebaseSaveHelper.loadGame(currentUser!!.uid)
            startActivity(Intent(this@MainMenuActivity, LevelsActivity::class.java))
        })

        newGameButton.setOnClickListener({
            FirebaseSaveHelper.newGame(currentUser!!.uid)
            startActivity(Intent(this@MainMenuActivity, LevelsActivity::class.java))
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
            startActivity(Intent(this@MainMenuActivity, HelpActivity::class.java))
        })

        this.initializeFirebase()
        this.initializeSession()
    }

    private fun initializeFirebase() {
        // Sign in anonymously
        if (currentUser == null) {
            mAuth!!.signInAnonymously().addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this, "Anonymous login unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Enable Firebase database persistence
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    private fun initializeSession() {
        if (Session.settingsHelper.settingsExists(applicationContext)) {
            Session.settingsHelper.loadSettings(applicationContext)
        } else {
            Session.settingsHelper.newSettings()
            Session.settingsHelper.saveSettings(applicationContext)
        }

        this.addFirebaseData()


    }

    private fun addFirebaseData() {
        firebaseDatabase!!.getReference("/").setValue(null)

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
        firebaseDatabase!!.getReference("categories/conditional_structures").child("name").setValue("Conditional Structures")
        firebaseDatabase!!.getReference("categories/loops").child("name").setValue("Loops")
        firebaseDatabase!!.getReference("categories/syntax").child("name").setValue("Syntax")
        firebaseDatabase!!.getReference("categories/semantics").child("name").setValue("Semantics")
        firebaseDatabase!!.getReference("categories/variables").child("name").setValue("Variables")
        firebaseDatabase!!.getReference("categories/error_handling").child("name").setValue("Error Handling")
        firebaseDatabase!!.getReference("categories/threads").child("name").setValue("Threads")

        // Creating the levels
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("1")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("2")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("3")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("4")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("5")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("6")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("7")
        firebaseDatabase!!.getReference("levels").push().child("id").setValue("8")

        // Set the questions
        firebaseDatabase!!.getReference("questions/level/1/category/conditional_structures").push().setValue(q1)
        firebaseDatabase!!.getReference("questions/level/1/category/conditional_structures").push().setValue(q2)
        firebaseDatabase!!.getReference("questions/level/1/category/conditional_structures").push().setValue(q3)
        firebaseDatabase!!.getReference("questions/level/1/category/conditional_structures").push().setValue(q4)
        firebaseDatabase!!.getReference("questions/level/1/category/loops").push().setValue(q5)
        firebaseDatabase!!.getReference("questions/level/1/category/loops").push().setValue(q6)
    }
}

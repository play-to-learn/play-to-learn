package com.avoid.playtolearn.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.models.Question;
import com.avoid.playtolearn.utils.SaveHelper;
import com.avoid.playtolearn.utils.SettingsHelper;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        this.initializeSession();
        this.loadQuestions();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onClickContinueButton(View view){
        Session.saveHelper.loadGame();
        startActivity(new Intent(MainMenuActivity.this, BoardActivity.class));
    }

    public void onClickNewGameButton(View view){
        Session.saveHelper.newGame();
        startActivity(new Intent(MainMenuActivity.this, BoardActivity.class));
    }

    public void onClickOptionsButton(View view){
        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
    }

    public void onClickProfileButton(View view){
        startActivity(new Intent(MainMenuActivity.this, ProfileActivity.class));
    }

    public void onClickLeaderboardButton(View view){
        startActivity(new Intent(MainMenuActivity.this, LeaderboardActivity.class));
    }

    public void onClickHelpButton(View view){
        startActivity(new Intent(MainMenuActivity.this, HelpActivity.class));
    }

    public void initializeSession(){
        Session.saveHelper = new SaveHelper(getApplicationContext());

        Session.settingsHelper = new SettingsHelper(getApplicationContext());
        if(Session.settingsHelper.settingsExists()){
            Session.settingsHelper.loadSettings();
        }else{
            Session.settingsHelper.newSettings();
            Session.settingsHelper.saveSettings();
        }

        Session.SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        Session.SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

        Session.database = FirebaseDatabase.getInstance();
    }

    public void loadQuestions(){
        // Sample questions
        Question q1 = new Question();
        q1.setTitle("One");
        q1.setDescription("X = 1\n" +
                "Y = 2\n" +
                "Z = X + Y\n" +
                "GO Z steps forward");
        q1.setAnswer(3);

        Question q2 = new Question();
        q2.setTitle("Two");
        q2.setDescription("A = 3\n" +
                "B = 4\n" +
                "C = 5\n" +
                "X = A - B + C\n" +
                "GO X steps forward");
        q2.setAnswer(4);

        Question q3 = new Question();
        q3.setTitle("Three");
        q3.setDescription("X = 10\n" +
                "Y = 17\n" +
                "Z = (X+Y)%X\n" +
                "GO Z steps forward");
        q3.setAnswer(7);

        Question q4 = new Question();
        q4.setTitle("Four");
        q4.setDescription("A = 25\n" +
                "B = 13\n" +
                "C = 11\n" +
                "IF (B+C < A) THEN\n" +
                "X = 7\n" +
                "ELSE\n" +
                "X = 9\n" +
                "GO X steps forward");
        q4.setAnswer(7);

        Question q5 = new Question();
        q5.setTitle("Five");
        q5.setDescription("X = 2\n" +
                "WHILE X < 10\n" +
                "\tX = X + 1\n" +
                "\tGO 1 step forward");
        q5.setAnswer(8);

        Question q6 = new Question();
        q6.setTitle("Six");
        q6.setDescription("X = 5\n" +
                "Y = 6\n" +
                "WHILE X > 3\n" +
                "\tX = X - 1\n" +
                "\tY = Y + 1\n" +
                "GO Y steps forward");
        q6.setAnswer(8);


        // Creating the categories
        Session.database.getReference("categories/conditional_structures").child("name").setValue("Conditional Structures");
        Session.database.getReference("categories/loops").child("name").setValue("Loops");

        // Creating the difficulties
        Session.database.getReference("difficulties/easy").child("name").setValue("Easy");
        Session.database.getReference("difficulties/easy").child("difficulty_index").setValue("1");
        Session.database.getReference("difficulties/medium").child("name").setValue("Medium");
        Session.database.getReference("difficulties/medium").child("difficulty_index").setValue("2");
        Session.database.getReference("difficulties/hard").child("name").setValue("Hard");
        Session.database.getReference("difficulties/hard").child("difficulty_index").setValue("3");

        // Set the questions
        Session.database.getReference("questions/difficulty/easy/category/conditional_structures").push().setValue(q1);
        Session.database.getReference("questions/category/conditional_structures/easy").push().setValue(q1);
        Session.database.getReference("questions/difficulty/easy/category/conditional_structures").push().setValue(q2);
        Session.database.getReference("questions/category/conditional_structures/easy").push().setValue(q2);
        Session.database.getReference("questions/difficulty/easy/category/conditional_structures").push().setValue(q3);
        Session.database.getReference("questions/category/conditional_structures/easy").push().setValue(q3);
        Session.database.getReference("questions/difficulty/easy/category/conditional_structures").push().setValue(q4);
        Session.database.getReference("questions/category/conditional_structures/easy").push().setValue(q4);
        Session.database.getReference("questions/difficulty/easy/category/loops").push().setValue(q5);
        Session.database.getReference("questions/category/loops/easy").push().setValue(q5);
        Session.database.getReference("questions/difficulty/easy/category/loops").push().setValue(q6);
        Session.database.getReference("questions/category/loops/easy").push().setValue(q6);
    }
}

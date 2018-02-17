package com.avoid.playtolearn.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.database.cursor.QuestionSQL;
import com.avoid.playtolearn.game.QuestionCache;
import com.avoid.playtolearn.utils.SaveHelper;
import com.avoid.playtolearn.utils.SettingsHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenuActivity extends AppCompatActivity {
    private LinearLayout tileGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.tileGridLayout = (LinearLayout) findViewById(R.id.tile_grid_layout);

        Session.saveHelper = new SaveHelper(getApplicationContext());

        Session.settingsHelper = new SettingsHelper(getApplicationContext());
        if(Session.settingsHelper.settingsExists()){
            Session.settingsHelper.loadSettings();
        }else{
            Session.settingsHelper.newSettings();
            Session.settingsHelper.saveSettings();
        }

        Session.databaseHelper = new DatabaseHelper(MainMenuActivity.this);
        Session.readableDatabase = Session.databaseHelper.getReadableDatabase();
        Session.writableDatabase = Session.databaseHelper.getWritableDatabase();

        Session.SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        Session.SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

        loadQuestions();


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
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

    public static void loadQuestions(){
        QuestionSQL questionSQL = new QuestionSQL();
        QuestionCache.setQuestionArrayList(questionSQL.getQuestions());
    }
}

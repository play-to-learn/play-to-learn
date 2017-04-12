package com.avoid.playtolearn.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.util.Controller;
import com.avoid.playtolearn.util.SaveFileHandler;
import com.avoid.playtolearn.util.SettingsHandler;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Session.saveFileHandler = new SaveFileHandler(getApplicationContext());

        Session.settingsHandler = new SettingsHandler(getApplicationContext());
        if(Session.settingsHandler.settingsExists()){
            Session.settingsHandler.loadSettings();
        }else{
            Session.settingsHandler.newSettings();
            Session.settingsHandler.saveSettings();
        }

        Session.databaseHelper = new DatabaseHelper(MainMenuActivity.this);
        Session.readableDatabase = Session.databaseHelper.getReadableDatabase();
        Session.writableDatabase = Session.databaseHelper.getWritableDatabase();

        Session.SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        Session.SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

        Controller.loadQuestions();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClickContinueButton(View view){
        Session.saveFileHandler.loadGame();
        startActivity(new Intent(MainMenuActivity.this, BoardActivity.class));
    }

    public void onClickNewGameButton(View view){
        Session.saveFileHandler.newGame();
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
}

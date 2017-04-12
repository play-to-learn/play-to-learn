package com.avoid.playtolearn.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.util.Controller;
import com.avoid.playtolearn.util.SaveFileHandler;
import com.avoid.playtolearn.util.SettingsHandler;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainMenuActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Firebase
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Facebook events
        AppEventsLogger.activateApp(getApplication());

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

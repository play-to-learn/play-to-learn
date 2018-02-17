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
import com.avoid.playtolearn.listeners.FirebaseAuthStateListener;
import com.avoid.playtolearn.utils.App;
import com.avoid.playtolearn.utils.Save;
import com.avoid.playtolearn.utils.Settings;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private LinearLayout tileGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.tileGridLayout = (LinearLayout) findViewById(R.id.tile_grid_layout);

        // Initialize Firebase Auth
        Session.firebaseAuth = FirebaseAuth.getInstance();
        Session.authStateListener = new FirebaseAuthStateListener();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Facebook events
        AppEventsLogger.activateApp(getApplication());

        Session.save = new Save(getApplicationContext());

        Session.settings = new Settings(getApplicationContext());
        if(Session.settings.settingsExists()){
            Session.settings.loadSettings();
        }else{
            Session.settings.newSettings();
            Session.settings.saveSettings();
        }

        Session.databaseHelper = new DatabaseHelper(MainMenuActivity.this);
        Session.readableDatabase = Session.databaseHelper.getReadableDatabase();
        Session.writableDatabase = Session.databaseHelper.getWritableDatabase();

        Session.SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        Session.SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

        App.loadQuestions();
    }

    @Override
    public void onStart() {
        super.onStart();
        Session.firebaseAuth.addAuthStateListener(Session.authStateListener);
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        if (Session.authStateListener != null) {
//            Session.firebaseAuth.removeAuthStateListener(Session.authStateListener);
//        }
//    }

    public void onClickContinueButton(View view){
        Session.save.loadGame();
        startActivity(new Intent(MainMenuActivity.this, BoardActivity.class));
    }

    public void onClickNewGameButton(View view){
        Session.save.newGame();
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

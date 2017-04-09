package com.avoid.playtolearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.util.SaveFileHandler;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Session.saveFileHandler = new SaveFileHandler(getApplicationContext());
        Session.databaseHelper = new DatabaseHelper(MainMenuActivity.this);

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
        Session.readableDatabase = Session.databaseHelper.getReadableDatabase();
//        Session.writableDatabase = Session.databaseHelper.getWritableDatabase();
        startActivity(new Intent(MainMenuActivity.this, LeaderboardActivity.class));
    }
}

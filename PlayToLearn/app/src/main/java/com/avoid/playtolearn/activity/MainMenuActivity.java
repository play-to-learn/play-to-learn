package com.avoid.playtolearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.avoid.playtolearn.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void onClickContinueButton(View view){
        startActivity(new Intent(MainMenuActivity.this, BoardActivity.class));
    }

    public void onClickNewGameButton(View view){
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
}

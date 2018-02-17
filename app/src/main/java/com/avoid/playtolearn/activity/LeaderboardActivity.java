package com.avoid.playtolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.database.SQLParser;
import com.avoid.playtolearn.database.cursor.UserSQL;
import com.avoid.playtolearn.model.Profile;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

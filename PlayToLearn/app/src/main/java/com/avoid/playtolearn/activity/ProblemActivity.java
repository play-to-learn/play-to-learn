package com.avoid.playtolearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.avoid.playtolearn.R;


public class ProblemActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ProblemDialog);
        setContentView(R.layout.activity_problem);
    }
}

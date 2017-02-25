package com.avoid.playtolearn.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avoid.playtolearn.R;

public class RandomActivity extends AppCompatActivity {
    private Button throwButton;
    private TextView diceValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        throwButton = (Button) findViewById(R.id.throw_button);
        diceValueTextView = (TextView) findViewById(R.id.dice_value_text_view);
    }

    public void onClickThrowButton(View view) {

    }
}
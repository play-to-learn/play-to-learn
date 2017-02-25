package com.avoid.playtolearn.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avoid.playtolearn.R;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    Button tossButton;
    TextView textValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        textValue = (TextView) findViewById(R.id.random_text_value);
        tossButton = (Button) findViewById(R.id.random_toss_button);
    }

    public int generateRandomNumber(){
        Random rand = new Random();
        int n = rand.nextInt(6) + 1;
        return n;
    }

    public void onClickTossButton(View view){
        int value = generateRandomNumber();
        textValue.setText(String.valueOf(value));
    }
}

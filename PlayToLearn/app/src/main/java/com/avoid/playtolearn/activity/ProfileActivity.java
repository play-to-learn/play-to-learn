package com.avoid.playtolearn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.avoid.playtolearn.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class ProfileActivity extends AppCompatActivity {
    private LoginButton facebookLoginButton;
    private TextView detailsTextView;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        this.detailsTextView = (TextView) findViewById(R.id.details_text_view);

        this.callbackManager = CallbackManager.Factory.create();

        this.facebookLoginButton.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ProfileActivity.this.detailsTextView.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel() {
                ProfileActivity.this.detailsTextView.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                ProfileActivity.this.detailsTextView.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

package com.avoid.playtolearn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.listener.CustomFacebookCallback;
import com.avoid.playtolearn.test.TestingFunction;
import com.avoid.playtolearn.util.ImageLoadTask;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private LoginButton facebookLoginButton;
    private ImageView profileImageView;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        this.profileImageView = (ImageView) findViewById(R.id.profile_image_view);

        // Initialize Facebook Login button
        this.callbackManager = CallbackManager.Factory.create();
        this.facebookLoginButton.setReadPermissions("email", "public_profile");
        this.facebookLoginButton.registerCallback(this.callbackManager, new CustomFacebookCallback(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickLoadButton(View view) {
        FirebaseUser user = Session.firebaseAuth.getCurrentUser();
        TestingFunction.print(user.getPhotoUrl().toString());
//        new ImageLoadTask(user.getPhotoUrl().toString(), this.profileImageView);
        Picasso.with(ProfileActivity.this).load(user.getPhotoUrl().toString()).fit().into(this.profileImageView);
    }
}

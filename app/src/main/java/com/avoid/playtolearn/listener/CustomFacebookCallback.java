package com.avoid.playtolearn.listener;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.avoid.playtolearn.common.Session;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;

public class CustomFacebookCallback implements FacebookCallback<LoginResult> {
    private Activity activity;
    String TAG = "FacebookCallback TAG";

    public CustomFacebookCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d(TAG, "facebook:onSuccess:UserID:" + loginResult.getAccessToken().getUserId());
        Log.d(TAG, "facebook:onSuccess:AuthToken:" + loginResult.getAccessToken().getToken());
        Log.d(TAG, "facebook:onSuccess:" + loginResult);
        handleFacebookAccessToken(loginResult.getAccessToken());
    }

    @Override
    public void onCancel() {
        Log.d(TAG, "facebook:onCancel");
    }

    @Override
    public void onError(FacebookException error) {
        Log.d(TAG, "facebook:onError", error);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        final String TAG = "Firebase TAG";

        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Session.firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this.activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithCredential", task.getException());
                    Toast.makeText(CustomFacebookCallback.this.activity.getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

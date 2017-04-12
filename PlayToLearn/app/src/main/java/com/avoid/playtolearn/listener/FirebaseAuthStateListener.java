package com.avoid.playtolearn.listener;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthStateListener implements FirebaseAuth.AuthStateListener {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        String TAG = "Firebase TAG";

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }
}

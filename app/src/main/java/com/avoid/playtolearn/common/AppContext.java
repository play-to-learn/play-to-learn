package com.avoid.playtolearn.common;

import android.app.Application;
import android.content.Context;

public class AppContext extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }
}
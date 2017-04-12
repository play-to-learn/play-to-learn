package com.avoid.playtolearn.common;

import android.database.sqlite.SQLiteDatabase;

import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.model.SaveFile;
import com.avoid.playtolearn.model.Settings;
import com.avoid.playtolearn.util.SaveFileHandler;
import com.avoid.playtolearn.util.SettingsHandler;
import com.google.firebase.auth.FirebaseAuth;

public class Session {
    //Save files
    public static SaveFileHandler saveFileHandler = null;
    public static SaveFile currentSaveFile = null;

    //Settings
    public static SettingsHandler settingsHandler = null;
    public static Settings currentSettings = null;

    //Database
    public static DatabaseHelper databaseHelper = null;
    public static SQLiteDatabase readableDatabase = null;
    public static SQLiteDatabase writableDatabase = null;

    //Device
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    //Firebase
    public static FirebaseAuth firebaseAuth = null;
    public static FirebaseAuth.AuthStateListener authStateListener = null;
}

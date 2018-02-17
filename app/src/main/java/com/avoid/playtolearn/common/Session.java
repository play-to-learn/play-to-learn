package com.avoid.playtolearn.common;

import android.database.sqlite.SQLiteDatabase;

import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.models.SaveFile;
import com.avoid.playtolearn.utils.Save;
import com.avoid.playtolearn.utils.Settings;
import com.avoid.playtolearn.widgets.BoardTileLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Session {
    //Save files
    public static Save save = null;
    public static SaveFile currentSaveFile = null;

    //Settings
    public static Settings settings = null;
    public static com.avoid.playtolearn.models.Settings currentSettings = null;

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

    public static ArrayList<ArrayList<BoardTileLayout>> boardLayoutGrid = new ArrayList<>();
}

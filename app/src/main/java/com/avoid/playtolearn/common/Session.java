package com.avoid.playtolearn.common;

import android.database.sqlite.SQLiteDatabase;

import com.avoid.playtolearn.database.DatabaseHelper;
import com.avoid.playtolearn.models.SaveFile;
import com.avoid.playtolearn.models.Settings;
import com.avoid.playtolearn.utils.SaveHelper;
import com.avoid.playtolearn.utils.SettingsHelper;
import com.avoid.playtolearn.widgets.BoardTileLayout;

import java.util.ArrayList;

public class Session {
    //SaveHelper files
    public static SaveHelper saveHelper = null;
    public static SaveFile currentSaveFile = null;

    //SettingsHelper
    public static SettingsHelper settingsHelper = null;
    public static Settings currentSettings = null;

    //Database
    public static DatabaseHelper databaseHelper = null;
    public static SQLiteDatabase readableDatabase = null;
    public static SQLiteDatabase writableDatabase = null;

    //Device
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static ArrayList<ArrayList<BoardTileLayout>> boardLayoutGrid = new ArrayList<>();
}

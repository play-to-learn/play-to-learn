package com.ivantha.playtolearn.common;

import com.google.firebase.database.FirebaseDatabase;
import com.ivantha.playtolearn.BoardActivity;
import com.ivantha.playtolearn.model.SaveFile;
import com.ivantha.playtolearn.model.Settings;
import com.ivantha.playtolearn.utils.SaveHelper;
import com.ivantha.playtolearn.utils.SettingsHelper;

import java.util.ArrayList;

public class Session {
    // SaveHelper files
    public static SaveHelper saveHelper = null;
    public static SaveFile currentSaveFile = null;

    // SettingsHelper
    public static SettingsHelper settingsHelper = null;
    public static Settings currentSettings = null;

    // Device
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    // Board
    public static int COLUMN_COUNT = 6;
    public static int ROW_COUNT = 10;
    public static ArrayList<ArrayList<BoardActivity.BoardTileLayout>> boardLayoutGrid = new ArrayList<>();

    // Firebase
    public static FirebaseDatabase database;

}
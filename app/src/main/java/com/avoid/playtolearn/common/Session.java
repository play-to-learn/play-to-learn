package com.avoid.playtolearn.common;

import com.avoid.playtolearn.models.SaveFile;
import com.avoid.playtolearn.models.Settings;
import com.avoid.playtolearn.utils.SaveHelper;
import com.avoid.playtolearn.utils.SettingsHelper;
import com.avoid.playtolearn.widgets.BoardTileLayout;
import com.google.firebase.database.FirebaseDatabase;

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
    public static ArrayList<ArrayList<BoardTileLayout>> boardLayoutGrid = new ArrayList<>();

    // Firebase
    public static FirebaseDatabase database;

}
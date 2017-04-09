package com.avoid.playtolearn.common;

import android.content.Context;

import com.avoid.playtolearn.model.SaveFile;
import com.avoid.playtolearn.model.Settings;
import com.avoid.playtolearn.util.SaveFileHandler;
import com.avoid.playtolearn.util.SettingsHandler;

public class Session {
    //Save files
    public static SaveFileHandler saveFileHandler = null;
    public static SaveFile currentSaveFile = null;

    //Settings
    private static SettingsHandler settingsHandler = null;
    public static Settings currentSettings = null;

    public static Context currentContext = null;
}

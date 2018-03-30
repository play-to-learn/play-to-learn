package com.ivantha.playtolearn.common;

import android.content.Context;

import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.model.Settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SettingsHelper {
    private Context context;

    public SettingsHelper(Context context) {
        this.context = context;
    }

    public void newSettings() {
        Session.currentSettings = new Settings();
    }

    public void loadSettings() {
        try {
            FileInputStream fileInputStream = context.openFileInput("settingsFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Session.currentSettings = (Settings) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("settingsFile", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Session.currentSettings);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean settingsExists() {
        try {
            FileInputStream fileInputStream = context.openFileInput("settingsFile");
            return fileInputStream != null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

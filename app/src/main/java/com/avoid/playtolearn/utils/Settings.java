package com.avoid.playtolearn.utils;

import android.content.Context;

import com.avoid.playtolearn.common.Session;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Settings {
    private Context context;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;

    public Settings(Context context) {
        this.context = context;
    }

    public void newSettings() {
        Session.currentSettings = new com.avoid.playtolearn.models.Settings();
    }

    public void loadSettings() {
        try {
            fileInputStream = context.openFileInput("settingsFile");
            objectInputStream = new ObjectInputStream(fileInputStream);

            Session.currentSettings = (com.avoid.playtolearn.models.Settings) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        try {
            fileOutputStream = context.openFileOutput("settingsFile", Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Session.currentSettings);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean settingsExists() {
        try {
            fileInputStream = context.openFileInput("settingsFile");
            return fileInputStream != null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

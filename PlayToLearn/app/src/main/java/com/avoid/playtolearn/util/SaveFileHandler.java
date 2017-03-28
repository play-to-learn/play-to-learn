package com.avoid.playtolearn.util;


import android.content.Context;

import com.avoid.playtolearn.global.Session;
import com.avoid.playtolearn.model.SaveFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveFileHandler {
    private Context context;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;

    public SaveFileHandler(Context context) {
        this.context = context;
    }

    public void newGame() {
        Session.currentSaveFile = new SaveFile(this.context);
    }

    public void loadGame() {
        try {
            fileInputStream = context.openFileInput("saveFile");
            objectInputStream = new ObjectInputStream(fileInputStream);

            Session.currentSaveFile = (SaveFile) objectInputStream.readObject();

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

    public void saveGame() {
        try {
            fileOutputStream = context.openFileOutput("saveFile", Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Session.currentSaveFile);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveExists() {
        try {
            fileInputStream = context.openFileInput("saveFile");
            return fileInputStream != null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

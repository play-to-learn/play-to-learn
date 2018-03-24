package com.ivantha.playtolearn.utils;


import android.content.Context;

import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.model.SaveFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveHelper {
    private Context context;

    public SaveHelper(Context context) {
        this.context = context;
    }

    public void newGame() {
        Session.currentSaveFile = new SaveFile(this.context);
    }

    public void loadGame() {
        try {
            FileInputStream fileInputStream = context.openFileInput("saveFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Session.currentSaveFile = (SaveFile) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("saveFile", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Session.currentSaveFile);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveExists() {
        try {
            FileInputStream fileInputStream = context.openFileInput("saveFile");
            return fileInputStream != null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

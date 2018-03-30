package com.ivantha.playtolearn.common


import android.content.Context
import com.ivantha.playtolearn.model.SaveFile
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SaveHelper(private val context: Context) {

    fun newGame() {
        Session.currentSaveFile = SaveFile()
    }

    fun loadGame() {
        try {
            val fileInputStream = context.openFileInput("saveFile")
            val objectInputStream = ObjectInputStream(fileInputStream)

            Session.currentSaveFile = objectInputStream.readObject() as SaveFile

            objectInputStream.close()
            fileInputStream.close()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun saveGame() {
        try {
            val fileOutputStream = context.openFileOutput("saveFile", Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            objectOutputStream.writeObject(Session.currentSaveFile)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveExists(): Boolean {
        try {
            val fileInputStream = context.openFileInput("saveFile")
            return fileInputStream != null
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        }
    }
}

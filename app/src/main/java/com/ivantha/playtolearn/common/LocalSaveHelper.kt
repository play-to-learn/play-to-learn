package com.ivantha.playtolearn.common


import android.content.Context
import com.ivantha.playtolearn.model.SaveFile
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Deprecated (message = "LocalSaveHelper is replaced with FirebaseSaveHelper")
class LocalSaveHelper {

    var currentSaveFile: SaveFile? = null

    fun newGame() {
        currentSaveFile = SaveFile()
    }

    fun loadGame(context: Context) {
        try {
            val fileInputStream = context.openFileInput("saveFile")
            val objectInputStream = ObjectInputStream(fileInputStream)

            currentSaveFile = objectInputStream.readObject() as SaveFile

            objectInputStream.close()
            fileInputStream.close()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun saveGame(context: Context) {
        try {
            val fileOutputStream = context.openFileOutput("saveFile", Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            objectOutputStream.writeObject(currentSaveFile)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveExists(context: Context): Boolean {
        try {
            val fileInputStream = context.openFileInput("saveFile")
            return fileInputStream != null
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        }
    }
}

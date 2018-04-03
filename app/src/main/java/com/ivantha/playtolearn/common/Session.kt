package com.ivantha.playtolearn.common

import com.ivantha.playtolearn.model.SaveFile

object Session {

    var saveFile: SaveFile? = null

    const val COLUMN_COUNT = 6
    const val ROW_COUNT = 10

    val settingsHelper: SettingsHelper = SettingsHelper()

}
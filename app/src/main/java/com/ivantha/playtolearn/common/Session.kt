package com.ivantha.playtolearn.common

import com.ivantha.playtolearn.model.SaveFile
import com.ivantha.playtolearn.model.Settings
import com.ivantha.playtolearn.widget.BoardTileLayout
import java.util.*

object Session {
    // SaveHelper files
    var saveHelper: SaveHelper? = null
    var currentSaveFile: SaveFile? = null

    // SettingsHelper
    var settingsHelper: SettingsHelper? = null
    var currentSettings: Settings? = null

    // Device
    var SCREEN_WIDTH: Int = 0
    var SCREEN_HEIGHT: Int = 0

    // Board
    var COLUMN_COUNT = 6
    var ROW_COUNT = 10
    var boardLayoutGrid = ArrayList<ArrayList<BoardTileLayout>>()

}
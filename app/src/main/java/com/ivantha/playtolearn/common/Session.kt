package com.ivantha.playtolearn.common

import com.ivantha.playtolearn.widget.BoardTileLayout
import java.util.*

object Session {
    // LocalSaveHelper files
    var localSaveHelper: LocalSaveHelper? = null

    // SettingsHelper
    var settingsHelper: SettingsHelper? = null

    // Device
    var SCREEN_WIDTH: Int = 0
    var SCREEN_HEIGHT: Int = 0

    // Board
    var COLUMN_COUNT = 6
    var ROW_COUNT = 10
    var boardLayoutGrid = ArrayList<ArrayList<BoardTileLayout>>()

}
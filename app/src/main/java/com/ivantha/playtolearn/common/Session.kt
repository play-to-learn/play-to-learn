package com.ivantha.playtolearn.common

import com.ivantha.playtolearn.model.Board
import com.ivantha.playtolearn.model.Profile
import com.ivantha.playtolearn.widget.BoardTileLayout
import java.util.*

object Session {

    var profile: Profile? = null

    var currentLevel: Int = 1
    var score: Int = 0

    var board: Board? = null

    var COLUMN_COUNT = 6
    var ROW_COUNT = 10
    var boardLayoutGrid = ArrayList<ArrayList<BoardTileLayout>>()

    val settingsHelper: SettingsHelper = SettingsHelper()

}
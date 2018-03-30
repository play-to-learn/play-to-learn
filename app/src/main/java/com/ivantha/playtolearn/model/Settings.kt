package com.ivantha.playtolearn.model

import java.io.Serializable

class Settings : Serializable {
    val isSoundsOnDefault: Boolean = true
    var isSoundsOn: Boolean = true
    val isMusicOnDefault: Boolean = true
    var isMusicOn: Boolean = true
    val isHintsOnDefault: Boolean = true
    var isHintsOn: Boolean = true
}

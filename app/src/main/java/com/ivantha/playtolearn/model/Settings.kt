package com.ivantha.playtolearn.model

import java.io.Serializable

class Settings : Serializable {
    val soundsOnDefault: Boolean = true
    var soundsOn: Boolean = true
    val musicOnDefault: Boolean = true
    var musicOn: Boolean = true
    val hintsOnDefault: Boolean = true
    var hintsOn: Boolean = true
}
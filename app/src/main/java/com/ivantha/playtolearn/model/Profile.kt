package com.ivantha.playtolearn.model

import java.io.Serializable

class Profile : Serializable {
    var firstName: String? = "John"
    var lastName: String? = "Doe"

    var completedLevels: ArrayList<Level> = ArrayList()
    var currentLevel: Level = Level(1)

    var score: Int = 0
}

package com.ivantha.playtolearn.model

class Level {
    var id: Int = 0
    var enabled: Boolean = false
    var score: Int = 0

    constructor()

    constructor(id: Int) {
        this.id = id
    }
}

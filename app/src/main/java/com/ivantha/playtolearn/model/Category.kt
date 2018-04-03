package com.ivantha.playtolearn.model

class Category {
    var id: String? = null
    var name: String? = null

    constructor()

    constructor(id: String) {
        this.id = id
    }

    constructor(id: String, name: String) {
        this.id = id
        this.name = name
    }

}

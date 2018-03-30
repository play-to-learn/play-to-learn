package com.ivantha.playtolearn.model

import java.io.Serializable

class Profile : Serializable {
    var id: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var score: Int = 0

    init {
        this.id = 0
        this.firstName = "John"
        this.lastName = "Doe"
        this.score = 0
    }
}

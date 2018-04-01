package com.ivantha.playtolearn.model

import java.io.Serializable

class Profile(var uid: String) : Serializable {
    var firstName: String? = "John"
    var lastName: String? = "Doe"
}

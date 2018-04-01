package com.ivantha.playtolearn.model

import java.io.Serializable

class Level(var id: Int) : Serializable{
    var enabled: Boolean = false
    var questions: ArrayList<Question> = ArrayList()

}

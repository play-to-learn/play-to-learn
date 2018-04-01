package com.ivantha.playtolearn.model

class Level(var id: Int){
    var enabled: Boolean = false
    var questions: ArrayList<Question> = ArrayList()

}

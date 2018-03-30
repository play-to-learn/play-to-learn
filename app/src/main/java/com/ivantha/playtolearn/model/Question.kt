package com.ivantha.playtolearn.model

import java.io.Serializable

class Question : Serializable {
    var id: String? = null
    var title: String? = null
    var description: String? = null
    var answer: Int = 0

    enum class Result {
        CORRECT,
        WRONG,
        INVALID
    }
}
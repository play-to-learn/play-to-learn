package com.ivantha.playtolearn.model

class Question {
    var id: String? = null
    var title: String? = null
    var description: String? = null
    var category: Category? = null
    var answer: Int = 0
    var correctPoints: Int = 10
    var wrongPoints: Int = -5
}
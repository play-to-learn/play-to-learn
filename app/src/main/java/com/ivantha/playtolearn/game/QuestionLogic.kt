package com.ivantha.playtolearn.game

import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Question
import java.util.*

object QuestionLogic {

    fun getRandomQuestion(): Question{
        return Session.questions[random(0, Session.questions.size - 1)]
    }

    private fun random(start: Int, end: Int): Int{
        return Random().nextInt(end + 1 - start) + start
    }
}
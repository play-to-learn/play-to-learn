package com.ivantha.playtolearn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.model.Level

import java.util.ArrayList

class LevelsActivity : AppCompatActivity() {
    private val levels = ArrayList<Level>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
    }
}

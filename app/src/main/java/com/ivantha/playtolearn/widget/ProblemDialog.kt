package com.ivantha.playtolearn.widget

import android.app.Dialog
import android.content.Context

import com.ivantha.playtolearn.R
import kotlinx.android.synthetic.main.dialog_problem.*

class ProblemDialog(context: Context) : Dialog(context, R.style.ProblemDialog) {

    init {
        this.setContentView(R.layout.dialog_problem)
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setDescription(description: String) {
        descriptionTextView.text = description
    }
}

package com.ivantha.playtolearn.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.ivantha.playtolearn.common.Font


class LabelTextView : android.support.v7.widget.AppCompatTextView {

    init {
        applyCustomFont()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun applyCustomFont() {
        val customFont = Font.getTypeface(Font.ACTION_MAN_BOLD_FONT, context)
        typeface = customFont
    }
}

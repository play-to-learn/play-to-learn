package com.ivantha.playtolearn.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet

import com.ivantha.playtolearn.R

class WindowTitle : android.support.v7.widget.AppCompatButton {

    init {
        applyCustomShape()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(context, attrs, defStyleAttrs)

    private fun applyCustomShape() {
        setBackgroundResource(R.drawable.common_window_title)
        typeface = Typeface.createFromAsset(context.assets, "fonts/zantroke.otf")
        textSize = 16f
        setTextColor(Color.parseColor("#874f21"))
    }

}

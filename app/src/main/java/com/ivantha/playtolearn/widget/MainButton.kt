package com.ivantha.playtolearn.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet

import com.ivantha.playtolearn.R

class MainButton : AppCompatButton {

    init {
        setBackgroundResource(R.drawable.common_main_button)
        typeface = Typeface.createFromAsset(context.assets, "fonts/zantroke.otf")
        textSize = 18f
        setTextColor(Color.parseColor("#874f21"))
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(context, attrs, defStyleAttrs)

}

package com.ivantha.playtolearn.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.SwitchCompat
import android.util.AttributeSet

import com.ivantha.playtolearn.R

class Switch : SwitchCompat {

    init {
        applyCustomShape()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(context, attrs, defStyleAttrs)

    private fun applyCustomShape() {
        //        setBackgroundResource(R.drawable.common_switch);
        setTrackResource(R.drawable.common_switch_track)
        setThumbResource(R.drawable.common_switch_thumb)

        typeface = Typeface.createFromAsset(context.assets, "fonts/zantroke.otf")
        textSize = 16f
        setTextColor(Color.parseColor("#e7a149"))
    }
}

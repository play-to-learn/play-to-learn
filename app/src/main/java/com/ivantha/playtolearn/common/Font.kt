package com.ivantha.playtolearn.common

import android.content.Context
import android.graphics.Typeface
import java.util.*

object Font {
    var ACTION_MAN_BOLD_FONT = "fonts/Action_Man_Bold.ttf"

    private val fontCache = Hashtable<String, Typeface>()

    fun getTypeface(name: String, context: Context): Typeface? {
        var typeface: Typeface? = fontCache[name]
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.assets, name)
            } catch (e: Exception) {
                return null
            }

            fontCache[name] = typeface
        }
        return typeface
    }
}

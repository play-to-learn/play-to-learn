package com.ivantha.playtolearn.common;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class Font {
    public static String ACTION_MAN_BOLD_FONT = "fonts/Action_Man_Bold.ttf";

    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    public static Typeface getTypeface(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}

package com.avoid.playtolearn.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.avoid.playtolearn.R;

public class WindowTitle extends android.support.v7.widget.AppCompatButton {
    public WindowTitle(Context context){
        super(context);
        applyCustomShape(context);
    }

    public WindowTitle(Context context, AttributeSet attrs){
        super(context, attrs);
        applyCustomShape(context);
    }

    public WindowTitle(Context context, AttributeSet attrs, int defStyleAttrs){
        super(context, attrs, defStyleAttrs);
        applyCustomShape(context);
    }

    private void applyCustomShape(Context context){
        setBackgroundResource(R.drawable.common_window_title);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/zantroke.otf"));
        setTextSize(16);
        setTextColor(Color.parseColor("#874f21"));
    }

}

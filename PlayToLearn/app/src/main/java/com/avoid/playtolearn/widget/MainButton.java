package com.avoid.playtolearn.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.avoid.playtolearn.R;

public class MainButton extends android.support.v7.widget.AppCompatButton {
    public MainButton(Context context){
        super(context);
        applyCustomShape(context);
    }

    public MainButton(Context context, AttributeSet attrs){
        super(context, attrs);
        applyCustomShape(context);
    }

    public MainButton(Context context, AttributeSet attrs, int defStyleAttrs){
        super(context, attrs, defStyleAttrs);
        applyCustomShape(context);
    }

    private void applyCustomShape(Context context){
        setBackgroundResource(R.drawable.common_main_button);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/zantroke.otf"));
        setTextSize(18);
        setTextColor(Color.parseColor("#874f21"));
    }

}

package com.avoid.playtolearn.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.avoid.playtolearn.R;

public class Switch extends SwitchCompat {
    public Switch(Context context){
        super(context);
        applyCustomShape(context);
    }

    public Switch(Context context, AttributeSet attrs){
        super(context, attrs);
        applyCustomShape(context);
    }

    public Switch(Context context, AttributeSet attrs, int defStyleAttrs){
        super(context, attrs, defStyleAttrs);
        applyCustomShape(context);
    }

    private void applyCustomShape(Context context){
//        setBackgroundResource(R.drawable.common_switch);
        setTrackResource(R.drawable.common_switch_track);
        setThumbResource(R.drawable.common_switch_thumb);

        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/zantroke.otf"));
        setTextSize(16);
        setTextColor(Color.parseColor("#e7a149"));
    }
}

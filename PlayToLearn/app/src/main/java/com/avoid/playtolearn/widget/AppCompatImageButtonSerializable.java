package com.avoid.playtolearn.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.avoid.playtolearn.common.Session;

public class AppCompatImageButtonSerializable extends AppCompatImageButton{
    public AppCompatImageButtonSerializable() {
        super(Session.currentContext);
    }

    public AppCompatImageButtonSerializable(Context context) {
        super(context);
    }

    public AppCompatImageButtonSerializable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppCompatImageButtonSerializable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

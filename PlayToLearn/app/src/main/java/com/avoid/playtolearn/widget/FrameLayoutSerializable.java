package com.avoid.playtolearn.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.avoid.playtolearn.common.Session;

public class FrameLayoutSerializable extends FrameLayout{
    public FrameLayoutSerializable() {
        super(Session.currentContext);
    }

    public FrameLayoutSerializable(@NonNull Context context) {
        super(context);
    }

    public FrameLayoutSerializable(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayoutSerializable(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

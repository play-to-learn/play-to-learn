package com.avoid.playtolearn.listener;

import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;

public class BoardTouchListener implements View.OnTouchListener, Serializable {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        } else {
            return false;
        }
    }
}

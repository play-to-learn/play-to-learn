package com.avoid.playtolearn.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.avoid.playtolearn.listener.BoardDragListener;
import com.avoid.playtolearn.listener.BoardTouchListener;

public class BoardTileButton extends AppCompatImageButton {
    private HexButtonDrawable drawable;

    public BoardTileButton(Context context) {
        super(context);
        constructorWork(context);
    }

    public BoardTileButton(Context context, AttributeSet attrs) {
        super(context);
        constructorWork(context);
    }

    public BoardTileButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context);
        constructorWork(context);
    }

    private void constructorWork(Context context){
        this.setOnTouchListener(new BoardTouchListener());
        this.setOnDragListener(new BoardDragListener());
        applyCustomLayout(context);
        applyCustomShape(context);
    }

    public void setIcon(int iconDrawable) {
        drawable.setIcon(iconDrawable);
    }

    private void applyCustomShape(Context context) {
        drawable = new HexButtonDrawable(context);
        setBackground(drawable);
    }

    private void applyCustomLayout(Context context){
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT);
//        params.addRule(FrameLayout.CENTER_HORIZONTAL, FrameLayout.TRUE);
//        params.
//        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
//        this.setLayoutParams(params);
    }


}

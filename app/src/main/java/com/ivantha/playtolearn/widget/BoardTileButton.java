package com.ivantha.playtolearn.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;

import com.ivantha.playtolearn.R;
import com.ivantha.playtolearn.listener.BoardDragListener;
import com.ivantha.playtolearn.listener.BoardTouchListener;

public class BoardTileButton extends AppCompatImageButton {
    private HexButtonDrawable drawable;

    public BoardTileButton(Context context) {
        super(context);

        this.setOnTouchListener(new BoardTouchListener(context));
        this.setOnDragListener(new BoardDragListener());
        applyCustomLayout(context);
        applyCustomShape(context);
    }

    public void setIcon(int iconDrawable) {
        drawable.setIcon(iconDrawable);
    }

    private void applyCustomShape(Context context) {
        setBackgroundResource(R.drawable.sprite);
//        drawable = new HexButtonDrawable(context);
//        setBackground(drawable);
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

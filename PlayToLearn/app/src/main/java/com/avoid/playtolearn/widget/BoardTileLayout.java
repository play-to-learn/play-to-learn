package com.avoid.playtolearn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.listener.BoardDragListener;

import java.io.Serializable;


public class BoardTileLayout extends FrameLayoutSerializable implements Serializable{
    public BoardTileLayout() {
    }

    public BoardTileLayout(Context context) {
        super(context);
        this.setOnDragListener(new BoardDragListener());
        applyCustomDesign(context);
    }

    public BoardTileLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnDragListener(new BoardDragListener());
        applyCustomDesign(context);
    }

    public BoardTileLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnDragListener(new BoardDragListener());
        applyCustomDesign(context);
    }

    public void applyCustomDesign(Context context) {
        setBackgroundResource(R.color.colorAccent);
        int margin_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                context.getResources().getDisplayMetrics());
        int size_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70,
                context.getResources().getDisplayMetrics());

        LayoutParams params = new LayoutParams(size_px, size_px);
        params.setMargins(margin_px, margin_px, margin_px, margin_px);
        this.setLayoutParams(params);
    }
}

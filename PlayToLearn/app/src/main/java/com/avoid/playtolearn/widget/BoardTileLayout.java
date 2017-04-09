package com.avoid.playtolearn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.listener.BoardDragListener;

import java.io.Serializable;

public class BoardTileLayout extends FrameLayoutSerializable implements Serializable{
    private int row;
    private int column;
    private int state;  // 0:NotPassed 1:Passed 2:ProblemPoint

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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setState(int state){
        this.state = state;
        if (state == 0){
            setBackgroundResource(R.drawable.board_tile_background_0);
        } else if (state == 1){
            setBackgroundResource(R.drawable.board_tile_background_1);
        } else if (state == 2){
            setBackgroundResource(R.drawable.board_tile_background_2);
        }
    }

    public int getState(){
        return this.state;
    }


    public void applyCustomDesign(Context context) {
        setState(1);
        int margin_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                context.getResources().getDisplayMetrics());
        int size_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70,
                context.getResources().getDisplayMetrics());

        LayoutParams params = new LayoutParams(size_px, size_px);
        params.setMargins(margin_px, margin_px, margin_px, margin_px);
        this.setLayoutParams(params);
    }
}
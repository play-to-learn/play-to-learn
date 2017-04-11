package com.avoid.playtolearn.widget;

import android.content.Context;
import android.widget.FrameLayout;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.common.Global;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.listener.BoardDragListener;
import com.avoid.playtolearn.model.BoardTile;
import com.avoid.playtolearn.model.BoardTileState;

public class BoardTileLayout extends FrameLayout {
    private BoardTile boardTile;

    public BoardTileLayout(Context context, BoardTile boardTile) {
        super(context);
        this.boardTile = boardTile;
        this.setOnDragListener(new BoardDragListener());

        applyCustomDesign();

        if(boardTile.getBoardTileState() == BoardTileState.CURRENT){
            this.addView(new BoardTileButton(context));
        }
    }

    public void setBoardTileState(BoardTileState boardTileState) {
        this.boardTile.setBoardTileState(boardTileState);
        this.refreshState();
    }

    public void refreshState(){
        BoardTileState boardTileState = boardTile.getBoardTileState();
        switch (boardTileState){
            case NOT_VISITED:
                setBackgroundResource(R.drawable.board_tile_background_not_visited);
                break;
            case VISITED:
                setBackgroundResource(R.drawable.board_tile_background_visited);
                break;
            case CURRENT:
                setBackgroundResource(R.drawable.board_tile_background_current);
                break;
            case CORRECT_ANSWER:
                setBackgroundResource(R.drawable.board_tile_background_correct);
                break;
            case WRONG_ANSWER:
                setBackgroundResource(R.drawable.board_tile_background_wrong);
                break;
            default:
                setBackgroundResource(R.drawable.board_tile_background_not_visited);
                break;
        }
    }

    public void applyCustomDesign() {
        int effectiveWidth = (int) (Session.SCREEN_WIDTH * 0.95);

//        int size_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
//                context.getResources().getDisplayMetrics());
        int size_px = (int) (effectiveWidth / (Global.COLUMN_COUNT + 1));

        //        int margin_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
//                context.getResources().getDisplayMetrics());
        int margin_px = (effectiveWidth - (size_px * Global.COLUMN_COUNT)) / (Global.COLUMN_COUNT * 2);

        LayoutParams params = new LayoutParams(size_px, size_px);
        params.setMargins(margin_px, margin_px, margin_px, margin_px);
        this.setLayoutParams(params);

        refreshState();
    }
}
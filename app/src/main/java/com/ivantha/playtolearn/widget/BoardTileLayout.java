package com.ivantha.playtolearn.widget;

import android.content.Context;
import android.util.TypedValue;
import android.widget.FrameLayout;

import com.ivantha.playtolearn.R;
import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.listener.BoardDragListener;
import com.ivantha.playtolearn.listener.BoardTouchListener;
import com.ivantha.playtolearn.model.BoardTile;

public class BoardTileLayout extends FrameLayout {
    private BoardTile boardTile;

    public BoardTileLayout(Context context, BoardTile boardTile) {
        super(context);
        this.boardTile = boardTile;

        this.setOnTouchListener(new BoardTouchListener(context));
        this.setOnDragListener(new BoardDragListener());

        applyCustomDesign(context);

        if(boardTile.getBoardTileState() == BoardTile.BoardTileState.CURRENT){
            this.addView(new BoardTileButton(context));
        }
    }

    public BoardTile getBoardTile() {
        return boardTile;
    }

    public void setBoardTileState(BoardTile.BoardTileState boardTileState) {
        this.boardTile.setBoardTileState(boardTileState);
        this.refreshStateChanges();
    }

    public int getRow(){
        return this.boardTile.getRow();
    }

    public int getColumn(){
        return  this.boardTile.getColumn();
    }

    public void generateQuestion(){
//        this.boardTile.setQuestion(QuestionCache.getQuestionArrayList().get(1));
    }

    public int getScore(){
        //Should fix
        return 1;
    }

    public void refreshStateChanges(){
        BoardTile.BoardTileState boardTileState = boardTile.getBoardTileState();
        switch (boardTileState){
            case NOT_VISITED:
                setBackgroundResource(R.drawable.board_tile_background_not_visited0);
//                setBackgroundColor(Color.parseColor("#00ff00"));
                break;
            case VISITED:
                setBackgroundResource(R.drawable.board_tile_background_visited);
                break;
            case CURRENT:
                setBackgroundResource(R.color.colorTransparent);
                break;
            case CORRECT_ANSWER:
                setBackgroundResource(R.drawable.board_tile_background_correct);
                break;
            case WRONG_ANSWER:
                setBackgroundResource(R.drawable.board_tile_background_wrong);
                break;
            default:
                setBackgroundResource(R.drawable.board_tile_background_not_visited0);
//                setBackgroundColor(Color.parseColor("#00ff00"));
                break;
        }
    }

    public void applyCustomDesign(Context context) {
        int outer_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
        int effectiveWidth = Session.SCREEN_WIDTH;

//        int size_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
//                context.getResources().getDisplayMetrics());
        int size_px = effectiveWidth / (Session.COLUMN_COUNT + 1);

        //        int margin_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
//                context.getResources().getDisplayMetrics());
        int margin_px = (effectiveWidth - (size_px * Session.COLUMN_COUNT)) / (Session.COLUMN_COUNT * 2);

        LayoutParams params = new LayoutParams(size_px, size_px);
        params.setMargins(margin_px, margin_px, margin_px, margin_px);
        this.setLayoutParams(params);

        refreshStateChanges();
    }
}
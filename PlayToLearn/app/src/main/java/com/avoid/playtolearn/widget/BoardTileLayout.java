package com.avoid.playtolearn.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.avoid.playtolearn.R;
import com.avoid.playtolearn.activity.ProfileActivity;
import com.avoid.playtolearn.common.Global;
import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.game.QuestionLogic;
import com.avoid.playtolearn.listener.BoardDragListener;
import com.avoid.playtolearn.listener.BoardTouchListener;
import com.avoid.playtolearn.model.BoardTile;
import com.avoid.playtolearn.model.BoardTileState;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class BoardTileLayout extends FrameLayout {
    private BoardTile boardTile;

    public BoardTileLayout(Context context, BoardTile boardTile) {
        super(context);
        this.boardTile = boardTile;

        this.setOnTouchListener(new BoardTouchListener(context));
        this.setOnDragListener(new BoardDragListener());

        applyCustomDesign(context);

        if(boardTile.getBoardTileState() == BoardTileState.CURRENT){
            this.addView(new BoardTileButton(context));
        }
    }

    public BoardTile getBoardTile() {
        return boardTile;
    }

    public void setBoardTileState(BoardTileState boardTileState) {
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
        this.boardTile.setQuestion(QuestionLogic.getRandomQuestion());
    }

    public int getScore(){
        return this.boardTile.getQuestion().getDifficulty().getScore();
    }

    public void refreshStateChanges(){
        BoardTileState boardTileState = boardTile.getBoardTileState();
        switch (boardTileState){
            case NOT_VISITED:
                setBackgroundResource(R.drawable.board_tile_background_not_visited0);
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
                break;
        }
    }

    public void applyCustomDesign(Context context) {
        int outer_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
        int effectiveWidth = (int) (Session.SCREEN_WIDTH);

//        int size_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
//                context.getResources().getDisplayMetrics());
        int size_px = (int) (effectiveWidth / (Global.COLUMN_COUNT + 1));

        //        int margin_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
//                context.getResources().getDisplayMetrics());
        int margin_px = (effectiveWidth - (size_px * Global.COLUMN_COUNT)) / (Global.COLUMN_COUNT * 2);

        LayoutParams params = new LayoutParams(size_px, size_px);
        params.setMargins(margin_px, margin_px, margin_px, margin_px);
        this.setLayoutParams(params);

        refreshStateChanges();
    }
}
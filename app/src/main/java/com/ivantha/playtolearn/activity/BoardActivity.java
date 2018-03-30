package com.ivantha.playtolearn.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ivantha.playtolearn.R;
import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.listener.BoardDragListener;
import com.ivantha.playtolearn.listener.BoardTouchListener;
import com.ivantha.playtolearn.model.BoardTile;
import com.ivantha.playtolearn.model.Question;
import com.ivantha.playtolearn.common.Tuple;
import com.ivantha.playtolearn.widget.HexButtonDrawable;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    private LinearLayout tileGridLayout;

    public static Tuple<Question.Result, ArrayList<Tuple<Integer, Integer>>> verifyMove(BoardTile preBoardTile, BoardTile newBoardTile){
        int preX = preBoardTile.getColumn();
        int preY = preBoardTile.getRow();
        int newX = newBoardTile.getColumn();
        int newY = newBoardTile.getRow();

        if(((newX < preX) && (newY <= preY)) || ((newX == preX) && (newY <= preY))){
            return new Tuple<>(Question.Result.INVALID, null);
        }else{
            int difference = 0;
            ArrayList<Tuple<Integer, Integer>> visitedTileArrayList = new ArrayList<>();
            if(newY == preY){
                difference += (newX - preX);

                for(int i = preX + 1; i < newX; i++){
                    visitedTileArrayList.add(new Tuple<>(i, preY));
                }
            }else if(newY == preY + 1){
                difference += (Session.COLUMN_COUNT - preX - 1) + (newX + 1);

                for(int i = preX + 1; i < Session.COLUMN_COUNT; i++){
                    visitedTileArrayList.add(new Tuple<>(i, preY));
                }
                for(int i = 0; i < newX; i++){
                    visitedTileArrayList.add(new Tuple<>(i, newY));
                }
            }else {
                difference += (Session.COLUMN_COUNT - preX - 1) + (newX + 1);
                difference += ((newY - preY - 1) * Session.COLUMN_COUNT);

                for(int i = preX + 1; i < Session.COLUMN_COUNT; i++){
                    visitedTileArrayList.add(new Tuple<>(i, preY));
                }
                for(int i = 0; i < newX; i++){
                    visitedTileArrayList.add(new Tuple<>(i, newY));
                }
                for(int i = preY + 1; i < newY; i++){
                    for(int j = 0; j < Session.COLUMN_COUNT; j++){
                        visitedTileArrayList.add(new Tuple<>(j, i));
                    }
                }
            }

            if (preBoardTile.getQuestion().getAnswer() == difference){
                return new Tuple<>(Question.Result.CORRECT, visitedTileArrayList);
            }else{
                return new Tuple<>(Question.Result.WRONG, visitedTileArrayList);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        tileGridLayout = findViewById(R.id.tile_grid_layout);

        createBoard();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Session.saveHelper.saveGame();
    }

    private void createBoard() {
        for (ArrayList<BoardTile> tileArrayList : Session.currentSaveFile.getBoard().getTileGrid()) {
            ArrayList<BoardTileLayout> layoutArrayList = new ArrayList<>();
            LinearLayout tileColumnLayout = new LinearLayout(BoardActivity.this);
            tileColumnLayout.setOrientation(LinearLayout.VERTICAL);
            for (BoardTile boardTile : tileArrayList) {
                BoardTileLayout boardTileLayout = new BoardTileLayout(BoardActivity.this, boardTile);
                tileColumnLayout.addView(boardTileLayout);
                layoutArrayList.add(boardTileLayout);
            }
            tileGridLayout.addView(tileColumnLayout);
            Session.boardLayoutGrid.add(layoutArrayList);
        }
    }

    public static class BoardTileButton extends AppCompatImageButton {
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

    public static class BoardTileLayout extends FrameLayout {
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
}

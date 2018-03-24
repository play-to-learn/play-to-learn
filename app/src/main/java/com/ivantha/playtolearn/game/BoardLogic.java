package com.ivantha.playtolearn.game;

import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.model.BoardTile;
import com.ivantha.playtolearn.model.Question;
import com.ivantha.playtolearn.utils.Tuple;

import java.util.ArrayList;

public class BoardLogic {
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
}

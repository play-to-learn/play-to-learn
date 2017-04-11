package com.avoid.playtolearn.game;

import com.avoid.playtolearn.common.Global;
import com.avoid.playtolearn.model.BoardTile;
import com.avoid.playtolearn.model.Result;

public class BoardLogic {
    public static Result verifyMove(BoardTile preBoardTile, BoardTile newBoardTile){
        int preX = preBoardTile.getColumn();
        int preY = preBoardTile.getRow();
        int newX = newBoardTile.getColumn();
        int newY = newBoardTile.getRow();

        if((newX < preX) && (newY <= preY)){
            return Result.INVALID;
        }else{
            int difference = 0;
            if(newY == preY){
                difference += (newX - preX);
            }else if(newY == preY + 1){
                difference += (Global.COLUMN_COUNT - preX - 1) + (newX + 1);
            }else {
                difference += (Global.COLUMN_COUNT - preX - 1) + (newX);
                difference += ((newY - preY) * Global.COLUMN_COUNT);
            }

            if (preBoardTile.getQuestion().getAnswer() == difference){
                return Result.CORRECT;
            }else{
                return Result.WRONG;
            }
        }
    }
}

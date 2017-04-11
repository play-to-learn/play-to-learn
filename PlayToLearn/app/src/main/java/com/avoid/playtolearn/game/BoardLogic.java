package com.avoid.playtolearn.game;

import com.avoid.playtolearn.model.BoardTile;
import com.avoid.playtolearn.model.Result;

public class BoardLogic {
    public static Result verifyMove(BoardTile preBoardTile, BoardTile newBoardTile){
        int preX = preBoardTile.getColumn();
        int preY = preBoardTile.getRow();
        int newX = newBoardTile.getColumn();
        int newY = newBoardTile.getRow();

        

        return Result.CORRECT;
    }
}

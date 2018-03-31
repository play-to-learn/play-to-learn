package com.ivantha.playtolearn.game

import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.common.Tuple
import com.ivantha.playtolearn.model.BoardTile
import com.ivantha.playtolearn.model.Result

import java.util.ArrayList

object MovementLogic {

    fun verifyMove(preBoardTile: BoardTile, newBoardTile: BoardTile): Tuple<Result, ArrayList<Tuple<Int, Int>>> {
        val preX = preBoardTile.column
        val preY = preBoardTile.row
        val newX = newBoardTile.column
        val newY = newBoardTile.row

        if (newX < preX && newY <= preY || newX == preX && newY <= preY) {
            return Tuple<Result, ArrayList<Tuple<Int, Int>>>(Result.INVALID, null)
        } else {
            var difference = 0
            val visitedTileArrayList = ArrayList<Tuple<Int, Int>>()
            if (newY == preY) {
                difference += newX - preX

                for (i in preX + 1 until newX) {
                    visitedTileArrayList.add(Tuple(i, preY))
                }
            } else if (newY == preY + 1) {
                difference += Session.COLUMN_COUNT - preX - 1 + (newX + 1)

                for (i in preX + 1 until Session.COLUMN_COUNT) {
                    visitedTileArrayList.add(Tuple(i, preY))
                }
                for (i in 0 until newX) {
                    visitedTileArrayList.add(Tuple(i, newY))
                }
            } else {
                difference += Session.COLUMN_COUNT - preX - 1 + (newX + 1)
                difference += (newY - preY - 1) * Session.COLUMN_COUNT

                for (i in preX + 1 until Session.COLUMN_COUNT) {
                    visitedTileArrayList.add(Tuple(i, preY))
                }
                for (i in 0 until newX) {
                    visitedTileArrayList.add(Tuple(i, newY))
                }
                for (i in preY + 1 until newY) {
                    for (j in 0 until Session.COLUMN_COUNT) {
                        visitedTileArrayList.add(Tuple(j, i))
                    }
                }
            }

            return if (preBoardTile.question!!.answer == difference) {
                Tuple(Result.CORRECT, visitedTileArrayList)
            } else {
                Tuple(Result.WRONG, visitedTileArrayList)
            }
        }
    }
}

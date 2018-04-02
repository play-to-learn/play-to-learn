package com.ivantha.playtolearn.game

import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.common.Tuple
import com.ivantha.playtolearn.model.Tile
import com.ivantha.playtolearn.model.Result

import java.util.ArrayList

object MovementLogic {

    fun verifyMove(preTile: Tile, newTile: Tile): Tuple<Result, ArrayList<Tuple<Int, Int>>> {
        val preX = preTile.column
        val preY = preTile.row
        val newX = newTile.column
        val newY = newTile.row

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

            return if (preTile.question!!.answer == difference) {
                Tuple(Result.CORRECT, visitedTileArrayList)
            } else {
                Tuple(Result.WRONG, visitedTileArrayList)
            }
        }
    }
}

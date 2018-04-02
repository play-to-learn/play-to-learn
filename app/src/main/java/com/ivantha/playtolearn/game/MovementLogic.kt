package com.ivantha.playtolearn.game

import com.ivantha.playtolearn.model.Position
import com.ivantha.playtolearn.model.Question
import com.ivantha.playtolearn.model.Result

object MovementLogic {

    fun verifyMove(preX: Int, preY: Int, newX: Int, newY: Int, preQuestion: Question, colCount: Int): Pair<Result, ArrayList<Position>?> {
        if (newX < preX && newY <= preY || newX == preX && newY <= preY) {
            return Pair(Result.INVALID, null)
        } else {
            var difference = 0
            val visitedTileList = ArrayList<Position>()
            when (newY) {
                preY -> {
                    difference += newX - preX

                    for (i in preX + 1 until newX) {
                        visitedTileList.add(Position(i, preY))
                    }
                }
                preY + 1 -> {
                    difference += colCount - preX - 1 + (newX + 1)

                    for (i in preX + 1 until colCount) {
                        visitedTileList.add(Position(i, preY))
                    }
                    for (i in 0 until newX) {
                        visitedTileList.add(Position(i, newY))
                    }
                }
                else -> {
                    difference += colCount - preX - 1 + (newX + 1)
                    difference += (newY - preY - 1) * colCount

                    for (i in preX + 1 until colCount) {
                        visitedTileList.add(Position(i, preY))
                    }
                    for (i in 0 until newX) {
                        visitedTileList.add(Position(i, newY))
                    }
                    for (i in preY + 1 until newY) {
                        for (j in 0 until colCount) {
                            visitedTileList.add(Position(j, i))
                        }
                    }
                }
            }

            return if (preQuestion.answer == difference) {
                Pair(Result.CORRECT, visitedTileList)
            } else {
                Pair(Result.WRONG, visitedTileList)
            }
        }
    }
}

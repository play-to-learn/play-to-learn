package com.avoid.playtolearn.util;

import com.avoid.playtolearn.model.Category;
import com.avoid.playtolearn.model.Difficulty;

public class Factory {
    public static Category getCategoryById(int id){
        switch (id){
            case 1:
                return Category.Loops;
            case 2:
                return Category.Conditional_Structures;
            default:
                return Category.Loops;
        }
    }

    public static Difficulty getDifficultyById(int id){
        switch (id){
            case 1:
                return Difficulty.Very_easy;
            case 2:
                return Difficulty.Easy;
            case 3:
                return Difficulty.Medium;
            case 4:
                return Difficulty.Hard;
            case 5:
                return Difficulty.Very_hard;
            case 6:
                return Difficulty.Expert;
            default:
                return Difficulty.Very_easy;
        }
    }
}

package com.avoid.playtolearn.game;

import com.avoid.playtolearn.cache.QuestionCache;
import com.avoid.playtolearn.model.Category;
import com.avoid.playtolearn.model.Difficulty;
import com.avoid.playtolearn.model.Question;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    public static Category getRandomCategory() {
        Random random = new Random();
        int i = random.nextInt(2);

        if (i == 0) {
            return Category.Loops;
        } else {
            return Category.Conditional_Structures;
        }
    }

    public static Difficulty getRandomDifficulty() {
        Random random = new Random();
        int i = random.nextInt(2);

        if (i == 0) {
            return Difficulty.Very_easy;
        } else if (i == 1) {
            return Difficulty.Easy;
        } else if (i == 2) {
            return Difficulty.Medium;
        } else if (i == 3) {
            return Difficulty.Hard;
        } else if (i == 4) {
            return Difficulty.Very_hard;
        } else {
            return Difficulty.Expert;
        }
    }

    public static Question getRandomQuestion(){
        Category category = GameLogic.getRandomCategory();
        Difficulty difficulty = GameLogic.getRandomDifficulty();

        ArrayList<Question> tempQuestionList = new ArrayList<>();
        for(Question question: QuestionCache.getQuestionArrayList()){
            if(question.getCategory() == category && question.getDifficulty() == difficulty){
                tempQuestionList.add(question);
            }
        }

        Random random = new Random();
        int i = random.nextInt(tempQuestionList.size());
        return tempQuestionList.get(i);
    }
}

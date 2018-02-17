package com.avoid.playtolearn.utils;

import android.content.Context;

import com.avoid.playtolearn.game.QuestionCache;
import com.avoid.playtolearn.database.cursor.QuestionSQL;
import com.avoid.playtolearn.widgets.ProblemDialog;

public class App {
    public static void showQuestionDialog(Context context, String title, String description){
        ProblemDialog problemDialog = new ProblemDialog(context);
        problemDialog.setTitle(title);
        problemDialog.setDescription(description);
        problemDialog.show();
    }

    public static void loadQuestions(){
        QuestionSQL questionSQL = new QuestionSQL();
        QuestionCache.setQuestionArrayList(questionSQL.getQuestions());
    }

    public static void applySoundSettings(Context context){

    }

    public static void applyMusicSettings(Context context){

    }
}

package com.avoid.playtolearn.game;

import android.content.Context;
import android.util.Log;

import com.avoid.playtolearn.cache.QuestionCache;
import com.avoid.playtolearn.database.cursor.QuestionSQL;
import com.avoid.playtolearn.widget.ProblemDialog;

public class Controller {
    public static void showQuestionDialog(Context context){
        ProblemDialog problemDialog = new ProblemDialog(context);
        problemDialog.show();
    }

    public static void loadQuestions(){
        QuestionSQL questionSQL = new QuestionSQL();
        QuestionCache.setQuestionArrayList(questionSQL.getQuestions());
    }
}

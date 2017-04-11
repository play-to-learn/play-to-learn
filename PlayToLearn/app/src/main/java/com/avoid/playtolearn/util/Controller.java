package com.avoid.playtolearn.util;

import android.content.Context;

import com.avoid.playtolearn.cache.QuestionCache;
import com.avoid.playtolearn.database.cursor.QuestionSQL;
import com.avoid.playtolearn.widget.ProblemDialog;

public class Controller {
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
}

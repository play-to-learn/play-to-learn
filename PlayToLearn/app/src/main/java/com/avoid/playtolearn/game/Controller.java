package com.avoid.playtolearn.game;

import android.content.Context;

import com.avoid.playtolearn.widget.ProblemDialog;

public class Controller {
    public static void showQuestionDialog(Context context){
        ProblemDialog problemDialog = new ProblemDialog(context);
        problemDialog.show();
    }

    public static void loadQuestions(){

    }
}

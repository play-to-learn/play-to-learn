package com.avoid.playtolearn.database.cursor;

import android.database.Cursor;

import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.model.Profile;
import com.avoid.playtolearn.model.Question;

import java.util.ArrayList;

public class QuestionSQL {
    public ArrayList<Question> getQuestions(){
        ArrayList<Question> questionArrayList = new ArrayList<>();

        Cursor cursor = Session.readableDatabase.query(
                "question",
                new String[]{"id", "name", "description", "answer", "question_category_id", "question_difficulty_id"},
                null,
                null,
                null,
                null,
                "id ASC");

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            int answer = cursor.getInt(3);
            int cagoryId = cursor.getInt(4);
            int difficultyId = cursor.getInt(5);

            Question question = new Question();
            question.setId(id);
            question.setName(name);
            question.setDescription(description);
            question.setAnswer(answer);

        }

        return questionArrayList;
    }
}

package com.avoid.playtolearn.database.cursor;

import android.database.Cursor;

import com.avoid.playtolearn.common.Session;
import com.avoid.playtolearn.model.Profile;

import java.util.ArrayList;

public class UserSQL {
    public ArrayList<Profile> getProfiles(){
        ArrayList<Profile> profileArrayList = new ArrayList<>();

        Cursor cursor = Session.readableDatabase.query(
                "user",
                new String[]{"id", "first_name", "last_name", "score"},
                null,
                null,
                null,
                null,
                "id ASC");

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            int score = cursor.getInt(3);

            Profile profile = new Profile();
            profile.setId(id);
            profile.setFirstName(firstName);
            profile.setLastName(lastName);
            profile.setScore(score);
            profileArrayList.add(profile);
        }

        return profileArrayList;
    }
}

package com.avoid.playtolearn.database;

import android.content.Context;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SQLParser {
    private Context context;

    public SQLParser(Context context) {
        this.context = context;
    }

    public ArrayList<String> getSQLStatements(int resourceID) {
        ArrayList<String> sqlStatements = new ArrayList<>();
        String sqlText;
        InputStream inputStream = context.getResources().openRawResource(resourceID);
        try {
            sqlText  = IOUtils.toString(inputStream, Charsets.UTF_8);

            StringTokenizer stringTokenizer = new StringTokenizer(sqlText, ";");
            while ((stringTokenizer.hasMoreElements())){
                sqlStatements.add(stringTokenizer.nextToken());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlStatements;
    }

}

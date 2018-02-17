package com.avoid.playtolearn.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.avoid.playtolearn.R;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "playtolearndb";
    private static final int DB_VERSION = 1;

    SQLParser sqlParser;

    public DatabaseHelper(Context context) {
        super(context, DatabaseHelper.DB_NAME, null, DatabaseHelper.DB_VERSION);
        this.sqlParser = new SQLParser(context);
    }

    public DatabaseHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DatabaseHelper.DB_NAME, null, DatabaseHelper.DB_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String sqlStatement : this.sqlParser.getSQLStatements(R.raw.init_database)) {
            db.execSQL(sqlStatement);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

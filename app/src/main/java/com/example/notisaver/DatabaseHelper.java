package com.example.notisaver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messages";

    private static final String TABLE_NAME = "messages";
    private static final String COLUMN_ID = "_id";
    private static final String PACKAGE_NAME = "package_name";
    private static final String APP_NAME = "app_name";
    private static final String USER = "user";
    private static final String CONTENT = "content";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PACKAGE_NAME + " TEXT, " +
                APP_NAME + " TEXT, " +
                USER + " TEXT, " +
                CONTENT + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

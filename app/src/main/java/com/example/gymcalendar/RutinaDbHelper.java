package com.example.gymcalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RutinaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
                    RutinaContract.RutinaEntry.TABLE_NAME + " (" +
                        RutinaContract.RutinaEntry._ID + " INTEGER PRIMARY KEY," +
                        RutinaContract.RutinaEntry.COLUMN_NAME_FECHA + " DATE," +
                        RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO + " TEXT," +
                        RutinaContract.RutinaEntry.COLUMN_NAME_SERIES + " INTEGER," +
                        RutinaContract.RutinaEntry.COLUMN_NAME_PESO + " INTEGER," +
                        RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES + " INTEGER" +
                    " )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + RutinaContract.RutinaEntry.TABLE_NAME;

    public RutinaDbHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}

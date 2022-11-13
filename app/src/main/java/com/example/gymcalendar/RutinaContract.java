package com.example.gymcalendar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class RutinaContract {
    private RutinaContract(){};

    public static abstract class RutinaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Rutina";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_EJERCICIO = "ejercicio";
        public static final String COLUMN_NAME_SERIES = "series";
        public static final String COLUMN_NAME_REPETICIONES = "repeticiones";
        public static final String COLUMN_NAME_PESO = "peso";

        public static void initRutina(SQLiteDatabase db){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_FECHA, "20/10/2022");
            values.put(COLUMN_NAME_EJERCICIO, "Sentadilla");
            db.insert(TABLE_NAME, null, values);
        }
    }


}

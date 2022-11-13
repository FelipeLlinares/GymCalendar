package com.example.gymcalendar;

import static com.example.gymcalendar.RutinaDbHelper.SQL_DELETE_ENTRIES;

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
            values.put(COLUMN_NAME_FECHA, "20/11/2022");
            values.put(COLUMN_NAME_EJERCICIO, "Sentadilla");
            values.put(COLUMN_NAME_PESO, "100");
            values.put(COLUMN_NAME_SERIES, "4");
            values.put(COLUMN_NAME_REPETICIONES, "15");
            db.insert(TABLE_NAME, null, values);

            values.put(COLUMN_NAME_FECHA, "20/11/2022");
            values.put(COLUMN_NAME_EJERCICIO, "Peso muerto");
            values.put(COLUMN_NAME_PESO, "150");
            values.put(COLUMN_NAME_SERIES, "3");
            values.put(COLUMN_NAME_REPETICIONES, "6");
            db.insert(TABLE_NAME, null, values);

            values.put(COLUMN_NAME_FECHA, "14/11/2022");
            values.put(COLUMN_NAME_EJERCICIO, "Banca");
            values.put(COLUMN_NAME_PESO, "100");
            values.put(COLUMN_NAME_SERIES, "4");
            values.put(COLUMN_NAME_REPETICIONES, "5");
            db.insert(TABLE_NAME, null, values);

            values.put(COLUMN_NAME_FECHA, "14/11/2022");
            values.put(COLUMN_NAME_EJERCICIO, "Inclinado Mancuernas");
            values.put(COLUMN_NAME_PESO, "30");
            values.put(COLUMN_NAME_SERIES, "4");
            values.put(COLUMN_NAME_REPETICIONES, "15");
            db.insert(TABLE_NAME, null, values);
        }
    }


}

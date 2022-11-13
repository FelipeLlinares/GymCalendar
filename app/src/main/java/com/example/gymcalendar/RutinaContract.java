package com.example.gymcalendar;

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
    }
}

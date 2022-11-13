package com.example.gymcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private RutinaDbHelper dbHelper;
    private SQLiteDatabase db;

    private CalendarView calendarView;
    private Button btnVerDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new RutinaDbHelper(getApplicationContext(), "myrutina.db");
        db = dbHelper.getWritableDatabase();

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        btnVerDia = (Button) findViewById(R.id.btnVerDia) ;

        RutinaContract.RutinaEntry.initRutina(db);
    }
}
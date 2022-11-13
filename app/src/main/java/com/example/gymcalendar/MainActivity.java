package com.example.gymcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private RutinaDbHelper dbHelper;
    private SQLiteDatabase db;

    private CalendarView calendarView;
    private Button btnVerDia;
    private Date dateSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new RutinaDbHelper(getApplicationContext(), "myrutina.db");
        db = dbHelper.getWritableDatabase();

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        btnVerDia = (Button) findViewById(R.id.btnVerDia) ;

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
            dateSeleccionado = new Date(year,month,dayOfMonth);
            }
        });
        RutinaContract.RutinaEntry.initRutina(db);
    }

    public void onClick(View view){
        if (dateSeleccionado != null) {
            Intent MainActivity2 = new Intent(this, MainActivity2.class);
            MainActivity2.putExtra("Fecha",dateSeleccionado);
            startActivity(MainActivity2);
        } else {
            Toast.makeText(this, "Dia no seleccionado", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
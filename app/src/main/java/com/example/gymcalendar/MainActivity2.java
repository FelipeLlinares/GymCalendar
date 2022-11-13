package com.example.gymcalendar;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText etxEjercicio;
    private EditText etxPeso;
    private EditText etxSeries;
    private EditText etxRepeticiones;
    private TextView txtFecha;
    private TextView txtEjercicio;
    private TextView txtPeso;
    private TextView txtSeries;
    private TextView txtRepeticiones;

    private RutinaDbHelper dbHelper;
    private SQLiteDatabase db;
    private EjerciciosHeaderAdapter ejerciciosHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db= MainActivity.db;
        dbHelper= MainActivity.dbHelper;

        String fecha = getIntent().getStringExtra("Fecha");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        etxEjercicio = (EditText) findViewById(R.id.etxEjercicio);
        etxPeso = (EditText) findViewById(R.id.etxPeso);
        etxSeries = (EditText) findViewById(R.id.etxSeries);
        etxRepeticiones = (EditText) findViewById(R.id.etxRepeticiones);
        txtEjercicio = (TextView) findViewById(R.id.txtEjercicio);
        txtPeso = (TextView) findViewById(R.id.txtPeso);
        txtRepeticiones = (TextView) findViewById(R.id.txtRepeticiones);
        txtSeries = (TextView) findViewById(R.id.txtSeries);

        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtFecha.setText(fecha);

        try {
            List<Ejercicio> ejercicioList = buscarEjericios();
            ejerciciosHeaderAdapter = new EjerciciosHeaderAdapter(MainActivity2.this,ejercicioList);
            recyclerView.setAdapter(ejerciciosHeaderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private List<Ejercicio> buscarEjericios() throws ParseException {
        List<Ejercicio> ejercicioList = new ArrayList<>();

        String[] columns = {
                RutinaContract.RutinaEntry._ID,
                RutinaContract.RutinaEntry.COLUMN_NAME_FECHA,
                RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO,
                RutinaContract.RutinaEntry.COLUMN_NAME_PESO,
                RutinaContract.RutinaEntry.COLUMN_NAME_SERIES,
                RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES
        };
        String where = RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO + " = ?";
        String[] whereArgs = { "Sentadilla" };

        Cursor cursor = db.query(RutinaContract.RutinaEntry.TABLE_NAME,columns,where,whereArgs,null,null,null);
        Ejercicio ejercicio;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            while (cursor.moveToNext()) {
                ejercicio = new Ejercicio();
                ejercicio.set_ID(cursor.getInt(0));
                ejercicio.setFecha(dateFormat.parse(cursor.getString(1)));
                ejercicio.setEjercicio(cursor.getString(2));
                ejercicio.setPeso(cursor.getInt(3));
                ejercicio.setNumSeries(cursor.getInt(4));
                ejercicio.setNumRepeticiones(cursor.getInt(5));

                ejercicioList.add(ejercicio);
            }
        } finally {
            cursor.close();
        }
        return ejercicioList;
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void onAñadir(View view) {
        ContentValues values = new ContentValues();
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_FECHA, txtFecha.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES, txtRepeticiones.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_SERIES, txtSeries.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_PESO, txtPeso.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO, txtEjercicio.getText().toString());

    }






    public void onLimpiar(View view){
        etxEjercicio.setText("");
        etxPeso.setText("");
        etxRepeticiones.setText("");
        etxSeries.setText("");


    }



    public void onBorrar(View view){



    }

}
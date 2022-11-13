package com.example.gymcalendar;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Button btnAñadir;
    private Button btnBorrar;
    private Button btnModificar;
    private Button btnLimpiar;


    private RutinaDbHelper dbHelper;
    private SQLiteDatabase db;


    private Ejercicio seleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
        btnAñadir = (Button) findViewById(R.id.btnAñadir);
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);

        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtFecha.setText(fecha);

        List<Ejercicio> ejercicioList =

       //  db= MainActivity.db;
       // dbHelper= MainActivity.dbHelper;


        seleccionado=null;


    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void onAñadir(View view) {

        dbHelper = new RutinaDbHelper(getApplicationContext(), "myrutina.db");
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_FECHA, txtFecha.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES, txtRepeticiones.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_SERIES, txtSeries.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_PESO, txtPeso.getText().toString());
        values.put(RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO, txtEjercicio.getText().toString());


        db.insert(RutinaContract.RutinaEntry.TABLE_NAME,null,values);
        db.close();

    }






    public void onLimpiar(View view){
        etxEjercicio.setText("");
        etxPeso.setText("");
        etxRepeticiones.setText("");
        etxSeries.setText("");
    }



    public void onBorrar(View view){
    if(seleccionado==null ){
        Toast.makeText(this, R.string.BORRAR_NO_VACIO, Toast.LENGTH_LONG).show();
    }else{
        dbHelper = new RutinaDbHelper(getApplicationContext(), "myrutina.db");
        db = dbHelper.getWritableDatabase();
        String where= RutinaContract.RutinaEntry._ID + " = ?";
        String[] whereArgs = {String.valueOf(seleccionado.get_ID())};
        db.delete(RutinaContract.RutinaEntry.TABLE_NAME, where, whereArgs);
        db.close();

         }
    }


    public void onModificar(View view){
        if(seleccionado==null ){
            Toast.makeText(this, R.string.BORRAR_NO_VACIO, Toast.LENGTH_LONG).show();
        }else{
            dbHelper = new RutinaDbHelper(getApplicationContext(), "myrutina.db");
            db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_FECHA, txtFecha.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES, txtRepeticiones.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_SERIES, txtSeries.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_PESO, txtPeso.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO, txtEjercicio.getText().toString());

            String where= RutinaContract.RutinaEntry._ID + " = ?";
            String[] whereArgs = {String.valueOf(seleccionado.get_ID())};
            db.update(RutinaContract.RutinaEntry.TABLE_NAME,values, where, whereArgs);
            db.close();

        }

    }

}
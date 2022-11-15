package com.example.gymcalendar;

import static com.example.gymcalendar.MainActivity.DATABASE_NAME;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
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

    private List<Ejercicio> ejercicioList;
    private Ejercicio seleccionado = null;


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

        cargarGrid();

        recyclerView.addOnItemTouchListener(new es.gestion.clinicaoftalmologica.RecyclerItemClickListener(this, recyclerView, new es.gestion.clinicaoftalmologica.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                seleccionado = ejercicioList.get(position);
                etxEjercicio.setText(seleccionado.getEjercicio());
                etxPeso.setText(String.valueOf(seleccionado.getPeso()));
                etxRepeticiones.setText(String.valueOf(seleccionado.getNumRepeticiones()));
                etxSeries.setText(String.valueOf(seleccionado.getNumSeries()));
                desmarcar();
                view.setBackgroundColor(Color.parseColor("#808080"));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));
    }

    private void desmarcar(){
        int n = recyclerView.getChildCount();
        for(int i=0;i<n;i++){
            recyclerView.getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    private void cargarGrid(){
        try {
            ejercicioList = buscarEjericios();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ejerciciosHeaderAdapter = new EjerciciosHeaderAdapter(MainActivity2.this,ejercicioList);
        recyclerView.setAdapter(ejerciciosHeaderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
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
        String where = RutinaContract.RutinaEntry.COLUMN_NAME_FECHA + " = ?";
        String[] whereArgs = { txtFecha.getText().toString() };

        Cursor cursor = db.query(RutinaContract.RutinaEntry.TABLE_NAME,columns,where,whereArgs,null,null,null);
        Ejercicio ejercicio;

        try {
            while (cursor.moveToNext()) {
                ejercicio = new Ejercicio();
                ejercicio.set_ID(cursor.getInt(0));
                ejercicio.setFecha(cursor.getString(1));
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

        dbHelper = new RutinaDbHelper(getApplicationContext(), DATABASE_NAME);
        db = dbHelper.getWritableDatabase();

        if(etxRepeticiones.getText().toString().isEmpty()
                || etxSeries.getText().toString().isEmpty()
                || etxPeso.getText().toString().isEmpty()
                || etxEjercicio.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.RELLENAR_TODOS_CAMPOS, Toast.LENGTH_LONG).show();
        }else{
            ContentValues values = new ContentValues();
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_FECHA, txtFecha.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES, etxRepeticiones.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_SERIES, etxSeries.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_PESO, etxPeso.getText().toString());
            values.put(RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO, etxEjercicio.getText().toString());
            db.insert(RutinaContract.RutinaEntry.TABLE_NAME,null,values);

            limpiarCampos();
            cargarGrid();
            db.close();
            Toast.makeText(this, R.string.Añadido_con_exito, Toast.LENGTH_LONG).show();
        }
        hideSoftKeyboard(etxRepeticiones);
    }

    public void onLimpiar(View view){
        limpiarCampos();
    }

    private void limpiarCampos(){
        etxEjercicio.setText("");
        etxPeso.setText("");
        etxRepeticiones.setText("");
        etxSeries.setText("");
        seleccionado = null;
        desmarcar();
    }


    public void onBorrar(View view){
        if(seleccionado==null ){
            Toast.makeText(this, R.string.BORRAR_NO_VACIO, Toast.LENGTH_LONG).show();
        }else{
            dbHelper = new RutinaDbHelper(getApplicationContext(), DATABASE_NAME);
            db = dbHelper.getWritableDatabase();
            String where= RutinaContract.RutinaEntry._ID + " = ?";
            String[] whereArgs = {String.valueOf(seleccionado.get_ID())};
            db.delete(RutinaContract.RutinaEntry.TABLE_NAME, where, whereArgs);
            cargarGrid();
            limpiarCampos();
            db.close();
            Toast.makeText(this, R.string.Borrado_correctamente, Toast.LENGTH_LONG).show();
        }
        hideSoftKeyboard(etxRepeticiones);
    }


    public void onModificar(View view){
        if(seleccionado==null){
            Toast.makeText(this, R.string.modificar_debeSeleccionar, Toast.LENGTH_LONG).show();
        }else{
            dbHelper = new RutinaDbHelper(getApplicationContext(), DATABASE_NAME);
            db = dbHelper.getWritableDatabase();

            if(etxRepeticiones.getText().toString().isEmpty()
                    || etxSeries.getText().toString().isEmpty()
                    || etxPeso.getText().toString().isEmpty()
                    || etxEjercicio.getText().toString().isEmpty()){
                Toast.makeText(this, R.string.RELLENAR_TODOS_CAMPOS, Toast.LENGTH_LONG).show();
            }else {
                ContentValues values = new ContentValues();
                values.put(RutinaContract.RutinaEntry.COLUMN_NAME_FECHA, txtFecha.getText().toString());
                values.put(RutinaContract.RutinaEntry.COLUMN_NAME_REPETICIONES, etxRepeticiones.getText().toString());
                values.put(RutinaContract.RutinaEntry.COLUMN_NAME_SERIES, etxSeries.getText().toString());
                values.put(RutinaContract.RutinaEntry.COLUMN_NAME_PESO, etxPeso.getText().toString());
                values.put(RutinaContract.RutinaEntry.COLUMN_NAME_EJERCICIO, etxEjercicio.getText().toString());

                String where = RutinaContract.RutinaEntry._ID + " = ?";
                String[] whereArgs = {String.valueOf(seleccionado.get_ID())};
                db.update(RutinaContract.RutinaEntry.TABLE_NAME, values, where, whereArgs);

                limpiarCampos();
                cargarGrid();
                db.close();
                Toast.makeText(this, R.string.Modificado_correctamente,Toast.LENGTH_LONG).show();
            }
        }
        hideSoftKeyboard(etxRepeticiones);

    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
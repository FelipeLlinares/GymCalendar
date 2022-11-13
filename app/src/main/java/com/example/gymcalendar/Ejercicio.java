package com.example.gymcalendar;

import java.util.Date;

public class Ejercicio {
    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    private int _ID;
    private String Ejercicio;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    private Date fecha;
    private int numSeries;
    private int peso;
    private int numRepeticiones;

    public String getEjercicio() {
        return Ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        Ejercicio = ejercicio;
    }

    public int getNumSeries() {
        return numSeries;
    }

    public void setNumSeries(int numSeries) {
        this.numSeries = numSeries;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setNumRepeticiones(int numRepeticiones) {
        this.numRepeticiones = numRepeticiones;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }
}

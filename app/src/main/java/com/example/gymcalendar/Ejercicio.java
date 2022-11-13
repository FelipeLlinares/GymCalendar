package com.example.gymcalendar;

public class Ejercicio {
    private String Ejercicio;
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
}

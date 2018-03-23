package com.example.tivi.appinicio.Modelo;

/**
 * Created by Tivi on 21/03/2018.
 */

public class Nota
{
    private int idNota;
    private double valorNota;
    private String promedio;
    private int Fk_Parametro;

    public Nota() {
    }

    public Nota(double valorNota, String promedio, int fk_Parametro) {
        this.valorNota = valorNota;
        this.promedio = promedio;
        Fk_Parametro = fk_Parametro;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public int getFk_Parametro() {
        return Fk_Parametro;
    }

    public void setFk_Parametro(int fk_Parametro) {
        Fk_Parametro = fk_Parametro;
    }
}

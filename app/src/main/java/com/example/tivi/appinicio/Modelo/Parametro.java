package com.example.tivi.appinicio.Modelo;

/**
 * Created by Tivi on 20/03/2018.
 */

public class Parametro
{
    private int idParametro;
    private String nombreParametro;
    private double valorPorcentual;
    private int fk_Materia;
    private double NotaFinal;
    private double PromedioParametro;

    public Parametro() {
    }

    public Parametro(String nombreParametro, double valorPorcentual, int fk_Materia) {
        this.nombreParametro = nombreParametro;
        this.valorPorcentual = valorPorcentual;
        this.fk_Materia = fk_Materia;
    }

    public Parametro(String nombreParametro, double valorPorcentual, int fk_Materia, double notaFinal, double promedioParametro) {
        this.nombreParametro = nombreParametro;
        this.valorPorcentual = valorPorcentual;
        this.fk_Materia = fk_Materia;
        NotaFinal = notaFinal;
        PromedioParametro = promedioParametro;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public double getValorPorcentual() {
        return valorPorcentual;
    }

    public void setValorPorcentual(double valorPorcentual) {
        this.valorPorcentual = valorPorcentual;
    }

    public int getFk_Materia() {
        return fk_Materia;
    }

    public void setFk_Materia(int fk_Materia) {
        this.fk_Materia = fk_Materia;
    }

    public double getNotaFinal() {
        return NotaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        NotaFinal = notaFinal;
    }

    public double getPromedioParametro() {
        return PromedioParametro;
    }

    public void setPromedioParametro(double promedioParametro) {
        PromedioParametro = promedioParametro;
    }
}

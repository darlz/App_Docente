package com.example.tivi.appinicio.Modelo;

/**
 * Created by Tivi on 18/03/2018.
 */

public class Materia
{
    private int idMateria;
    private String nombreMateria;
    private String estadoMateria;

    public Materia() {
    }

    public Materia(String nombreMateria, String estadoMateria) {
        this.nombreMateria = nombreMateria;
        this.estadoMateria = estadoMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getEstadoMateria() {
        return estadoMateria;
    }

    public void setEstadoMateria(String estadoMateria) {
        this.estadoMateria = estadoMateria;
    }
}

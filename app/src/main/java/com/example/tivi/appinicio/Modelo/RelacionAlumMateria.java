package com.example.tivi.appinicio.Modelo;

/**
 * Created by Tivi on 18/03/2018.
 */

public class RelacionAlumMateria
{
    private int idRelacion;
    private int fk_Alumno;
    private int fk_Materia;
    private String NombreRecibidoAlumno;
    private String NombreRecibidoMateria;

    public RelacionAlumMateria() {
    }

    public RelacionAlumMateria(int fk_Alumno, int fk_Materia, String nombreRecibidoAlumno, String nombreRecibidoMateria) {
        this.fk_Alumno = fk_Alumno;
        this.fk_Materia = fk_Materia;
        NombreRecibidoAlumno = nombreRecibidoAlumno;
        NombreRecibidoMateria = nombreRecibidoMateria;
    }

    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }

    public int getFk_Alumno() {
        return fk_Alumno;
    }

    public void setFk_Alumno(int fk_Alumno) {
        this.fk_Alumno = fk_Alumno;
    }

    public int getFk_Materia() {
        return fk_Materia;
    }

    public void setFk_Materia(int fk_Materia) {
        this.fk_Materia = fk_Materia;
    }

    public String getNombreRecibidoAlumno() {
        return NombreRecibidoAlumno;
    }

    public void setNombreRecibidoAlumno(String nombreRecibidoAlumno) {
        NombreRecibidoAlumno = nombreRecibidoAlumno;
    }

    public String getNombreRecibidoMateria() {
        return NombreRecibidoMateria;
    }

    public void setNombreRecibidoMateria(String nombreRecibidoMateria) {
        NombreRecibidoMateria = nombreRecibidoMateria;
    }
}

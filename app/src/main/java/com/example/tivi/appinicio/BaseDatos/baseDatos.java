package com.example.tivi.appinicio.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tivi.appinicio.Modelo.Estudiante;
import com.example.tivi.appinicio.Modelo.Materia;
import com.example.tivi.appinicio.Modelo.Nota;
import com.example.tivi.appinicio.Modelo.Parametro;
import com.example.tivi.appinicio.Modelo.RelacionAlumMateria;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tivi on 17/03/2018.
 */

//SQLITEOPENHELPER CREA UN OBJETO DE AYUDA PARA CREAR Y GESTIONAR LA BASE DE DATOS
public class baseDatos extends SQLiteOpenHelper{

    //DECLARAMOS VARIABLES GLOBALES FINALES, QUE NO SE MODIFIQUEN
    //CREAMOS LA VARIABLE CON EL NOMBRE DE LA BASE DE DATOS
    public static final String DATABASE_NAME = "aula.db";


    //DETERMINAMOS LA VERSION DE NUESTRA BASE DE DATOS
    private static final int DATABASE_VERSION = 4 ;

    //TABLA ALUMNO
    public static final String TABLA_PERSONA_NOMBRE = "persona";
    public static final String COLUMNAS_ID = "_id";
    public static final String COLUMNAS_NOMBRE_PERSONAS = "nombre";
    public static final String COLUMNAS_APELLIDO_PERSONAS = "apellido";
    public static final String COLUMNAS_EMAIL_PERSONAS = "email";
    public static final String COLUMNAS_CELULAR_PERSONAS = "celular";
    public static final String COLUMNAS_CICLO_PERSONAS = "ciclo";


    //TABLA MATERIA

    public static final String TABLA_MATERIA_NOMBRE = "materia";
    public static final String COLUMNAS_IDMATERIA = "_idmateria";
    public static final String COLUMNAS_NOMBRE_MATERIA = "nombre_materia";
    public static final String COLUMNAS_ESTADO_MATERIA = "estado_materia";

    //TABLA PARAMETRO

    public static final String TABLA_PARAMETRO_NOMBRE = "parametros";
    public static final String COLUMNAS_IDPARAMETRO = "_idparametro";
    public static final String COLUMNAS_NOMBRE_PARAMETRO = "nombreparametro";
    public static final String COLUMNAS_VALOR_PARAMETRO = "valorparametro";
    public static final String COLUMNAS_FK_MATERIA_PARAMETRO = "_fkmateria";
    public static final String COLUMNAS_NOTA_FINAL_PARAMETRO = "notafinal";
    public static final String COLUMNAS_PROMEDIOPARAMETRO_PARAMETRO = "promedioparametro";


    //TABLA MATERIA_ALUMNO

    public static final String TABLA_MATERIA_ALUMNO_NOMBRE = "materia_alumno";
    public static final String COLUMNAS_ID_MATERIA_ALUMNO = "_id_materia_alumno";
    public static final String COLUMNAS_FK_MATERIA = "_fk_materia";
    public static final String COLUMNAS_FK_ALUMNO = "_fk_alumno";
    public static final String COLUMNAS_NOMBRE_RECIBIDO_ALUMNO = "nombrealumno";
    public static final String COLUMNAS_NOMBRE_RECIBIDO_MATERIA = "nombremateria";

    //CREAMOS VARIABLES PARA LA TABLA NOTA
    public static final String TABLA_NOTA_NOMBRE = "nota";
    public static final String COLUMNAS_IDNOTA = "_idnota";
    public static final String COLUMNAS_VALOR_NOTA = "valornota";
    public static final String COLUMNAS_PROMEDIO_NOTA = "promedio";
    public static final String COLUMNAS_FK_PARAMETRO_NOTA = "_fkparametro";


    //CREAMOS UN CONSTRUCTOR DE LA CLASE
    public baseDatos(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    //GENERAMOS EL METODO ONCREATE PARA QUE AQUÍ SE EJECUTEN LAS CONSULTAS SQLITE Y CREE CADA TABLA
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLA_PERSONA_NOMBRE + " (" +
                COLUMNAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNAS_NOMBRE_PERSONAS +" TEXT  NOT NULL, "+
                COLUMNAS_APELLIDO_PERSONAS +" TEXT  NOT NULL, "+
                COLUMNAS_EMAIL_PERSONAS +" TEXT  NOT NULL, "+
                COLUMNAS_CELULAR_PERSONAS +" TEXT  NOT NULL, "+
                COLUMNAS_CICLO_PERSONAS + " TEXT  NOT NULL);"
        );

        String query="CREATE TABLE materia(_idmateria integer primary key autoincrement, nombre_materia TEXT, estado_materia TEXT)";
        db.execSQL(query);



        String query1="CREATE TABLE materia_alumno(_id_materia_alumno integer primary key autoincrement, _fk_materia integer, _fk_alumno integer, nombrealumno TEXT, nombremateria TEXT, FOREIGN KEY(_fk_alumno) REFERENCES persona(_id) ON DELETE RESTRICT  ON UPDATE CASCADE, FOREIGN KEY(_fk_materia) REFERENCES materia(_idmateria) ON DELETE RESTRICT  ON UPDATE CASCADE)";
        db.execSQL(query1);

        db.execSQL("CREATE TABLE " + TABLA_PARAMETRO_NOMBRE + " (" +
                COLUMNAS_IDPARAMETRO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNAS_NOMBRE_PARAMETRO +" TEXT NOT NULL, "+
                COLUMNAS_VALOR_PARAMETRO +" TEXT NOT NULL, "+
                COLUMNAS_FK_MATERIA_PARAMETRO +" TEXT NOT NULL, " +
                COLUMNAS_NOTA_FINAL_PARAMETRO +" TEXT NOT NULL, " +
                COLUMNAS_PROMEDIOPARAMETRO_PARAMETRO +" TEXT NOT NULL);"
        );

        //CREAMOS LA TABLA NOTA
        db.execSQL("CREATE TABLE " + TABLA_NOTA_NOMBRE + " (" +
                COLUMNAS_IDNOTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNAS_VALOR_NOTA +" REAL NOT NULL, "+
                COLUMNAS_PROMEDIO_NOTA +" TEXT, "+
                COLUMNAS_FK_PARAMETRO_NOTA +" INTEGER NOT NULL);"
        );

        //String query2="CREATE TABLE parametros(_idparametro integer primary key autoincrement, nombreparametro TEXT, valorparametro REAL, _fkmateria integer)";
        //db.execSQL(query2);

        /*db.execSQL("CREATE TABLE " + TABLA_MATERIA_NOMBRE + " (" +
                COLUMNAS_IDMATERIA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNAS_NOMBRE_MATERIA +" TEXT UNIQUE NOT NULL, "+
                COLUMNAS_ESTADO_MATERIA +" TEXT UNIQUE NOT NULL);"
        );*/


    }

    //GENERAMOS EL METODO ONUPGRADE PARA MODIFICAR LA BASE DE DATOS, TAMBIEN
    //PERMITE CREAR MAS TABLAS EN LA BASE DE DATOS
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PERSONA_NOMBRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MATERIA_NOMBRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PARAMETRO_NOMBRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_NOTA_NOMBRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MATERIA_ALUMNO_NOMBRE);
        this.onCreate(db);
    }

    //METODO PARA GUARDAR ALUMNOS
    public void GuardarPersonas(Estudiante estudiante)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMNAS_NOMBRE_PERSONAS, estudiante.getNombre());
        values.put(COLUMNAS_APELLIDO_PERSONAS, estudiante.getApellido());
        values.put(COLUMNAS_EMAIL_PERSONAS, estudiante.getEmail());
        values.put(COLUMNAS_CELULAR_PERSONAS, estudiante.getCelular());
        values.put(COLUMNAS_CICLO_PERSONAS, estudiante.getCiclo());

        //EJECUTAMOS EL METODO PARA INSERTAR LAS FILAS EN LA TABLA
        //String query = "INSERT INTO " + TABLA_PERSONA_NOMBRE + "(nombre, apellido, email, celular, ciclo) values"+"("+estudiante.getNombre()+","+estudiante.getApellido()+","+estudiante.getEmail()+","+estudiante.getCelular()+","+estudiante.getCiclo()+");" ;

        //db.execSQL(query);
        db.insert(TABLA_PERSONA_NOMBRE,null, values);
        db.close();
    }

    //METODO PARA GUARDAR MATERIAS

    public void GuardarMaterias(Materia materia)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMNAS_NOMBRE_MATERIA, materia.getNombreMateria());
        values.put(COLUMNAS_ESTADO_MATERIA, materia.getEstadoMateria());

        //EJECUTAMOS EL METODO PARA INSERTAR LAS FILAS EN LA TABLA
        //String query = "INSERT INTO " + TABLA_PERSONA_NOMBRE + "(nombre, apellido, email, celular, ciclo) values"+"("+estudiante.getNombre()+","+estudiante.getApellido()+","+estudiante.getEmail()+","+estudiante.getCelular()+","+estudiante.getCiclo()+");" ;

        //db.execSQL(query);
        db.insert(TABLA_MATERIA_NOMBRE,null, values);
        db.close();
    }

    //METODO PARA GUARDAR RELACION_MATERIAS

    public void Guardar_Relacion_Materias_Alumnos(RelacionAlumMateria relacion_mater_alum)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMNAS_FK_MATERIA, relacion_mater_alum.getFk_Materia());
        values.put(COLUMNAS_FK_ALUMNO, relacion_mater_alum.getFk_Alumno());
        values.put(COLUMNAS_NOMBRE_RECIBIDO_ALUMNO, relacion_mater_alum.getNombreRecibidoAlumno());
        values.put(COLUMNAS_NOMBRE_RECIBIDO_MATERIA, relacion_mater_alum.getNombreRecibidoMateria());

        //EJECUTAMOS EL METODO PARA INSERTAR LAS FILAS EN LA TABLA
        //String query = "INSERT INTO " + TABLA_PERSONA_NOMBRE + "(nombre, apellido, email, celular, ciclo) values"+"("+estudiante.getNombre()+","+estudiante.getApellido()+","+estudiante.getEmail()+","+estudiante.getCelular()+","+estudiante.getCiclo()+");" ;

        //db.execSQL(query);
        db.insert(TABLA_MATERIA_ALUMNO_NOMBRE,null, values);
        db.close();
    }


    //METODO PARA GUARDAR PARAMETRO
    public void GuardarParametros(Parametro parametro)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMNAS_NOMBRE_PARAMETRO, parametro.getNombreParametro());
        values.put(COLUMNAS_VALOR_PARAMETRO, parametro.getValorPorcentual());
        values.put(COLUMNAS_FK_MATERIA_PARAMETRO, parametro.getFk_Materia());
        values.put(COLUMNAS_NOTA_FINAL_PARAMETRO, parametro.getNotaFinal());
        values.put(COLUMNAS_PROMEDIOPARAMETRO_PARAMETRO, parametro.getPromedioParametro());


        //EJECUTAMOS EL METODO PARA INSERTAR LAS FILAS EN LA TABLA
        //String query = "INSERT INTO " + TABLA_PERSONA_NOMBRE + "(nombre, apellido, email, celular, ciclo) values"+"("+estudiante.getNombre()+","+estudiante.getApellido()+","+estudiante.getEmail()+","+estudiante.getCelular()+","+estudiante.getCiclo()+");" ;

        //db.execSQL(query);
        db.insert(TABLA_PARAMETRO_NOMBRE,null, values);
        db.close();
    }


    //METODO PARA GUARDAR NOTA
    public void GuardarNotas(Nota notas)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMNAS_VALOR_NOTA, notas.getValorNota());
        values.put(COLUMNAS_PROMEDIO_NOTA, notas.getPromedio());
        values.put(COLUMNAS_FK_PARAMETRO_NOTA, notas.getFk_Parametro());

        db.insert(TABLA_NOTA_NOMBRE,null, values);
        db.close();
    }


    //SE CREA UNA LISTA DE TIPO LINKEDLIST YA QUE PERMITE
    // INSERTAR Y EXTRAER MEJOR LOS DATOS A DIFERENCIA DEL ARRAYLIST
    public List<Estudiante> listaEstudiantes(String filter) {
        String query;
        //filter results by filter option provided
        if(filter=="n"){
            query = "SELECT  * FROM " + TABLA_PERSONA_NOMBRE+ " ORDER BY "+ filter+" asc";
        }
            query = "SELECT  * FROM " + TABLA_PERSONA_NOMBRE;

        List<Estudiante> estudianteLinkenlist = new LinkedList<>();
        //GETWRITABLEDATABASE CREA Y/O ABRE UNA BASE DE DATOS
        // QUE SE UTILIZARÁ PARA LA LECTURA Y ESCRITURA DE LOS DATOS
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Estudiante estudiante;

        if (cursor.moveToFirst()) {
            do {
                estudiante = new Estudiante();

                estudiante.setIdEstudiante(cursor.getInt(cursor.getColumnIndex(COLUMNAS_ID)));
                estudiante.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_PERSONAS)));
                estudiante.setApellido(cursor.getString(cursor.getColumnIndex(COLUMNAS_APELLIDO_PERSONAS)));
                estudiante.setEmail(cursor.getString(cursor.getColumnIndex(COLUMNAS_EMAIL_PERSONAS)));
                estudiante.setCelular(cursor.getString(cursor.getColumnIndex(COLUMNAS_CELULAR_PERSONAS)));
                estudiante.setCiclo(cursor.getString(cursor.getColumnIndex(COLUMNAS_CICLO_PERSONAS)));
                estudianteLinkenlist.add(estudiante);
            } while (cursor.moveToNext());
        }
        return estudianteLinkenlist;
    }

    //METODO PARA LISTAR RELACION_MATERIA_ALUMNOS A TRAVES DE UN FILTRO
    public List<RelacionAlumMateria> relacionAlumMateriaList(int id) {
        String query;
        //filter results by filter option provided
        //query = "SELECT  * FROM " + TABLA_MATERIA_ALUMNO_NOMBRE;
        query = "select * from materia_alumno where _fk_materia="+id;


        List<RelacionAlumMateria> relacionAlumMateriaLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        RelacionAlumMateria estudiante;

        if (cursor.moveToFirst()) {
            do {
                estudiante = new RelacionAlumMateria();

                estudiante.setIdRelacion(cursor.getInt(cursor.getColumnIndex(COLUMNAS_ID_MATERIA_ALUMNO)));
                estudiante.setFk_Materia(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_MATERIA)));
                estudiante.setFk_Alumno(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_ALUMNO)));
                estudiante.setNombreRecibidoAlumno(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_RECIBIDO_ALUMNO)));
                estudiante.setNombreRecibidoMateria(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_RECIBIDO_MATERIA)));
                relacionAlumMateriaLinkedList.add(estudiante);
            } while (cursor.moveToNext());
        }
        return relacionAlumMateriaLinkedList;
    }

    //METODO PARA LISTAR MATERIAS A TRAVES DE UN FILTRO
    public List<Materia> listaMaterias(String filter) {

        String query;

        //filter results by filter option provided
        if(filter=="n"){
            query = "SELECT  * FROM " + TABLA_MATERIA_NOMBRE+ " ORDER BY "+ filter+" asc";
        }
        query = "SELECT  * FROM " + TABLA_MATERIA_NOMBRE;



        List<Materia> materiaLinkenList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Materia materia;

        if (cursor.moveToFirst()) {
            do {
                materia = new Materia();

                materia.setIdMateria(cursor.getInt(cursor.getColumnIndex(COLUMNAS_IDMATERIA)));
                materia.setNombreMateria(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_MATERIA)));
                materia.setEstadoMateria(cursor.getString(cursor.getColumnIndex(COLUMNAS_ESTADO_MATERIA)));
                materiaLinkenList.add(materia);
            } while (cursor.moveToNext());
        }
        return materiaLinkenList;
    }

    //METODO PARA LISTAR PARAMETROS A TRAVES DE UN FILTRO
    public List<Parametro> listaParametros(int id) {

        String query;

        query = "SELECT  * FROM " + TABLA_PARAMETRO_NOMBRE + " WHERE _fkmateria ="+id;
        //query = "select nombreparametro from parametros, materia where materia._idmateria=parametros._fkmateria=" + id ;

        List<Parametro> parametroLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Parametro parametro;

        if (cursor.moveToFirst()) {
            do {
                parametro = new Parametro();

                parametro.setIdParametro(cursor.getInt(cursor.getColumnIndex(COLUMNAS_IDPARAMETRO)));
                parametro.setNombreParametro(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_PARAMETRO)));
                parametro.setValorPorcentual(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_VALOR_PARAMETRO)));
                parametro.setFk_Materia(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_MATERIA_PARAMETRO)));
                parametro.setNotaFinal(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_NOTA_FINAL_PARAMETRO)));
                parametro.setPromedioParametro(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_PROMEDIOPARAMETRO_PARAMETRO)));
                parametroLinkedList.add(parametro);
            } while (cursor.moveToNext());
        }
        return parametroLinkedList;
    }

    //METODO PARA LISTAR NOTAS A TRAVES DE UN FILTRO
    public List<Nota> listaNotas(int id) {

        String query;

        //query = "SELECT  * FROM " + TABLA_NOTA_NOMBRE;
        query = "SELECT  * FROM " + TABLA_NOTA_NOMBRE + " WHERE _fkparametro ="+id;

        List<Nota> notaLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Nota nota;

        if (cursor.moveToFirst()) {
            do {
                nota = new Nota();
                nota.setIdNota(cursor.getInt(cursor.getColumnIndex(COLUMNAS_IDNOTA)));
                nota.setValorNota(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_VALOR_NOTA)));
                nota.setPromedio(cursor.getString(cursor.getColumnIndex(COLUMNAS_PROMEDIO_NOTA)));
                nota.setFk_Parametro(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_PARAMETRO_NOTA)));
                notaLinkedList.add(nota);
            } while (cursor.moveToNext());
        }

        /*double sumanota=0;
        for (int i=0; i<notaLinkedList.size();i++){
            sumanota = sumanota+notaLinkedList.get(i).getValorNota();
            Log.i("SUMA",String.valueOf(sumanota));
        }*/
        return notaLinkedList;
    }


    //METODO PARA OBTENER TODOS LOS ALUMNOS
    public Estudiante ObtenerEstudiantes(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + TABLA_PERSONA_NOMBRE + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Estudiante EstudianteRecibido = new Estudiante();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            EstudianteRecibido.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_PERSONAS)));
            EstudianteRecibido.setApellido(cursor.getString(cursor.getColumnIndex(COLUMNAS_APELLIDO_PERSONAS)));
            EstudianteRecibido.setEmail(cursor.getString(cursor.getColumnIndex(COLUMNAS_EMAIL_PERSONAS)));
            EstudianteRecibido.setCelular(cursor.getString(cursor.getColumnIndex(COLUMNAS_CELULAR_PERSONAS)));
            EstudianteRecibido.setCiclo(cursor.getString(cursor.getColumnIndex(COLUMNAS_CICLO_PERSONAS)));
        }
        return EstudianteRecibido;
    }

    public List<Estudiante> listaEstudiantesSp() {

        String query;

        query = "SELECT  * FROM " + TABLA_PERSONA_NOMBRE;

        List<Estudiante> estudianteLinkenlist = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Estudiante estudiante;

        if (cursor.moveToFirst()) {
            do {
                estudiante = new Estudiante();

                estudiante.setIdEstudiante(cursor.getInt(cursor.getColumnIndex(COLUMNAS_ID)));
                estudiante.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_PERSONAS)));
                estudiante.setApellido(cursor.getString(cursor.getColumnIndex(COLUMNAS_APELLIDO_PERSONAS)));
                estudiante.setEmail(cursor.getString(cursor.getColumnIndex(COLUMNAS_EMAIL_PERSONAS)));
                estudiante.setCelular(cursor.getString(cursor.getColumnIndex(COLUMNAS_CELULAR_PERSONAS)));
                estudiante.setCiclo(cursor.getString(cursor.getColumnIndex(COLUMNAS_CICLO_PERSONAS)));
                estudianteLinkenlist.add(estudiante);
            } while (cursor.moveToNext());
        }
        return estudianteLinkenlist;
    }


    //METODO PARA OBTENER TODAS LAS MATERIAS
    public Materia ObtenerMaterias(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + TABLA_MATERIA_NOMBRE + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Materia materiaRecibida = new Materia();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            materiaRecibida.setNombreMateria(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_MATERIA)));
            materiaRecibida.setEstadoMateria(cursor.getString(cursor.getColumnIndex(COLUMNAS_ESTADO_MATERIA)));
        }
        return materiaRecibida;
    }

    //METODO PARA OBTENER TODAS LOS PARAMETROS
    public Parametro ObtenerParametros(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + TABLA_PARAMETRO_NOMBRE + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Parametro parametroRecibido = new Parametro();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            parametroRecibido.setNombreParametro(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_PARAMETRO)));
            parametroRecibido.setValorPorcentual(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_VALOR_PARAMETRO)));
            parametroRecibido.setFk_Materia(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_MATERIA_PARAMETRO)));
            parametroRecibido.setNotaFinal(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_NOTA_FINAL_PARAMETRO)));
            parametroRecibido.setPromedioParametro(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_PROMEDIOPARAMETRO_PARAMETRO)));
        }
        return parametroRecibido;
    }


    //METODO PARA OBTENER TODAS LAS RELACION_MATERIAS_ALUMNOS
    public RelacionAlumMateria ObtenerRelacion_Materias_Alumnos(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + TABLA_MATERIA_ALUMNO_NOMBRE + " WHERE _id_materia_alumno="+ id;
        Cursor cursor = db.rawQuery(query, null);

        RelacionAlumMateria relacionAlumMateria = new RelacionAlumMateria();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            relacionAlumMateria.setFk_Materia(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_MATERIA)));
            relacionAlumMateria.setFk_Alumno(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_ALUMNO)));
            relacionAlumMateria.setNombreRecibidoAlumno(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_RECIBIDO_ALUMNO)));
            relacionAlumMateria.setNombreRecibidoMateria(cursor.getString(cursor.getColumnIndex(COLUMNAS_NOMBRE_RECIBIDO_MATERIA)));
        }
        return relacionAlumMateria;
    }

    //METODO PARA OBTENER TODAS LAS NOTAS
    public Nota ObtenerNOTAS(int id, TextView total)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + TABLA_NOTA_NOMBRE + " WHERE _idnota="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Nota nota = new Nota();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            nota.setValorNota(cursor.getDouble(cursor.getColumnIndex(COLUMNAS_VALOR_NOTA)));
            nota.setPromedio(cursor.getString(cursor.getColumnIndex(COLUMNAS_PROMEDIO_NOTA)));
            nota.setFk_Parametro(cursor.getInt(cursor.getColumnIndex(COLUMNAS_FK_PARAMETRO_NOTA)));
        }
        return nota;


    }



    //METODO PARA ELIMINAR REGISTROS DE ALUMNOS

    public void EliminarRegistroPersonas(int id, Context context)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLA_PERSONA_NOMBRE+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Se ha Eliminado correctamente.", Toast.LENGTH_SHORT).show();
    }

    //METODO PARA ELIMINAR REGISTROS DE MATERIAS
    public void EliminarRegistroMaterias(int id, Context context)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLA_MATERIA_NOMBRE+" WHERE _idmateria='"+id+"'");
        Toast.makeText(context, "Se ha Eliminado correctamente.", Toast.LENGTH_SHORT).show();
    }

    //METODO PARA ELIMINAR REGISTROS DE RELACION_MATERIAS_ALUMNOS
    public void EliminarRegistroRelacionMateriasAlumnos(int id, Context context)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLA_MATERIA_ALUMNO_NOMBRE+" WHERE _id_materia_alumno='"+id+"'");
        Toast.makeText(context, "Se ha Eliminado correctamente.", Toast.LENGTH_SHORT).show();
    }

    //METODO PARA ELIMINAR REGISTROS DE PARAMETROS
    public void EliminarRegistroParametros(int id, Context context)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLA_PARAMETRO_NOMBRE+" WHERE _idparametro='"+id+"'");
        Toast.makeText(context, "Se ha Eliminado correctamente.", Toast.LENGTH_SHORT).show();
    }

    //METODO PARA ELIMINAR REGISTROS DE NOTAS
    public void EliminarRegistroNotas(int id, Context context)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLA_NOTA_NOMBRE+" WHERE _idnota='"+id+"'");
        Toast.makeText(context, "Se ha Eliminado correctamente.", Toast.LENGTH_SHORT).show();
    }

    public void ActualizarRegistroPersonas(int personaId, Context context, Estudiante actualizarRegistro) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLA_PERSONA_NOMBRE+" SET nombre ='"+ actualizarRegistro.getNombre() +"'  WHERE _id='" + personaId + "'");
        Toast.makeText(context, "Se ha Actualizado el Alumno correctamente.", Toast.LENGTH_SHORT).show();
    }

    public void ActualizarRegistroMaterias(int personaId, Context context, Materia actualizarRegistro) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLA_MATERIA_NOMBRE+" SET nombre_materia ='"+ actualizarRegistro.getNombreMateria()+ "', estado_materia ='"+ actualizarRegistro.getEstadoMateria()+"'  WHERE _idmateria='" + personaId + "'");
        Toast.makeText(context, "Se ha Actualizado la Materia correctamente.", Toast.LENGTH_SHORT).show();
    }

    public void ActualizarRegistroRelacionMateriasAlumnos(int personaId, Context context, RelacionAlumMateria relacionAlumMateria) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLA_MATERIA_ALUMNO_NOMBRE+" SET _fk_materia ='"+ relacionAlumMateria.getFk_Materia()+ "', _fk_alumno ='"+ relacionAlumMateria.getFk_Alumno()+  "', nombrealumno ='"+ relacionAlumMateria.getNombreRecibidoAlumno()+  "', nombremateria ='"+ relacionAlumMateria.getNombreRecibidoMateria()+"'  WHERE _id_materia_alumno='" + personaId + "'");
        Toast.makeText(context, "Se ha Actualizado la Materia correctamente.", Toast.LENGTH_SHORT).show();
    }

    public void ActualizarRegistroParametro(int personaId, Context context, Parametro actualizarParametro) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLA_PARAMETRO_NOMBRE+" SET nombreparametro ='"+ actualizarParametro.getNombreParametro()+ "', valorparametro ='"+ actualizarParametro.getValorPorcentual()+ "', _fkmateria ='"+ actualizarParametro.getFk_Materia()+ "', notafinal ='"+ actualizarParametro.getNotaFinal()+ "', promedioparametro ='"+ actualizarParametro.getPromedioParametro()+"'  WHERE _idparametro='" + personaId + "'");
        Toast.makeText(context, "Se ha Actualizado la parametro correctamente.", Toast.LENGTH_SHORT).show();
    }

    public void ActualizarRegistroNotas(int personaId, Context context, Nota actualizarNota) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLA_NOTA_NOMBRE+" SET valornota ='"+ actualizarNota.getValorNota()+ "', promedio ='"+ actualizarNota.getPromedio()+ "', _fkparametro ='"+ actualizarNota.getFk_Parametro()+"'  WHERE _idnota='" + personaId + "'");
        Toast.makeText(context, "Se ha Actualizado la Materia correctamente.", Toast.LENGTH_SHORT).show();
    }
}



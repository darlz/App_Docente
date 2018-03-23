package com.example.tivi.appinicio.Relacionando;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Estudiante;
import com.example.tivi.appinicio.Modelo.Materia;
import com.example.tivi.appinicio.Modelo.RelacionAlumMateria;
import com.example.tivi.appinicio.R;
import com.example.tivi.appinicio.VistaMaterias.IngresarMaterias;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Relacion_Mater_Alum extends AppCompatActivity {

    Spinner Spinalumnos;
    TextView materia, idAlumSelec;
    TextView alumno;
    Button btnGuardarRelacion, ListarMaterias_Alumnos;

    baseDatos baseDatos;
    int idMateria;
    String nameMateria;

    ArrayList<String> listaAl = new ArrayList<>();
    private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacion__mater__alum);


        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            idMateria = b.getInt("MATERIA_ID");
            nameMateria = b.getString("MATERI_NAME");
        }

        //Toast.makeText(this,"ID: "+idMateria+"Nombre: "+nameMateria,Toast.LENGTH_SHORT).show();

        materia = (TextView)findViewById(R.id.txt_Materia_Selecc);
        idAlumSelec = (TextView)findViewById(R.id.txt_id_Alum_Selec);


        alumno = (TextView) findViewById(R.id.txt_Alum_Selec_del_Spinn);

        Spinalumnos = (Spinner)findViewById(R.id.spinnerAlumnos);
        btnGuardarRelacion = (Button) findViewById(R.id.guardarRelacion);
        ListarMaterias_Alumnos = (Button) findViewById(R.id.btnListarMaterias_Alumnos);

        idAlumSelec.setVisibility(View.INVISIBLE);
        alumno.setVisibility(View.INVISIBLE);
        materia.setText(nameMateria);

        db();



        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaAl);
        Spinalumnos.setAdapter(adaptador);

        Spinalumnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idAlumSelec.setText(String.valueOf(estudiantes.get(position).getIdEstudiante()));
                alumno.setText(estudiantes.get(position).getNombre().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGuardarRelacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRelacionMateriaAlumnos();
            }
        });

        ListarMaterias_Alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });
    }

    public void db(){
        baseDatos = new baseDatos(this);
        estudiantes.addAll(baseDatos.listaEstudiantesSp());

        for (int i=0;i<estudiantes.size();i++){
            listaAl.add(estudiantes.get(i).getIdEstudiante()+" - "+estudiantes.get(i).getNombre());

            //Toast.makeText(this, "ID ESTU Y NOMBRE"+listaAl,Toast.LENGTH_SHORT).show();
        }
    }

    private void saveRelacionMateriaAlumnos(){
        baseDatos = new baseDatos(this);

        int dataId = Integer.parseInt(idAlumSelec.getText().toString());
        String dataNameSelected = alumno.getText().toString();
        //create new person
        RelacionAlumMateria relacionAlumMateria = new RelacionAlumMateria(dataId, idMateria,dataNameSelected ,nameMateria);

        baseDatos.Guardar_Relacion_Materias_Alumnos(relacionAlumMateria);
        Toast.makeText(this,"Relacionado correctamente",Toast.LENGTH_SHORT).show();
        alumno.setText("");
    }

    private void listar(){
        Intent i = new Intent(Relacion_Mater_Alum.this, ListarRelacion.class);
        i.putExtra("FK_materia_alumno",idMateria);
        startActivity(i);

    }

}

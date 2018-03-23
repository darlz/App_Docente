package com.example.tivi.appinicio.VistaMaterias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Estudiante;
import com.example.tivi.appinicio.Modelo.Materia;
import com.example.tivi.appinicio.R;
import com.example.tivi.appinicio.Relacionando.ListarRelacion;

public class IngresarMaterias extends AppCompatActivity {

    EditText nombreMateria, estadoMateria;
    Button btnGuardarMateria;
    private baseDatos dbHelper;

    Button ListarMaterias, ListarMaterias_Alumnos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_materias);

        nombreMateria = (EditText)findViewById(R.id.txt_nombreMateria);
        estadoMateria = (EditText)findViewById(R.id.txt_estadoMateria);

        btnGuardarMateria = (Button)findViewById(R.id.btn_ingresarMateria);

        ListarMaterias = (Button) findViewById(R.id.btnListarMaterias);
       // ListarMaterias_Alumnos = (Button) findViewById(R.id.btnListarMaterias_Alumnos);


        btnGuardarMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMateria();
            }
        });

       ListarMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IngresarMaterias.this, ListarMaterias.class);
                startActivity(i);
            }
        });


    }

    private void saveMateria(){
        String strNombreMater = nombreMateria.getText().toString().trim();
        String strEstadoMater = estadoMateria.getText().toString().trim();
        dbHelper = new baseDatos(this);

        //create new person
        Materia materia = new Materia(strNombreMater, strEstadoMater);

        dbHelper.GuardarMaterias(materia);
        Toast.makeText(this,"Ingresado correctamente",Toast.LENGTH_SHORT).show();

        nombreMateria.setText("");
        estadoMateria.setText("");
    }

}


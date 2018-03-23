package com.example.tivi.appinicio.VistaEstudiantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.MainActivity;
import com.example.tivi.appinicio.Modelo.Estudiante;
import com.example.tivi.appinicio.R;

public class IngresarEstudiantes extends AppCompatActivity {

    EditText nombre, apellido, email, celular, ciclo;
    Button btnGuardar;
    private baseDatos dbHelper;

    Button listarAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_estudiantes);

        nombre = (EditText)findViewById(R.id.txt_nombre);
        apellido = (EditText)findViewById(R.id.txt_apellido);
        email = (EditText)findViewById(R.id.txt_email);
        celular = (EditText)findViewById(R.id.txt_celular);
        ciclo = (EditText)findViewById(R.id.txt_ciclo);

        btnGuardar = (Button)findViewById(R.id.btn_ingresar);
        listarAlumnos = (Button) findViewById(R.id.btnListarAlumnos);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().equalsIgnoreCase("")){
                    nombre.setError("Falta Nombre del Alumno");
                } else if (apellido.getText().toString().trim().equalsIgnoreCase("")){
                    apellido.setError("Falta Apellido del Alumno");
                } else if (email.getText().toString().trim().equalsIgnoreCase("")){
                    email.setError("Falta Email del Alumno");
                } else if (celular.getText().toString().trim().equalsIgnoreCase("")){
                    celular.setError("Falta Celular del Alumno");
                } else if (ciclo.getText().toString().trim().equalsIgnoreCase("")){
                    ciclo.setError("Falta Ciclo del Alumno");
                }else {
                    savePerson();
                    nombre.setText("");
                    apellido.setText("");
                    email.setText("");
                    celular.setText("");
                    ciclo.setText("");
                }
            }
        });

        listarAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IngresarEstudiantes.this, ListadoEstudiantes.class);
                startActivity(i);
            }
        });
    }

    private void savePerson(){
        String strNombre = nombre.getText().toString().trim();
        String strApellido = apellido.getText().toString().trim();
        String strEmail = email.getText().toString().trim();
        String strCelular = celular.getText().toString().trim();
        String strCiclo = ciclo.getText().toString().trim();
        dbHelper = new baseDatos(this);

        //create new person
        Estudiante person = new Estudiante(strNombre, strApellido, strEmail, strCelular, strCiclo);
        dbHelper.GuardarPersonas(person);


        Toast.makeText(this,"Ingresado correctamente",Toast.LENGTH_SHORT).show();
    }

}

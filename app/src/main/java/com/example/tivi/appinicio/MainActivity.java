package com.example.tivi.appinicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tivi.appinicio.VistaEstudiantes.IngresarEstudiantes;
import com.example.tivi.appinicio.VistaMaterias.IngresarMaterias;

public class MainActivity extends AppCompatActivity {

    Button Alumnos, Materias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Alumnos = (Button) findViewById(R.id.btnAlumnos);
        Materias = (Button) findViewById(R.id.btnMaterias);

        Alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IngresarEstudiantes.class);
                startActivity(i);
            }
        });



        Materias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IngresarMaterias.class);
                startActivity(i);
            }
        });



    }
}

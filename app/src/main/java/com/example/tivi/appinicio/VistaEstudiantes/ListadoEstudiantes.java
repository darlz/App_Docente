package com.example.tivi.appinicio.VistaEstudiantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tivi.appinicio.Adaptador.AdapterAlumnos;
import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.R;

public class ListadoEstudiantes extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private baseDatos dbHelper;
    private AdapterAlumnos adapter;
    private String filter = "nombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_estudiantes);


        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewEstudiantes);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);
    }

    private void populaterecyclerView(String filter){
        dbHelper = new baseDatos(this);
        adapter = new AdapterAlumnos(dbHelper.listaEstudiantes(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

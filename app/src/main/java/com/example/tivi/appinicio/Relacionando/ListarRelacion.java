package com.example.tivi.appinicio.Relacionando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.tivi.appinicio.Adaptador.AdapterRelacionMateriaAlumno;
import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.InsertParamtr;
import com.example.tivi.appinicio.R;

public class ListarRelacion extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private baseDatos dbHelper;
    private AdapterRelacionMateriaAlumno adapter;
    private String filter = "";

    int FK_Materia_Alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_relacion);

        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            FK_Materia_Alumno = b.getInt("FK_materia_alumno");
        }

        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewRelacion);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);


    }

    private void populaterecyclerView(String filter){
        dbHelper = new baseDatos(this);
        adapter = new AdapterRelacionMateriaAlumno(dbHelper.relacionAlumMateriaList(FK_Materia_Alumno), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

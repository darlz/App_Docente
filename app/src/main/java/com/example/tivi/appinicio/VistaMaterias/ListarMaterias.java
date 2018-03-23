package com.example.tivi.appinicio.VistaMaterias;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.tivi.appinicio.Adaptador.AdapterAlumnos;
import com.example.tivi.appinicio.Adaptador.AdapterMaterias;
import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.MainActivity;
import com.example.tivi.appinicio.R;

public class ListarMaterias extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private baseDatos dbHelper;
    private AdapterMaterias adapter;
    private String filter = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_materias);


        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewMaterias);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);


    }

    private void populaterecyclerView(String filter){
        dbHelper = new baseDatos(this);
        adapter = new AdapterMaterias(dbHelper.listaMaterias(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

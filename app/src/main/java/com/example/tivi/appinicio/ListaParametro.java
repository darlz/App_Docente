package com.example.tivi.appinicio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tivi.appinicio.Adaptador.AdapterParametros;
import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Parametro;

import java.util.ArrayList;

public class ListaParametro extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private baseDatos dbHelper;
    private AdapterParametros adapter;
    private String filter = "";

    int fkMateria;




    private ArrayList<Parametro> parametro = new ArrayList<Parametro>();

    ArrayList<Double> listaParametro = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parametro);

        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            fkMateria = b.getInt("MATERIA_ALUMNO_ID");
        }

        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewParametros);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);


    }

    private void populaterecyclerView(String filter){
        dbHelper = new baseDatos(this);
        adapter = new AdapterParametros(dbHelper.listaParametros(fkMateria), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void ConsultarNotas() {
        dbHelper = new baseDatos(this);
        parametro.addAll(dbHelper.listaParametros(fkMateria));

        for (int i = 0; i < parametro.size(); i++)
        {
            listaParametro.add(parametro.get(i).getNotaFinal());
            //double notes = listaParametro.get(i);
        }
    }

    public void SumaPromediada()
    {
        double notes=0, n=0, resultado=0;
        for (int i = 0; i < listaParametro.size(); i++)
        {
            notes += listaParametro.get(i);
        }

        resultado = notes/ listaParametro.size();
        //promedioGener.setText(String.valueOf(resultado));

        //ActualizarParametro(notes,resultado);


    }

}

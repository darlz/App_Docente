package com.example.tivi.appinicio.AddNota;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tivi.appinicio.Adaptador.AdapterMaterias;
import com.example.tivi.appinicio.Adaptador.AdapterNotas;
import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Estudiante;
import com.example.tivi.appinicio.Modelo.Nota;
import com.example.tivi.appinicio.Modelo.Parametro;
import com.example.tivi.appinicio.R;

import java.util.ArrayList;

public class ListarNotas extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private baseDatos dbHelper;
    private AdapterNotas adapter;
    private String filter = "";

    String namParam;
    int fk_PArametro, fkmaterias;
    double valPor, notFinale, promFinale;

    TextView valueTraido, promedioGener;
    Button btnPromediar;

    private ArrayList<Nota> estudiantes = new ArrayList<Nota>();

    ArrayList<Double> listaAlNotas = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            fk_PArametro = b.getInt("PARAMETRO_ID_NOTA_LISTA");
            namParam = b.getString("name");
            valPor = b.getDouble("valPorcent");
            fkmaterias = b.getInt("fkMAterias");
            notFinale = b.getDouble("noteFinal");
            promFinale = b.getDouble("promedioFin");
        }


        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewNotas);

        valueTraido = (TextView)findViewById(R.id.PorcentajeRecibido);
        promedioGener = (TextView)findViewById(R.id.txtPromediarNotas);

        btnPromediar = (Button)findViewById(R.id.PROMEDIAR);

        valueTraido.setText(String.valueOf(valPor));

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(fk_PArametro);

        btnPromediar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarNotas();
                SumaPromediada();
            }
        });

    }

    private void populaterecyclerView(int  parametros){
        dbHelper = new baseDatos(this);

        //Toast.makeText(this,"ID DEL PARAMETRO PARA VER NOTAS: "+parametros,Toast.LENGTH_SHORT).show();

        adapter = new AdapterNotas(dbHelper.listaNotas(parametros), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void ConsultarNotas() {
        dbHelper = new baseDatos(this);
        estudiantes.addAll(dbHelper.listaNotas(fk_PArametro));

        for (int i = 0; i < estudiantes.size(); i++)
        {
            listaAlNotas.add(estudiantes.get(i).getValorNota());
            //double notes = listaAlNotas.get(i);
        }
    }

    public void SumaPromediada()
    {
        double notes=0, n=0, resultado=0;
        for (int i = 0; i < listaAlNotas.size(); i++)
        {
            notes += listaAlNotas.get(i);
        }

        resultado = notes/listaAlNotas.size();
        promedioGener.setText(String.valueOf(resultado));

        ActualizarParametro(notes,resultado);


    }

   private void ActualizarParametro(double notes, double resultado){
        dbHelper = new baseDatos(this);

        //create new person
        Parametro parametro = new Parametro(namParam, valPor, fkmaterias,notes, resultado);

        dbHelper.ActualizarRegistroParametro(fk_PArametro,getApplicationContext(),parametro);
       //Toast.makeText(this,"NOTA FINAL: "+notes,Toast.LENGTH_SHORT).show();
       //Toast.makeText(this,"PROMEDIO FINAL: "+resultado,Toast.LENGTH_SHORT).show();

       /*Toast.makeText(this,"Notas Ingresadas correctamente",Toast.LENGTH_SHORT).show();
       Toast.makeText(this,"ID: "+fk_PArametro,Toast.LENGTH_SHORT).show();
       Toast.makeText(this,"NOMBRE: "+namParam,Toast.LENGTH_SHORT).show();
       Toast.makeText(this,"VALOR: "+valPor,Toast.LENGTH_SHORT).show();
       Toast.makeText(this,"FK MATERIA: "+fkmaterias,Toast.LENGTH_SHORT).show();
       Toast.makeText(this,"NOTA FINAL: "+notes,Toast.LENGTH_SHORT).show();
       Toast.makeText(this,"PROMEDIO FINAL: "+promFinale,Toast.LENGTH_SHORT).show();*/
    }
}

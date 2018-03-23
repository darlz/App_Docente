package com.example.tivi.appinicio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Parametro;

public class InsertParamtr extends AppCompatActivity {

    EditText nombre, value;
    TextView nombreMateriaAceptada;
    Button btnGuardar;
    private baseDatos dbHelper;


    int idMateria;
    String nameMateriaEnviada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_paramtr);

        nombre = (EditText)findViewById(R.id.txt_nombreParam);
        value = (EditText)findViewById(R.id.txt_Value_Param);

        btnGuardar = (Button)findViewById(R.id.btn_ingresarParam);
        nombreMateriaAceptada = (TextView)findViewById(R.id.txtMateriaRecibida);


        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            idMateria = b.getInt("MATERIA_ID");
            nameMateriaEnviada = b.getString("MATERI_NAME");
        }

        nombreMateriaAceptada.setText(nameMateriaEnviada);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePerson();
            }
        });

    }

    private void savePerson(){
        String strNombre = nombre.getText().toString().trim();
        double strvalue = Double.parseDouble(value.getText().toString().trim());
        double dividirValorPorcentaje = strvalue/100;
        dbHelper = new baseDatos(this);

        //create new person
        Parametro person = new Parametro(strNombre, dividirValorPorcentaje,idMateria,0,0);

        dbHelper.GuardarParametros(person);
        Toast.makeText(this,"Ingresado correctamente",Toast.LENGTH_SHORT).show();

        nombre.setText("");
        value.setText("");
    }
}

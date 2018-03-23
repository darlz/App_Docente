package com.example.tivi.appinicio.AddNota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Nota;
import com.example.tivi.appinicio.R;

public class InsertNote extends AppCompatActivity {

    EditText notaValue, promedioFinal;
    Button btnGuardarNota;
    Button btnListarNota;
    private baseDatos dbHelper;


    int fkParametro, fkMater;
    double vaPor, notaFin, promedioParam;
    String nameParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);

        notaValue = (EditText)findViewById(R.id.txt_valor_Nota);
        promedioFinal = (EditText)findViewById(R.id.txt_promedio_nota);

        btnGuardarNota = (Button)findViewById(R.id.btn_ingresarNota);
        btnListarNota = (Button)findViewById(R.id.btn_ListarNota);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            fkParametro = b.getInt("PARAMETRO_ID");
            nameParam = b.getString("NOMBREARAM");
            vaPor = b.getDouble("VALORPORC");
            fkMater = b.getInt("FKMATERIA");
            notaFin = b.getDouble("NOTAFINAL");
            promedioParam = b.getDouble("PROMEDIOFINALCONPORCENTAJE");
        }



        btnGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        btnListarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarData();
            }
        });
        /*Toast.makeText(this,"ID: "+fkParametro,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"NOMBRE: "+nameParam,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"VALOR: "+vaPor,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"FK MATERIA: "+fkMater,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"NOTA FINAL: "+notaFin,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"PROMEDIO FINAL: "+promedioParam,Toast.LENGTH_SHORT).show();*/
    }

    private void saveNote(){
        Double strNota = Double.parseDouble(notaValue.getText().toString().trim());
        String strPromedio = promedioFinal.getText().toString().trim();
        dbHelper = new baseDatos(this);

        //create new person
        Nota nota = new Nota(strNota, strPromedio, fkParametro);

        dbHelper.GuardarNotas(nota);
        Toast.makeText(this,"Notas Ingresadas correctamente",Toast.LENGTH_SHORT).show();
        notaValue.setText("");
        promedioFinal.setText("");
    }

    public void enviarData(){
        Intent i = new Intent(InsertNote.this, ListarNotas.class);
        i.putExtra("PARAMETRO_ID_NOTA_LISTA",fkParametro);
        i.putExtra("name",nameParam);
        i.putExtra("valPorcent",vaPor);
        i.putExtra("fkMAterias",fkMater);
        i.putExtra("noteFinal",notaFin);
        i.putExtra("promedioFin",promedioParam);
        startActivity(i);

    }
}

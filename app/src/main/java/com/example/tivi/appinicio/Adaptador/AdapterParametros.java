package com.example.tivi.appinicio.Adaptador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tivi.appinicio.AddNota.InsertNote;
import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Materia;
import com.example.tivi.appinicio.Modelo.Parametro;
import com.example.tivi.appinicio.R;
import com.example.tivi.appinicio.Relacionando.Relacion_Mater_Alum;

import java.util.List;

/**
 * Created by Tivi on 20/03/2018.
 */

public class AdapterParametros extends RecyclerView.Adapter<AdapterParametros.ViewHolder>
{
    private List<Parametro> ListadoParametros;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nombreParametro;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nombreParametro = (TextView) v.findViewById(R.id.txt_item_parametro_nombre);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterParametros(List<Parametro> myDataset, Context context, RecyclerView recyclerView) {
        ListadoParametros = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterParametros.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_parametro, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Parametro parametro = ListadoParametros.get(position);
        holder.nombreParametro.setText(parametro.getNombreParametro());

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Escoge una opcion");
                builder.setMessage("Actualizar o Eliminar parametro?");
                builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        // goToUpdateActivity(parametro.getIdEstudiante());

                    }
                });
                builder.setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatos dbHelper = new baseDatos(mContext);
                        dbHelper.EliminarRegistroParametros(parametro.getIdParametro(), mContext);

                        ListadoParametros.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, ListadoParametros.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Agregar nota", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addAlumno(parametro.getIdParametro(), parametro.getNombreParametro(),parametro.getValorPorcentual(), parametro.getFk_Materia(), parametro.getNotaFinal(), parametro.getPromedioParametro());
                        //dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

   /* private void goToUpdateActivity(int personId){
        Intent goToUpdate = new Intent(mContext, ActualizarDatosPersona.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }*/

    private void addAlumno(int materiaId, String NombreParam, double valPorcent, int fkMateria, double notaFin, double Promedio){
        Intent goToUpdate = new Intent(mContext, InsertNote.class);
        goToUpdate.putExtra("PARAMETRO_ID", materiaId);
        goToUpdate.putExtra("NOMBREARAM", NombreParam);
        goToUpdate.putExtra("VALORPORC", valPorcent);
        goToUpdate.putExtra("FKMATERIA", fkMateria);
        goToUpdate.putExtra("NOTAFINAL", notaFin);
        goToUpdate.putExtra("PROMEDIOFINALCONPORCENTAJE", Promedio);

        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ListadoParametros.size();
    }


}

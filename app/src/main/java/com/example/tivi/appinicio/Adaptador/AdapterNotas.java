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
import com.example.tivi.appinicio.Modelo.Nota;
import com.example.tivi.appinicio.Modelo.Parametro;
import com.example.tivi.appinicio.R;

import java.util.List;

/**
 * Created by Tivi on 21/03/2018.
 */

public class AdapterNotas extends RecyclerView.Adapter<AdapterNotas.ViewHolder>
{
    private List<Nota> ListadoParametros;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView valorNota;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            valorNota = (TextView) v.findViewById(R.id.txt_item_nota_valor);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterNotas(List<Nota> myDataset, Context context, RecyclerView recyclerView) {
        ListadoParametros = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterNotas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_notas, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Nota nota = ListadoParametros.get(position);
        holder.valorNota.setText(String.valueOf(nota.getValorNota()));

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Escoge una opcion");
                builder.setMessage("Actualizar o Eliminar nota?");
                builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        // goToUpdateActivity(nota.getIdEstudiante());

                    }
                });
                builder.setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatos dbHelper = new baseDatos(mContext);
                        dbHelper.EliminarRegistroNotas(nota.getIdNota(), mContext);

                        ListadoParametros.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, ListadoParametros.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ListadoParametros.size();
    }

}

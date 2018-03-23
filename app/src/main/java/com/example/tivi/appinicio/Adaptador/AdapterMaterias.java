package com.example.tivi.appinicio.Adaptador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.InsertParamtr;
import com.example.tivi.appinicio.Modelo.Materia;
import com.example.tivi.appinicio.R;
import com.example.tivi.appinicio.Relacionando.ListarRelacion;
import com.example.tivi.appinicio.Relacionando.Relacion_Mater_Alum;

import java.util.List;

/**
 * Created by Tivi on 18/03/2018.
 */

public class AdapterMaterias extends RecyclerView.Adapter<AdapterMaterias.ViewHolder>
{

    private List<Materia> ListadoMaterias;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView materiaNameTxtV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            materiaNameTxtV = (TextView) v.findViewById(R.id.txt_item_nombre_materia);
        }
    }

    public void add(int position, Materia materia) {
        ListadoMaterias.add(position, materia);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        ListadoMaterias.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterMaterias(List<Materia> myDataset, Context context, RecyclerView recyclerView) {
        ListadoMaterias = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMaterias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.items_materia, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Materia materia = ListadoMaterias.get(position);
        holder.materiaNameTxtV.setText(materia.getNombreMateria());

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Escoge una opcion");
                builder.setMessage("Actualizar o Eliminar Materia?");

                builder.setPositiveButton("Crear Parametro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        addParam(materia.getIdMateria(), materia.getNombreMateria());

                        //go to update activity
                        // goToUpdateActivity(materia.getIdEstudiante());

                    }
                });
                builder.setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatos dbHelper = new baseDatos(mContext);
                        dbHelper.EliminarRegistroMaterias(materia.getIdMateria(), mContext);

                        ListadoMaterias.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, ListadoMaterias.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Ver Alumnos", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addAlumno(materia.getIdMateria(), materia.getNombreMateria());
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

   private void addAlumno(int materiaId, String name){
        Intent goToUpdate = new Intent(mContext, Relacion_Mater_Alum.class);
       goToUpdate.putExtra("MATERIA_ID", materiaId);
       goToUpdate.putExtra("MATERI_NAME", name);
        mContext.startActivity(goToUpdate);
    }

    private void addParam(int materiaId, String name){
        Intent goToUpdate = new Intent(mContext, InsertParamtr.class);
        goToUpdate.putExtra("MATERIA_ID", materiaId);
        goToUpdate.putExtra("MATERI_NAME", name);

        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ListadoMaterias.size();
    }



}

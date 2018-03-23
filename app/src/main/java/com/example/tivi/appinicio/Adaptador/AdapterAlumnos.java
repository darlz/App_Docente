package com.example.tivi.appinicio.Adaptador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tivi.appinicio.BaseDatos.baseDatos;
import com.example.tivi.appinicio.Modelo.Estudiante;
import com.example.tivi.appinicio.R;

import java.util.List;

/**
 * Created by Tivi on 17/03/2018.
 */

public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.ViewHolder>
{
    private List<Estudiante> ListaDeAlumnos;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nombreAlumno;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nombreAlumno = (TextView) v.findViewById(R.id.txt_item_nombre);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterAlumnos(List<Estudiante> myDataset, Context context, RecyclerView recyclerView) {
        ListaDeAlumnos = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterAlumnos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.items_alumnos, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Estudiante person = ListaDeAlumnos.get(position);
        holder.nombreAlumno.setText(person.getNombre()+"  "+ person.getApellido());

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Elegir opción");

                builder.setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatos dbHelper = new baseDatos(mContext);
                        dbHelper.EliminarRegistroPersonas(person.getIdEstudiante(), mContext);

                        ListaDeAlumnos.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, ListaDeAlumnos.size());
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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ListaDeAlumnos.size();
    }
}

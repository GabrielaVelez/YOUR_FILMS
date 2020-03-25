package com.example.a1erparcial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PeliculaAdapter extends ArrayAdapter<Pelicula> {

    // private ArrayList<Pelicula> peli;
    private LayoutInflater inflater = null;

    public PeliculaAdapter(@NonNull Context context,  @NonNull ArrayList<Pelicula> peli) {
        super(context, 0, peli);
        //   this.peli = peli;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.listadop, null);
            // Do some initialization

            //Retrieve the view on the item layout and set the value.
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //Retrieve your object
        Pelicula data = (Pelicula) getItem(position);


        viewHolder.nombre.setText(data.getNombre());
        viewHolder.director.setText(data.getDirector());
        viewHolder.idioma.setText(data.getIdioma());
        viewHolder.genero.setText(data.getGenero());

        return view;

    }

    private class ViewHolder
    {
        private final TextView nombre;
        private final TextView director;
        private final TextView idioma;
        private final TextView genero;

        private ViewHolder(View view)
        {
            nombre = (TextView) view.findViewById(R.id.nombre);
            director = (TextView) view.findViewById(R.id.director);
            idioma = (TextView) view.findViewById(R.id.idioma);
            genero = (TextView) view.findViewById(R.id.genero);
        }
    }
}

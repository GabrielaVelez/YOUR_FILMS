package com.example.a1erparcial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView listado;
    ArrayList<String> peliculas;
    ArrayAdapter<Pelicula> arrayAdapter;
    ArrayList<Pelicula> peli = new ArrayList<Pelicula>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listado = findViewById(R.id.listado);
        Intent in = getIntent();

        //Aqui se se recibe la lista de pelicula que viene de Agregar Peliculas pero esta puede ser vacía o nula
        peli = in.getParcelableArrayListExtra("peli");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Agg_peliculas.class);
                i.putParcelableArrayListExtra("peli", peli);
                startActivity(i);
            }
        });

        if (peli==null) {
            peli = new ArrayList<Pelicula>();
            Toast.makeText(this, "Agregar Peliculas" , Toast.LENGTH_LONG).show();
        }
        arrayAdapter = new PeliculaAdapter(this, peli);

        listado.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.eliminar:
                if (peli==null){
                    Toast.makeText(this, "No hay dato que eliminar" , Toast.LENGTH_LONG).show();
                }else {
                    if (peli.size()>0)
                    peli.remove(0);
                    else
                        Toast.makeText(this, "No hay dato que eliminar" , Toast.LENGTH_LONG).show();
                }
                           arrayAdapter.notifyDataSetChanged();
                break;

            case R.id.ordgenero:

                    Collections.sort(peli, new Comparator<Pelicula>() {
                        @Override
                        public int compare(Pelicula o1, Pelicula o2) {
                            return o1.getGenero().compareTo(o2.getGenero());
                        }

                    });

            arrayAdapter.notifyDataSetChanged();
                    break;

            case R.id.ordxnombre:

                    Collections.sort(peli, new Comparator<Pelicula>() {
                        @Override
                        public int compare(Pelicula o1, Pelicula o2) {
                            return o1.getNombre().compareTo(o2.getNombre());
                        }
                    });

            arrayAdapter.notifyDataSetChanged();
                break;

            case R.id.invlista:

                    Collections.reverse(peli);

            arrayAdapter.notifyDataSetChanged();
                break;

        }
        return super.onOptionsItemSelected(item);
    }




}

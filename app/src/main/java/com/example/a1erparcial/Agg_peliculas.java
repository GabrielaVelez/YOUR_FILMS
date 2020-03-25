package com.example.a1erparcial;

import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Agg_peliculas extends AppCompatActivity implements View.OnClickListener {


    Button guardar, cancelar;

    ArrayAdapter<String> adaptadorgenero;
    EditText nombre, director;
    RadioGroup idioma;
    RadioButton espanol, ingles;
    Spinner genero;
    String gen;
    ArrayList<Pelicula> peli= new ArrayList<Pelicula>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agg_peliculas);

        Intent in = getIntent();
        peli = in.getParcelableArrayListExtra("peli");
        //Se verfica si la lista de peliculas es nula
        if (peli==null)
        {
            peli= new ArrayList<Pelicula>();

        }
        espanol = findViewById(R.id.rbespañol);
        ingles = findViewById(R.id.rbingles);

        guardar = findViewById(R.id.guardar);
        cancelar = findViewById(R.id.cancelar);

        nombre = findViewById(R.id.nombre);
        director = findViewById(R.id.director);
        idioma = findViewById(R.id.idioma);
        genero = findViewById(R.id.genero);
        String [] generos = {"...", "Accion", "comedia", "psicologico", "romance", "suspenso", "terror","tragedia"};
         adaptadorgenero = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,generos);
        genero.setAdapter(adaptadorgenero);

        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        registerForContextMenu(nombre);
        registerForContextMenu(director);

    }

    public void onClick(View v) {

        switch(v.getId()){

            case R.id.guardar:


               gen= genero.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), gen, Toast.LENGTH_SHORT).show();
              AlertDialog.Builder builder = new AlertDialog.Builder(Agg_peliculas.this);
                builder.setMessage("¿Desea guardar la pelicula?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String idioma2="" ;
                                if (espanol.isChecked()) {
                                    idioma2="español";
                                } else
                                if (ingles.isChecked()) {
                                    idioma2="ingles";
                                }
                                peli.add(new Pelicula(nombre.getText().toString(), director.getText().toString(), idioma2, gen));
                                Toast.makeText(getApplicationContext(), "Pelicula guardada", Toast.LENGTH_SHORT).show();
                       }

                   });
               AlertDialog alerta = builder.create();
               alerta.show();

                break;
            case R.id.cancelar:


                cancelar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        nombre.setText("");
                        director.setText("");

                    }
                });

                break;




        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agg_peliculas, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();


        if (v.getId() == R.id.nombre || v.getId() == R.id.director  ) {
            inflater.inflate(R.menu.menu_random, menu);
            v.setBackgroundColor(getRandomColor());

                 }
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.color:
                if (item.getItemId() == R.id.nombre) {
                    getRandomColor();
                } else if (item.getItemId() == R.id.director) {
                    getRandomColor();
                }

                break;


        }
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case R.id.mnulistado: Intent i = new Intent(Agg_peliculas.this, MainActivity.class);
                i.putParcelableArrayListExtra("peli", peli);
                startActivity(i);
                break;
            case R.id.mnumayuscula:
                nombre.setText(nombre.getText().toString().toUpperCase());
                director.setText(nombre.getText().toString().toUpperCase());

        }

        return super.onOptionsItemSelected(item);
    }

}

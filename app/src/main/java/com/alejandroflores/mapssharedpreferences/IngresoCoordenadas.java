package com.alejandroflores.mapssharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngresoCoordenadas extends AppCompatActivity {

    private Button buttonGuardarCoordenadas, buttonMostrarMapa;
    private EditText editTextLongitud, editTextLatitud;
    private String longitud, latitud;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_coordenadas);

        buttonGuardarCoordenadas = findViewById(R.id.buttonGuardar);
        buttonMostrarMapa = findViewById(R.id.buttonMostrarMapa);

        editTextLatitud = findViewById(R.id.editTextLatitud);
        editTextLongitud = findViewById(R.id.editTextLongitud);

        buttonGuardarCoordenadas.setOnClickListener((View v) -> {
            getCoordenadas();
            guardarCoordenadasSP(longitud, latitud);
        });

        buttonMostrarMapa.setOnClickListener((View v) -> mostrarMapa());

        sharedPref = this.getSharedPreferences("Coordenadas", Context.MODE_PRIVATE);

    }

    private void getCoordenadas(){
        longitud = editTextLongitud.getText().toString();
        latitud = editTextLatitud.getText().toString();

        editTextLongitud.setText("");
        editTextLatitud.setText("");
    }

    private void guardarCoordenadasSP(String longitud, String latitud){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Longitud", longitud);
        editor.putString("Latitud", latitud);
        editor.commit();
        mostrarCoordenadasGuardadas();
    }

    private void mostrarCoordenadasGuardadas(){
        String longi = sharedPref.getString("Longitud", "undefined");
        String lat = sharedPref.getString("Latitud", "undefined");

        Toast.makeText(this, "Longitud: " + longi + " Latitud: " + lat, Toast.LENGTH_SHORT).show();
    }

    private void mostrarMapa(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}

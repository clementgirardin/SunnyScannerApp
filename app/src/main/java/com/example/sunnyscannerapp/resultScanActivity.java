package com.example.sunnyscannerapp;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class resultScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);

        //----------------------------------Séparation partie du code----------------------------------------\\
        // Récupération des coordonnées envoyées par l'Intent
        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);


        //----------------------------------Séparation partie du code----------------------------------------\\
        // Création instances d'infoFragment et mapFragment
        InfoFragment fragmentInfos = new InfoFragment();

        // Création d'un objet bundle pour y stocker les coordonées
        Bundle args = new Bundle();
        args.putDouble("latitude", latitude);
        args.putDouble("longitude", longitude);
        // Passe le bundle en tant qu'argument aux deux fragments
        fragmentMap.setArguments(args);
        fragmentInfos.setArguments(args);

        // Remplace activity main par le fragment map
        getSupportFragmentManager().beginTransaction().replace(R.id.resultScan, fragmentMap).commit();
        // Ajout du fragment infos après la map
        getSupportFragmentManager().beginTransaction().add(R.id.resultScan, fragmentInfos).commit();
        Log.d("result1", "fragments ajoutés");
    }
}
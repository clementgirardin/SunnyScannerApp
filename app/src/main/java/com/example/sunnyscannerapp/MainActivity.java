package com.example.sunnyscannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Création d'une instance d'infoFragment
        mainFragment fragmentMain = new mainFragment();
        // Remplace activity main par le fragment map
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMain).commit();
    }
}
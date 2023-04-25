package com.example.sunnyscannerapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeQrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code_qr);

        // Initialisation du scanner
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    /**
     * SET le text renvoyé par le scan du code QR
     * @param result
     */
    @Override
    public void handleResult(Result result) {
//        MainActivity.txt_resultat.setText(result.getText());
//        // Retour à la page d'accueil après le scan pour visualiser le résultat
//        onBackPressed();


        // Affichage de la map
        // Récupération données code QR
        String resultQRcode = result.getText();
        // Affichage données récupérées dans la textView
        mainFragment.txt_resultat.setText(resultQRcode);

        // Vérifie si la chaîne commence par "geo:"
        if (resultQRcode.startsWith("geo:")) {
            // Extrait la chaîne de caractère "geo:" renvoyée par le code QR
            resultQRcode = resultQRcode.substring(4);
        }

        // Sépare la latitude de la longitude retournés par le code QR
        String[] localisation = resultQRcode.split(",");

        // Récupération latitude & longitude après la séparation des valeurs
        double latitude = Double.parseDouble(localisation[0]);
        double longitude = Double.parseDouble(localisation[1]);

        // Start l'activité et fournis la latitude et longitude en paramètre
        Intent intent = new Intent(getApplicationContext(), resultScanActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Définit l'appel de la fonction handleResult à chaque scan du scanneur
        scannerView.setResultHandler(this);
        // Démarre la caméra
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Arrête la caméra
        scannerView.stopCamera();
    }
}
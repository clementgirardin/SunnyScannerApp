package com.example.sunnyscannerapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.sunnyscannerapp.R;

public class mainFragment extends Fragment {

    Button btn_scan;
    public static TextView txt_resultat;
    private static final int PERMISSION = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        // Récupération par id
        btn_scan = view.findViewById(R.id.btn_scan);
        txt_resultat = view.findViewById(R.id.txt_resultat);

        // Lance l'activité scan_code_qr au clique du bouton
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vérifie si les permissions de la caméra, de la localisation et des SMS sont autorisées
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                    // Si l'une des permissions n'a pas été accordée, demande les permissions nécessaires
                    requestCameraPermission();
//                } else {
                    // Si toutes les permissions sont accordées, lance ScanCodeQrActivity
//                    startActivity(new Intent(getContext(),ScanCodeQrActivity.class));
                }

            }
        });
        return view;
    }


    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS}, PERMISSION);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                // Si toutes les permissions sont accordées, lance ScanCodeQrActivity
//                 startActivity(new Intent(getContext(),ScanCodeQrActivity.class));
            } else {
                // Si l'une des permissions est refusée, affiche un message d'erreur
                Toast.makeText(getActivity(), "Permissions non autorisées", Toast.LENGTH_SHORT).show();
                // Demande a nouveau les permissions
                requestCameraPermission();
            }
        }
    }
}
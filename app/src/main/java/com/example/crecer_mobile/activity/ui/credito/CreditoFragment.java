package com.example.crecer_mobile.activity.ui.credito;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crecer_mobile.R;

import java.net.URL;

public class CreditoFragment extends Fragment {
    View vista;
    Button btncotizar, btndescargar;
    private String url1, url2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credito, container, false);


        //logico y visual
        btncotizar = (Button) vista.findViewById(R.id.button4);
        btndescargar = (Button) vista.findViewById(R.id.button5);


        //enlacez
        url1 = "https://crecer.fin.ec/cotizador/MicroCalculadora.html";
        url2 =  "https://crecer.fin.ec/wp-content/uploads/2021/08/SOLICITUD-CREDITO-2020-ENERO2.pdf";

                //boton cotizar credito
        btncotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =Uri.parse(url1);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //boton descargar el archivo
        btndescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =Uri.parse(url2);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        return vista;
    }
}

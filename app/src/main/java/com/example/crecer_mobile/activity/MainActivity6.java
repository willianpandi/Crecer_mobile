package com.example.crecer_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crecer_mobile.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity6 extends AppCompatActivity {

    TextView tx1, tx2;
    Button btnoffline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        //logico y grafico
        tx1 = (TextView) findViewById(R.id.textView_offline1_2);
        tx2 = (TextView) findViewById(R.id.textView_offline2_2);
        btnoffline = (Button) findViewById(R.id.buttonoffline_2);

        //deshabilitar barra de navegacion
        getSupportActionBar().hide();

        //boton volver a intentar
        btnoffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    Toast.makeText(MainActivity6.this, "Se ha restablecido la conexión a internet.".toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(MainActivity6.this);
        alerta.setTitle("Salir de la aplicación")
                .setIcon(R.drawable.ic_error)
                .setMessage("¿Deseas salir de la aplicación Crecer Móvil?")
                .setCancelable(false)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity6.this, "¡GRACIAS!\n Por utilizar Crecer Móvil", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        alerta.show();
    }
}
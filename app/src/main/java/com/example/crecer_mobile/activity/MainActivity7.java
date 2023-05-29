package com.example.crecer_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crecer_mobile.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity7 extends AppCompatActivity {

    TextView tx1, tx2;
    Button btnsession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        //logico y grafico
        tx1 = (TextView) findViewById(R.id.textView_sesion1_2);
        tx2 = (TextView) findViewById(R.id.textView_session2_2);
        btnsession = (Button) findViewById(R.id.buttonsession_2);

        //deshabilitar barra de navegacion
        getSupportActionBar().hide();
        btnsession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity7.this, MainActivity2.class));
                Toast.makeText(MainActivity7.this, "Se ha cerrado la sesión correctamente.".toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(MainActivity7.this);
        alerta.setTitle("Salir de la aplicación")
                .setIcon(R.drawable.ic_error)
                .setMessage("¿Deseas salir de la aplicación Crecer Móvil?")
                .setCancelable(false)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity7.this, "¡GRACIAS!\n Por utilizar Crecer Móvil", Toast.LENGTH_SHORT).show();
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
package com.example.crecer_mobile.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crecer_mobile.R;

public class MainActivity2 extends AppCompatActivity {
    EditText txt1, txt2;
    Button btnIngresar;
    ImageView imageViewTwitter, imageViewFacebook, imageViewYoutube, imageViewLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //logico vs grafico
        btnIngresar = (Button) findViewById(R.id.button);
        txt1 = (EditText) findViewById(R.id.editTextTextPersonName);
        txt2 = (EditText) findViewById(R.id.editTextTextPassword);
        imageViewTwitter = (ImageView) findViewById(R.id.imagetwitter);
        imageViewFacebook = (ImageView) findViewById(R.id.imagefacebook);
        imageViewYoutube = (ImageView) findViewById(R.id.imageyoutube);
        imageViewLink = (ImageView) findViewById(R.id.imagelink);

        //deshabilitar barra de navegacion
        getSupportActionBar().hide();

        //boton ingresar
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt1.getText().toString().equals("") || txt2.getText().toString().equals(""))
                {
                    //Toast.makeText(getApplicationContext(), "¡Existen campos vacios!",Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity2.this);
                    alerta.setTitle("¡Existen campos vacios!")
                            .setMessage("Ingrese los campos requeridos")
                            .setIcon(android.R.drawable.ic_dialog_info);
                    alerta.show();

                    txt1.setText("");
                    txt2.setText("");
                    txt1.requestFocus();
                }
                else
                {
                    if(txt1.getText().toString().equals("Willian") && txt2.getText().toString().equals("12345"))
                    {
                        startActivity(new Intent(MainActivity2.this, InicioActivity.class));
                        //Intent pantallamenu = new Intent(MainActivity2.this, InicioActivity.class);
                        //startActivity(pantallamenu);
                        finish();
                    }
                    else
                    {
                        //Toast.makeText(getApplicationContext(),"¡Credenciales incorrectas!",Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity2.this);
                        alerta.setTitle("¡Credenciales incorrectas!")
                                .setMessage("Ingrese el usuario y contraseña correspondiente")
                                .setIcon(android.R.drawable.ic_dialog_alert);
                        alerta.show();

                        txt1.setText("");
                        txt2.setText("");
                        txt1.requestFocus();
                    }
                }
            }
        });


        //Redes sociales
        imageViewTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri1 = Uri.parse("https://twitter.com");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
            }
        });

        imageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri2 = Uri.parse("https://www.facebook.com/CoacCrecer");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent2);
            }
        });

        imageViewYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri3 = Uri.parse("https://twitter.com");
                Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(intent3);
            }
        });

        imageViewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri4 = Uri.parse("https://twitter.com");
                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(intent4);
            }
        });
    }

}
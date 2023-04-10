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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;

import java.util.HashMap;
import java.util.Map;

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
                validarusuario("http://192.168.100.36:80/crecer/validar_usuario.php");
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


    private void validarusuario(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()) {
                    startActivity(new Intent(MainActivity2.this, InicioActivity.class));
                    finish();
                } else {
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity2.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
              Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("usuario", txt1.getText().toString());
            parameters.put("password", txt2.getText().toString());
            return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
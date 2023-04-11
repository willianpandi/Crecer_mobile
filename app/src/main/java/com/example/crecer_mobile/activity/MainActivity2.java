package com.example.crecer_mobile.activity;

import android.annotation.SuppressLint;
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

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.http.Url;

public class MainActivity2 extends AppCompatActivity {
    EditText txtuser, txtpass;
    Button btnIngresar;
    ImageView imageViewTwitter, imageViewFacebook, imageViewYoutube, imageViewLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //logico vs grafico
        btnIngresar = (Button) findViewById(R.id.button);
        txtuser = (EditText) findViewById(R.id.editTextTextPersonName);
        txtpass = (EditText) findViewById(R.id.editTextTextPassword);

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

                if (txtuser.getText().toString().isEmpty() || txtpass.getText().toString().isEmpty())
                {
                    if (txtuser.getText().toString().isEmpty()){
                        txtuser.setError("Usuario Obligatorio");
                    }
                    if (txtpass.getText().toString().isEmpty()){
                        txtpass.setError("Contraseña Obligatorio");
                    }
                    //Toast.makeText(getApplicationContext(), "¡Existen campos vacios!",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity2.this);
                    alerta.setTitle("¡Existen campos vacios!")
                            .setMessage("Ingrese los campos requeridos")
                            .setIcon(android.R.drawable.ic_dialog_info);
                    alerta.show();

                }
                else
                {
                    validarusuario("https://proyectoflol.000webhostapp.com/validar_usuario.php");
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
                            .setIcon(R.drawable.ic_error);
                    alerta.show();

                    txtuser.setText("");
                    txtpass.setText("");
                    txtuser.requestFocus();
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
            parameters.put("user", txtuser.getText().toString());
            parameters.put("password", txtpass.getText().toString());
            return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    /**
     * Enables https connections
     */
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}


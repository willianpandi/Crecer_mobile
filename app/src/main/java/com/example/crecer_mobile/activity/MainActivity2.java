package com.example.crecer_mobile.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

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
    TextInputLayout txtInputUsuario, txtInputPassword;
    Button btnIngresar;
    ImageView imageViewTwitter, imageViewFacebook, imageViewYoutube, imageViewLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //logico vs grafico
        btnIngresar = (Button) findViewById(R.id.button);

        txtuser = findViewById(R.id.edtUser);
        txtpass = findViewById(R.id.edtPassword);
        txtInputUsuario = findViewById(R.id.editTextTextPersonName);
        txtInputPassword = findViewById(R.id.editTextTextPassword);


        imageViewTwitter = (ImageView) findViewById(R.id.imagetwitter);
        imageViewFacebook = (ImageView) findViewById(R.id.imagefacebook);
        //imageViewYoutube = (ImageView) findViewById(R.id.imageyoutube);
        //imageViewLink = (ImageView) findViewById(R.id.imagelink);

        //deshabilitar barra de navegacion
        getSupportActionBar().hide();

        //boton ingresar
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (validar()){
                        validarusuario("https://proyectoflol.000webhostapp.com/validar_usuario.php");
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "¡Se ha producido un error al intentar loguearte!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //METODOS para no mostrar las notificaciones de los campos vacios
        txtuser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtInputUsuario.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtInputPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //LINK a Redes sociales
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
/*
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

 */
    }

    private void validarusuario(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    startActivity(new Intent(MainActivity2.this, InicioActivity.class));
                    finish();
                } else {
                    MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(MainActivity2.this);
                    alerta.setTitle("Credenciales incorrectas")
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


    //METODO PARA VALIDAR los campos
        private boolean validar(){
            boolean retorno=true;
            String usuario, password;
            usuario = txtuser.getText().toString();
            password = txtpass.getText().toString();
            if (usuario.isEmpty()) {
                txtInputUsuario.setError("Ingrese su usario y/o correo electrónico");
                retorno = false;
            } else {
                txtInputUsuario.setErrorEnabled(false);
            }
            if (password.isEmpty()) {
                txtInputPassword.setError("Ingrese su contraseña");
                retorno = false;
            } else {
                txtInputPassword.setErrorEnabled(false);
            }
            return retorno;
        }

    //Enables https connections
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


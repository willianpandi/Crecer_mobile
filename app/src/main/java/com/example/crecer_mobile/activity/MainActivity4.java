package com.example.crecer_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity4 extends AppCompatActivity {

    TextView txtcedula, txtcliente, txtfecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //logico y grafico
        txtcedula = (TextView) findViewById(R.id.textView_Log_Cedula);
        txtcliente = (TextView) findViewById(R.id.textView_InicioNombre);
        txtfecha = (TextView) findViewById(R.id.textView_Log_Fecha);

        //guardar el temporizador
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        long currentTime = System.currentTimeMillis();
        editor.putLong("session_start_time", currentTime);
        editor.apply();

        //funcion para quitar Action Bar
        getSupportActionBar().hide();

        //traer el dato de login
        SharedPreferences shared = MainActivity4.this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String dato = shared.getString("string", "");

        buscarusuario("https://computacionmovil2.000webhostapp.com/buscar_usuario.php?id="+ dato);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity4.this, InicioActivity.class));

                String textocedula = txtcedula.getText().toString();
                String textocliente = txtcliente.getText().toString();
                String textofecha = txtfecha.getText().toString();

                SharedPreferences preferences = getSharedPreferences("preferences2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("string1",textocedula);
                editor.putString("string2",textocliente);
                editor.putString("string3",textofecha);
                editor.commit();
                finish();
            }
        }, 5000);

    }

    private void buscarusuario(String URL)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i=0 ; i<response.length(); i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtcedula.setText(jsonObject.getString("n_cuenta"));
                        txtcliente.setText(jsonObject.getString("nombre"));
                        txtfecha.setText(jsonObject.getString("fecha_naci"));
                    }
                    catch (Exception err)
                    {
                        Toast.makeText(MainActivity4.this, "Error:"+err, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity4.this, "Error:"+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
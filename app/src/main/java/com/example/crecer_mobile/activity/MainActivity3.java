package com.example.crecer_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.ui.consultas.ConsultasFragment;
import com.example.crecer_mobile.entity.Detalle;
import com.example.crecer_mobile.entity.adapter.AdapterDetalles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    TextView txtcuenta, txtnombre, txtcedula, txtsaldo;
    RecyclerView recyclerView2;
    List<Detalle> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        //cambiar el tema de la pantalla accion bar
        getSupportActionBar().setTitle("Detalle de Cuenta");

        //icono en el accion bar
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_usuario);

        //Traer datos del adapter
        String cuenta = "", nombre =" ", cedula="", saldo="";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            cuenta = extras.getString("cuenta");
            nombre = extras.getString("nombre");
            cedula = extras.getString("cedula");
            saldo = extras.getString("saldo");
        }

        txtcuenta = (TextView) findViewById(R.id.textView14);
        txtnombre = (TextView) findViewById(R.id.textView12);
        txtcedula = (TextView) findViewById(R.id.textView13);
        txtsaldo = (TextView) findViewById(R.id.textView15);

        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview_detalle);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        lista = new ArrayList<Detalle>();

        txtcuenta.setText(cuenta);
        txtnombre.setText(nombre);
        txtcedula.setText(cedula);
        txtsaldo.setText(saldo);


        //Mostrar detalle
        mostrarDetalleCuentas("https://computacionmovil2.000webhostapp.com/mostrar_productos.php");


        //Boton aceptar


    }

    ///////////////////// MUESTRA TODOS LOS DETALLES /////////////////////////////////////

    private void mostrarDetalleCuentas(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Detalle detalle;
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i =0; i< jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        lista.add(new Detalle(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("n_cuenta"),
                                (float)jsonObject.optDouble("saldo")

                        ));
                    }
                    AdapterDetalles adapterDetalles = new AdapterDetalles((ArrayList<Detalle>) lista);
                    recyclerView2.setAdapter(adapterDetalles);
                }catch (JSONException e){
                    Toast.makeText(MainActivity3.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,error.toString(),Toast.LENGTH_SHORT).show();
            }

        });
        Volley.newRequestQueue(MainActivity3.this).add(stringRequest);
    }
}
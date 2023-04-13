package com.example.crecer_mobile.activity.ui.consultas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.InicioActivity;
import com.example.crecer_mobile.activity.MainActivity2;
import com.example.crecer_mobile.adapter.AdapterCuentas;
import com.example.crecer_mobile.entity.Cuenta;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.example.crecer_mobile.databinding.FragmentGalleryBinding;


public class ConsultasFragment extends Fragment {

    View vista;
    RecyclerView recyclerView;
    Button btnbuscar;
    EditText txtbuscar;
    List<Cuenta> lista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_consultas, container, false);

        //logico y GUI
        recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerview_cuentas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnbuscar = (Button) vista.findViewById(R.id.button2);
        txtbuscar = (EditText) vista.findViewById(R.id.editTextNumber);


        lista = new ArrayList<Cuenta>();


        //BTON buscar}
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtbuscar.getText().toString().isEmpty()) {
                    buscarcuenta("https://computacionmovil2.000webhostapp.com/buscar_producto.php?id="+txtbuscar.getText().toString()+ " ");
                    txtbuscar.setText("");
                    txtbuscar.requestFocus();
                }
                else
                {
                    Toast.makeText(getActivity(), "El campo ID no puede estar vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Mostrar producto
        //mostrarcuentas("https://computacionmovil2.000webhostapp.com/mostrar_productos.php");

        return vista;

    }

    /////////////////////////////
    private void buscarcuenta(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i=0; i < response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(i);
                        lista.add(new Cuenta(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("n_cuenta"),
                                jsonObject.getString("nombre"),
                                (float)jsonObject.optDouble("saldo")
                        ));

                    } catch (JSONException error){
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                AdapterCuentas adapterCuentas = new AdapterCuentas((ArrayList<Cuenta>) lista);
                recyclerView.setAdapter(adapterCuentas);
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),"No Existe el Dato",Toast.LENGTH_SHORT).show();
                }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }



    /////////////////////
    private void mostrarcuentas(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Cuenta cuenta;
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i =0; i< jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        lista.add(new Cuenta(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("n_cuenta"),
                                jsonObject.getString("nombre"),
                                (float)jsonObject.optDouble("saldo")
                        ));
                    }
                    AdapterCuentas adapterCuentas = new AdapterCuentas((ArrayList<Cuenta>) lista);
                    recyclerView.setAdapter(adapterCuentas);

                }catch (JSONException e){
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

}
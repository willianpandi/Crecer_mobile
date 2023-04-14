package com.example.crecer_mobile.activity.ui.consultas;

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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.adapter.AdapterCuentas;
import com.example.crecer_mobile.entity.Cuenta;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//import com.example.crecer_mobile.databinding.FragmentGalleryBinding;


public class ConsultasFragment extends Fragment {

    View vista;
    RecyclerView recyclerView;
    Button btnbuscar;
    EditText txtbuscar;
    TextInputLayout txtInputUsuario;
    List<Cuenta> lista;
    LinearProgressIndicator carga;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_consultas, container, false);

        //logico y GUI
        recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerview_cuentas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnbuscar = (Button) vista.findViewById(R.id.button2);
        txtbuscar = (EditText) vista.findViewById(R.id.edtbusqueda);
        txtInputUsuario = (TextInputLayout) vista.findViewById(R.id.editTextTextPersonName_busqueda);

        carga = (LinearProgressIndicator) vista.findViewById(R.id.carga_lineal);

        //BTON buscar}
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtbuscar.getText().toString().isEmpty()) {
                    lista = new ArrayList<Cuenta>();
                    buscarcuenta("https://computacionmovil2.000webhostapp.com/buscar_producto.php?id="+txtbuscar.getText().toString()+ " ");
                    carga.setVisibility(View.VISIBLE);
                    txtbuscar.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(), "Ingrese un número de cédula", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Mostrar producto
        //mostrarcuentas("https://computacionmovil2.000webhostapp.com/mostrar_productos.php");

        return vista;

    }

    ///////////// BUSCA CUENTA POR NUMERO DE CEDULA ////////////
    private void buscarcuenta(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Cuenta cuenta;
               for (int i=0; i < response.length();i++){
                    JSONObject jsonObject = null;
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
                carga.setVisibility(View.INVISIBLE);
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    carga.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"No Existe el Dato",Toast.LENGTH_SHORT).show();
                }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }



    ///////////////////// MUESTRA TODAS LAS CUENTAS /////////////////////////////////////
    /*
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
    }*/

}
package com.example.crecer_mobile.activity.ui.consultas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.MainActivity2;
import com.example.crecer_mobile.activity.MainActivity5;
import com.example.crecer_mobile.activity.MainActivity7;
import com.example.crecer_mobile.entity.adapter.AdapterCuentas;
import com.example.crecer_mobile.entity.Cuenta;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
                             ViewGroup container, Bundle savedInstanceState){
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            startActivity(new Intent(getActivity(), MainActivity5.class));
        } else {
            vista = inflater.inflate(R.layout.fragment_consulta, container, false);

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
                    if (!txtbuscar.getText().toString().isEmpty()) {
                        lista = new ArrayList<Cuenta>();
                        buscarcuenta("https://computacionmovil2.000webhostapp.com/buscar_cuenta.php?id=" + txtbuscar.getText().toString());
                        carga.setVisibility(View.VISIBLE);
                        txtbuscar.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Ingrese un número de cédula", Toast.LENGTH_SHORT).show();
                    }
                    cerrarTeclado();
                }
            });
        }
        return vista;
    }

    ///////////// BUSCA CUENTA POR NUMERO DE CEDULA ////////////
    private void buscarcuenta(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Cuenta cuenta;
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        lista.add(new Cuenta(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("n_cuenta"),
                                jsonObject.getString("nombre"),
                                jsonObject.getString("email")
                        ));

                    } catch (JSONException error) {
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
                if (error instanceof NetworkError) {
                    startActivity(new Intent(getActivity(), MainActivity5.class));
                    //Toast.makeText(getActivity(), "En este momento no tienes conexión a internet", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getActivity(), "Tiempo de espera excedido", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getActivity(), "Error del servidor", Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(getActivity(), "No Existe el Dato", Toast.LENGTH_SHORT).show();
                    }
                carga.setVisibility(View.INVISIBLE);

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    ///Cerrar Teclado movil
    private void cerrarTeclado() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //cerrar sesion despues de un tiempo
    @Override
    public void onResume() {
        super.onResume();
        someOtherMethod();
    }
    private static final long MAX_SESSION_TIME = 600; // tiempo máximo de validez en segundos
    public void someOtherMethod() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        long sessionStartTime = sharedPreferences.getLong("session_start_time", 0);
        long currentTime = System.currentTimeMillis();
        long sessionTime = (currentTime - sessionStartTime) / 1000; // tiempo transcurrido en segundos
        boolean isSessionValid = sessionTime < MAX_SESSION_TIME; // MAX_SESSION_TIME es el tiempo máximo de validez en segundos
        // hacer algo con el resultado de isSessionValid
        if (!isSessionValid) {
            startActivity(new Intent(getActivity(), MainActivity7.class));
        }
    }
}
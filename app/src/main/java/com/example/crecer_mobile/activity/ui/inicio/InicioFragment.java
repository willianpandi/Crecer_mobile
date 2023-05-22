package com.example.crecer_mobile.activity.ui.inicio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.crecer_mobile.activity.InicioActivity;
import com.example.crecer_mobile.activity.MainActivity2;
import com.example.crecer_mobile.entity.Credito;
import com.example.crecer_mobile.entity.Cuenta_inicio;
import com.example.crecer_mobile.entity.adapter.AdapterCredito;
import com.example.crecer_mobile.entity.adapter.AdapterCuenta_Inicio;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//import com.example.crecer_mobile.databinding.FragmentHomeBinding;

public class InicioFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //Aviso de Cerrar la aplicacion
                MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(getActivity());
                alerta.setTitle("Salir de la aplicación")
                        .setIcon(R.drawable.ic_error)
                        .setMessage("¿Deseas SALIR o CERRAR SESIÓN de la aplicación Crecer Móvil?")
                        .setCancelable(false)
                        .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "¡GRACIAS!\n Por utilizar Crecer Móvil".toString(), Toast.LENGTH_SHORT).show();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Cerrar Sesión", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getActivity(), MainActivity2.class));
                                Toast.makeText(getActivity(), "Se ha cerrado la sesión correctamente.".toString(), Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alerta.show();
            }
        });
    }
    //Resto de las actividades del Inicio Fragment
    View vista;
    RecyclerView recyclerView5;
    List<Cuenta_inicio> lista;
    LinearProgressIndicator carga;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_inicio, container, false);

        //someOtherMethod();
        recyclerView5 = (RecyclerView) vista.findViewById(R.id.recyclerview_cuenta_inicio);
        recyclerView5.setLayoutManager(new LinearLayoutManager(getActivity()));
        carga = (LinearProgressIndicator) vista.findViewById(R.id.carga_lineal);

        SharedPreferences shared = getActivity().getSharedPreferences("preferences2", Context.MODE_PRIVATE);
        String dato = shared.getString("string1", "");

        lista = new ArrayList<Cuenta_inicio>();
        buscarUsuario("https://computacionmovil2.000webhostapp.com/buscar_cuenta.php?id=" + dato + "");
        carga.setVisibility(View.VISIBLE);

        //setupBackPressed();
        return vista;
    }

    private void buscarUsuario(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Cuenta_inicio cuenta_inicio;
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        lista.add(new Cuenta_inicio(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("n_cuenta"),
                                jsonObject.getString("nombre"),
                                jsonObject.getString("email")
                        ));

                    } catch (JSONException error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                AdapterCuenta_Inicio adapterCuenta_inicio = new AdapterCuenta_Inicio((ArrayList<Cuenta_inicio>) lista);
                recyclerView5.setAdapter(adapterCuenta_inicio);
                carga.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(getActivity(), "En este momento no tienes conexión a internet", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getActivity(), "Tiempo de espera excedido", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getActivity(), "Error del servidor", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                carga.setVisibility(View.INVISIBLE);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
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
            MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(getActivity());
            alerta.setTitle("Tu sesión ha expirado")
                    .setIcon(R.drawable.ic_error)
                    .setMessage("Por seguridad hemos cerrado tu sesión, debido a que excediste tu límite de tiempo.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getActivity(), MainActivity2.class));
                            Toast.makeText(getActivity(), "Se ha cerrado la sesión correctamente.".toString(), Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    });
            alerta.show();
        }
    }
}
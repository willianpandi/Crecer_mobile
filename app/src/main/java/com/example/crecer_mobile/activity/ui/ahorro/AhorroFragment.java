package com.example.crecer_mobile.activity.ui.ahorro;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.InicioActivity;
import com.example.crecer_mobile.activity.MainActivity3;
import com.example.crecer_mobile.entity.Ahorro;
import com.example.crecer_mobile.entity.Cuenta;
import com.example.crecer_mobile.entity.Detalle;
import com.example.crecer_mobile.entity.adapter.AdapterAhorros;
import com.example.crecer_mobile.entity.adapter.AdapterCuentas;
import com.example.crecer_mobile.entity.adapter.AdapterDetalles;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import com.example.crecer_mobile.databinding.FragmentSlideshowBinding;


public class AhorroFragment extends Fragment {

    View vista;

    List<Ahorro> lista;
    Button btnbuscarahorro, btncalendario;
    RecyclerView recyclerView3;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4;
    RadioGroup rgbtn;
    String op;
    LinearProgressIndicator carga;
    TextView txtcalendario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ahorro, container, false);

        rbtn1 = (RadioButton) vista.findViewById(R.id.radioButton);
        rbtn2 = (RadioButton) vista.findViewById(R.id.radioButton2);
        rbtn3 = (RadioButton) vista.findViewById(R.id.radioButton3);
        rbtn4 = (RadioButton) vista.findViewById(R.id.radioButton4);
        rgbtn = (RadioGroup) vista.findViewById(R.id.radioGroup);
        carga = (LinearProgressIndicator) vista.findViewById(R.id.carga_lineal2);

        btnbuscarahorro = (Button) vista.findViewById(R.id.button3);
        btncalendario = (Button) vista.findViewById(R.id.button6);
        txtcalendario = (TextView) vista.findViewById(R.id.textViewCalendario);
        recyclerView3 = (RecyclerView) vista.findViewById(R.id.recyclerview_ahorro);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Mostrar detalle
        //mostrarahorros("https://computacionmovil2.000webhostapp.com/mostrar_ahorros.php");
        //Click de cada uno de las opciones de los botones
        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = "uno";
                txtcalendario.setText("");
            }
        });
        rbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = "dos";
                txtcalendario.setText("");
            }
        });
        rbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = "tres";
                txtcalendario.setText("");
            }
        });
        rbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = "cuatro";
                txtcalendario.setText("");
            }
        });

        //Boton buscar ahorro
        btnbuscarahorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == "uno" || op == "dos" || op == "tres" || op == "cuatro") {
                    switch (op) {
                        case "uno": {
                            carga.setVisibility(View.VISIBLE);
                            Toast.makeText(getActivity(), "DATOS: Ahorro a la vista", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 1 + "&&fecha="+txtcalendario.getText().toString()+"");
                            break;
                        }
                        case "dos": {
                            Toast.makeText(getActivity(), "DATOS: Ahorro mi cofrecito", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 2 + "&&fecha="+txtcalendario.getText().toString()+"");
                            carga.setVisibility(View.VISIBLE);
                            break;
                        }
                        case "tres": {
                            Toast.makeText(getActivity(), "DATOS: Ahorro gana más", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 3 + "&&fecha="+txtcalendario.getText().toString()+"");
                            carga.setVisibility(View.VISIBLE);
                            break;
                        }
                        case "cuatro": {
                            Toast.makeText(getActivity(), "DATOS: Ahorro programado", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 4 + "&&fecha="+txtcalendario.getText().toString()+"");
                            carga.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Seleccione una opción", Toast.LENGTH_SHORT).show();
                }
                cerrarTeclado();
            }
        });

        //Boton calendario
        btncalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                        month=month+1;
                        String fecha = year+"-"+month+"-"+dayofMonth;
                        txtcalendario.setText(fecha);
                    }
                },anio,mes,dia);
                dpd.show();
            }
        });

        return vista;
    }

    private void buscarahorro(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Ahorro ahorro;
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        lista.add(new Ahorro(
                                jsonObject.getInt("cuenta"),
                                //jsonObject.getInt("codigo"),
                                jsonObject.getString("detalle"),
                                jsonObject.getString("fechas"),
                                (float) jsonObject.getDouble("deposito")
                        ));

                    } catch (JSONException error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                AdapterAhorros adapterAhorros = new AdapterAhorros((ArrayList<Ahorro>) lista);
                recyclerView3.setAdapter(adapterAhorros);
                carga.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                carga.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Error de fecha", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    //Cerrar Teclado movil
    private void cerrarTeclado() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
/*
    private void mostrarahorros(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Ahorro ahorro;
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i =0; i< jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        lista.add(new Ahorro(
                                jsonObject.getInt("cuenta"),
                                jsonObject.getString("detalle"),
                                (float)jsonObject.optDouble("depositos"),
                                jsonObject.getString("fecha")
                        ));
                    }
                    AdapterAhorros adapterAhorros = new AdapterAhorros((ArrayList<Ahorro>) lista);
                    recyclerView2.setAdapter(adapterAhorros);
                }catch (JSONException e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
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
*/
}
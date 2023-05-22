package com.example.crecer_mobile.activity.ui.ahorro;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.InicioActivity;
import com.example.crecer_mobile.activity.MainActivity2;
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

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
//import com.example.crecer_mobile.databinding.FragmentSlideshowBinding;


public class AhorroFragment extends Fragment {

    View vista;

    List<Ahorro> lista;
    Button btnbuscarahorro, btncalendario, btnPDFs;
    RecyclerView recyclerView3;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4;
    RadioGroup rgbtn;
    String op;
    LinearProgressIndicator carga;
    TextView txtcalendario;

    String dat, dato1, dato2;
    Bitmap bitmap, bitmap2, bitmapEscala, bitmapEscala2;
    int pageWith = 1400;

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
        btnPDFs = (Button) vista.findViewById(R.id.button8);
        txtcalendario = (TextView) vista.findViewById(R.id.textViewCalendario);
        recyclerView3 = (RecyclerView) vista.findViewById(R.id.recyclerview_ahorro);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_crecer);
        bitmapEscala = Bitmap.createScaledBitmap(bitmap, 350, 115, false);

        SharedPreferences shared = getActivity().getSharedPreferences("preferences2", Context.MODE_PRIVATE);
        dato1 = shared.getString("string1", "");
        dato2 = shared.getString("string2", "");

        SharedPreferences shared2 = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        dat = shared2.getString("string", "");

        //PDF
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();

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
                            //Toast.makeText(getActivity(), "DATOS: Ahorro a la vista", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 1 + "&&fecha="+txtcalendario.getText().toString()+"");
                            break;
                        }
                        case "dos": {
                            //Toast.makeText(getActivity(), "DATOS: Ahorro mi cofrecito", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 2 + "&&fecha="+txtcalendario.getText().toString()+"");
                            carga.setVisibility(View.VISIBLE);
                            break;
                        }
                        case "tres": {
                            //Toast.makeText(getActivity(), "DATOS: Ahorro gana más", Toast.LENGTH_SHORT).show();
                            lista = new ArrayList<Ahorro>();
                            buscarahorro("https://computacionmovil2.000webhostapp.com/buscar_ahorro.php?id=" + 3 + "&&fecha="+txtcalendario.getText().toString()+"");
                            carga.setVisibility(View.VISIBLE);
                            break;
                        }
                        case "cuatro": {
                            //Toast.makeText(getActivity(), "DATOS: Ahorro programado", Toast.LENGTH_SHORT).show();
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
                txtcalendario.setText("");
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
                dpd.getDatePicker().setMaxDate(cal.getTimeInMillis());
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
                                jsonObject.getString("detalle"),
                                //jsonObject.getString("fechas"),
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
                if (error instanceof NetworkError) {
                    Toast.makeText(getActivity(), "En este momento no tienes conexión a internet", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getActivity(), "Tiempo de espera excedido", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getActivity(), "Error del servidor", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "Error de busqueda por fecha", Toast.LENGTH_LONG).show();
                carga.setVisibility(View.INVISIBLE);
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

    //cerrar sesion despues de 10 minutos
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

    //Generara archivo PDF
    public void createPDF(){
        btnPDFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdfDocument pdfDocument = new PdfDocument();
                Paint paint = new Paint();

                //Ancho y largo de la hoja
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1400, 2400, 1).create();
                PdfDocument.Page pagina1 = pdfDocument.startPage(pageInfo);
                Canvas canvas = pagina1.getCanvas();

                //Logo en PDF
                canvas.drawBitmap(bitmapEscala, 50, 50, paint);

                //datos generales
                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                paint.setColor(Color.rgb(149, 31, 31));
                paint.setTextSize(60);
                canvas.drawText("Crecer Móvil", pageWith - 50, 90, paint);
                paint.setTextSize(40);
                paint.setColor(Color.BLACK);
                canvas.drawText("Detalle de Ahorro", pageWith -50, 140, paint);

                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                paint.setTextSize(40);
                paint.setColor(Color.rgb(122, 119, 119));
                canvas.drawText("Datos Generales", pageInfo.getPageWidth() / 2, 250, paint);

                paint.setTextAlign(Paint.Align.LEFT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                paint.setTextSize(35f);
                paint.setColor(Color.BLACK);
                canvas.drawText("Cuenta:", 100, 300, paint);
                canvas.drawText("Cédula:", 100, 350, paint);
                canvas.drawText("Cliente:", 100, 400, paint);

                paint.setTextAlign(Paint.Align.LEFT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                paint.setTextSize(30f);
                paint.setColor(Color.BLACK);
                canvas.drawText("" + dat, 230, 300, paint);
                canvas.drawText("" + dato1, 230, 350, paint);
                canvas.drawText("" + dato2, 230, 400, paint);

                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                paint.setColor(Color.BLACK);
                paint.setTextSize(35f);
                canvas.drawText("Fecha:", pageWith - 270, 300, paint);
                canvas.drawText("Hora:", pageWith - 290, 350, paint);

                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                paint.setTextSize(30f);
                paint.setColor(Color.BLACK);
                SimpleDateFormat sdfecha = new SimpleDateFormat("dd/MM/" + 20 + "yy", Locale.getDefault());
                canvas.drawText("" + sdfecha.format(Calendar.getInstance().getTime()), pageWith - 100, 300, paint);
                SimpleDateFormat sdfhora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                canvas.drawText("" + sdfhora.format(Calendar.getInstance().getTime()), pageWith - 140, 350, paint);

                //Indice de Tabla
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                canvas.drawRect(165, 440, pageWith - 210, 500, paint);

                paint.setTextAlign(Paint.Align.LEFT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                paint.setTextSize(35f);
                canvas.drawText("Cuenta", 167, 480, paint);
                canvas.drawText("Detalle", 417, 480, paint);
                canvas.drawText("Saldo Total", 932, 480, paint);

                canvas.drawLine(415, 440, 415, 520, paint);
                canvas.drawLine(930, 440, 930, 520, paint);

                //Capturar del recycle view para el archivo PDF
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                Canvas canvas3 = new Canvas(bitmap2);
                recyclerView3.draw(canvas3);
                canvas.drawBitmap(bitmap2,137,501,null);

                pdfDocument.finishPage(pagina1);

                // Genera el archivo PDF
                SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss _ dd-MM-yyyy", Locale.getDefault());
                String pdfName = "Detalle_Ahorro_"+ sdf.format(Calendar.getInstance().getTime()) + ".pdf";

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(), pdfName);
                try {
                    pdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(getActivity(), "Se completó la descarga: "+pdfName, Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
                pdfDocument.close();
            }
        });
    }
}
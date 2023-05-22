package com.example.crecer_mobile.activity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.ui.consultas.ConsultasFragment;
import com.example.crecer_mobile.entity.Detalle;
import com.example.crecer_mobile.entity.adapter.AdapterDetalles;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    TextView txtcuenta, txtnombre, txtcedula, txtsaldo;
    RecyclerView recyclerView2;
    List<Detalle> lista;
    Button btnPDF;
    Bitmap bitmap, bitmap2, bitmapEscala, bitmapEscala2;
    int pageWith = 1400;

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
        String cuenta = "", nombre = " ", cedula = "", saldo = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cuenta = extras.getString("cuenta");
            nombre = extras.getString("nombre");
            cedula = extras.getString("cedula");
            saldo = extras.getString("saldo");
        }

        txtcuenta = (TextView) findViewById(R.id.textView14);
        txtnombre = (TextView) findViewById(R.id.textView12);
        txtcedula = (TextView) findViewById(R.id.textView13);
        txtsaldo = (TextView) findViewById(R.id.textView15);
        btnPDF = (Button) findViewById(R.id.button7);

        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview_detalle);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        lista = new ArrayList<Detalle>();

        txtcuenta.setText(cuenta);
        txtnombre.setText(nombre);
        txtcedula.setText(cedula);
        txtsaldo.setText(saldo);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_crecer);
        bitmapEscala = Bitmap.createScaledBitmap(bitmap, 350, 115, false);

        //PDF
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();

        //Mostrar detalle
        mostrarDetalleCuentas("https://computacionmovil2.000webhostapp.com/mostrar_cuentas.php");

    }

    ///////////////////// MUESTRA TODOS LOS DETALLES /////////////////////////////////////
    private void mostrarDetalleCuentas(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Detalle detalle;
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        lista.add(new Detalle(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("n_cuenta"),
                                (float) jsonObject.optDouble("saldo")
                        ));
                    }
                    AdapterDetalles adapterDetalles = new AdapterDetalles((ArrayList<Detalle>) lista);
                    recyclerView2.setAdapter(adapterDetalles);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity3.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "En este momento no tienes conexión a internet", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "Tiempo de espera excedido", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Error del servidor", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(MainActivity3.this, error.toString(), Toast.LENGTH_SHORT).show();
            }

        });
        Volley.newRequestQueue(MainActivity3.this).add(stringRequest);
    }

    //cerrar sesion despues de un tiempo
    @Override
    public void onResume() {
        super.onResume();
        someOtherMethod();
    }
    private static final long MAX_SESSION_TIME = 600; // tiempo máximo de validez en segundos
    public void someOtherMethod() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        long sessionStartTime = sharedPreferences.getLong("session_start_time", 0);
        long currentTime = System.currentTimeMillis();
        long sessionTime = (currentTime - sessionStartTime) / 1000; // tiempo transcurrido en segundos
        boolean isSessionValid = sessionTime < MAX_SESSION_TIME; // MAX_SESSION_TIME es el tiempo máximo de validez en segundos
        // hacer algo con el resultado de isSessionValid
        if (!isSessionValid) {
            MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(this);
            alerta.setTitle("Tu sesión ha expirado!")
                    .setIcon(R.drawable.ic_error)
                    .setMessage("Por seguridad hemos cerrado tu sesión, debido a que excediste tu límite de tiempo.")
                    .setCancelable(false)
                    .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity3.this, "¡GRACIAS! por utilizar Crecer Móvil".toString(), Toast.LENGTH_SHORT).show();
                            System.exit(0);
                        }
                    });
            alerta.show();
        }
    }

    //Metodo de PDF
    public void createPDF(){
        btnPDF.setOnClickListener(new View.OnClickListener() {
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
                canvas.drawText("Detalle de Cuenta", pageWith -50, 140, paint);

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
                canvas.drawText("" + txtcuenta.getText(), 230, 300, paint);
                canvas.drawText("" + txtcedula.getText(), 230, 350, paint);
                canvas.drawText("" + txtnombre.getText(), 230, 400, paint);

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

                //cuadro
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                canvas.drawRect(20, 440, pageWith - 20, 500, paint);

                paint.setTextAlign(Paint.Align.LEFT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                paint.setTextSize(35f);
                canvas.drawText("Cuenta", 40, 480, paint);
                canvas.drawText("Nombre y Apellido", 250, 480, paint);
                canvas.drawText("Cédula", 700, 480, paint);
                canvas.drawText("Saldo", 950, 480, paint);

                canvas.drawLine(230, 440, 230, 500, paint);
                canvas.drawLine(680, 440, 680, 500, paint);
                canvas.drawLine(930, 440, 930, 500, paint);

                int width = canvas.getWidth();
                int height = canvas.getHeight();
                bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                Canvas canvas3 = new Canvas(bitmap2);
                recyclerView2.draw(canvas3);
                canvas.drawBitmap(bitmap2,100,501,null);

                pdfDocument.finishPage(pagina1);

//////////////////      PAGINA 2     //////////////////////////////////
             /*   PdfDocument.PageInfo pageInfo2 = new PdfDocument.PageInfo.Builder(1200,2010,1).create();
                PdfDocument.Page pagina2 = pdfDocument.startPage(pageInfo2);
                Canvas canvas2 = pagina2.getCanvas();

                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                paint.setTextSize(30);
                canvas2.drawText("Dirección: Av. 12 de Noviembre y Juan Montalvo(esquina)", pageWith - 50, 1970, paint);


                pdfDocument.finishPage(pagina2);*/


                //// GENERA EL ARCHIVO /////////////////////
                SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss - dd-MM-yyyy", Locale.getDefault());
                String pdfName = "DetalleCuenta_" + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(), pdfName);
                try {
                    pdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(MainActivity3.this, "Se completó la descarga: "+pdfName, Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
                pdfDocument.close();
            }
        });
    }
}
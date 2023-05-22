package com.example.crecer_mobile.activity.ui.configuracion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.MainActivity2;
import com.example.crecer_mobile.activity.MainActivity4;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ConfiguracionFragment extends Fragment {
    TextView txtnmbre;
    String dato1, dato2;
    View vista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_configuracion, container, false);

        //grafico y logico
        txtnmbre = (TextView) vista.findViewById(R.id.TextView_Nombre);

        //Traer el usuario los datos del login
        SharedPreferences shared = getActivity().getSharedPreferences("preferences2", Context.MODE_PRIVATE);
        dato1 = shared.getString("string1", "");
        dato2 = shared.getString("string2", "");

        txtnmbre.setText(dato2);
        return vista;
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
package com.example.crecer_mobile.activity.ui.transacciones;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.MainActivity5;
import com.example.crecer_mobile.activity.MainActivity7;


public class TransaccionFragment extends Fragment {
    View vista;
    Button btntransaccion;
    private String url1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        vista = inflater.inflate(R.layout.fragment_transaccion, container, false);
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            startActivity(new Intent(getActivity(), MainActivity5.class));
        } else {
            //logico y visual
            btntransaccion = vista.findViewById(R.id.buttontransaccion);
            //enlaces
            url1 = "https://crecer-enlinea.fit-bank.com/#/";
            //boton AQUI
            btntransaccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(url1);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
        return vista;
    }
    //cerrar sesion despues de un tiempo
    @Override
    public void onResume() {
        super.onResume();
        someOtherMethod();
    }
    private static final long MAX_SESSION_TIME = 600; // tiempo máximo de validez en segundos 10 min
    public void someOtherMethod() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        long sessionStartTime = sharedPreferences.getLong("session_start_time", 0);
        long currentTime = System.currentTimeMillis();
        long sessionTime = (currentTime - sessionStartTime) / 1000; // tiempo transcurrido en segundos
        boolean isSessionValid = sessionTime < MAX_SESSION_TIME;
        if (!isSessionValid) {
            startActivity(new Intent(getActivity(), MainActivity7.class));
        }
    }
}
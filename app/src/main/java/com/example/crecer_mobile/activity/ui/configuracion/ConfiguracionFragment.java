package com.example.crecer_mobile.activity.ui.configuracion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crecer_mobile.R;

public class ConfiguracionFragment extends Fragment {

    View vista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_configuracion, container, false);


        return vista;
    }


}
package com.example.crecer_mobile.activity.ui.consultas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crecer_mobile.R;
//import com.example.crecer_mobile.databinding.FragmentGalleryBinding;


public class ConsultasFragment extends Fragment {

    View vista;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_consultas, container, false);


        return vista;

    }

}
package com.example.crecer_mobile.activity.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crecer_mobile.R;

import java.util.ArrayList;
//import com.example.crecer_mobile.databinding.FragmentHomeBinding;

public class InicioFragment extends Fragment {
    View vista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);


        return vista;
    }

}
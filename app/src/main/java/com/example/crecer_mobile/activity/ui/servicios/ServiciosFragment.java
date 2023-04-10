package com.example.crecer_mobile.activity.ui.servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crecer_mobile.R;
//import com.example.crecer_mobile.databinding.FragmentSlideshowBinding;


public class ServiciosFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicios, container, false);
    }

}
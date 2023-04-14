package com.example.crecer_mobile.activity.ui.ahorro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crecer_mobile.R;
//import com.example.crecer_mobile.databinding.FragmentSlideshowBinding;


public class AhorroFragment extends Fragment {


    View vista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ahorro, container, false);


        return vista;
    }

}
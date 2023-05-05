package com.example.crecer_mobile.activity.ui.inicio;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crecer_mobile.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
//import com.example.crecer_mobile.databinding.FragmentHomeBinding;

public class InicioFragment extends Fragment {
    View vista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);

        //setupBackPressed();
        return vista;
    }

    /*private static final int Intervalo = 2000;
    //private  long tiempoPrimerClik;
    private void setupBackPressed(){
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isEnabled()){
                    Toast.makeText(requireActivity(), "Aplicación cerrada correctamente", Toast.LENGTH_SHORT).show();
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
            }
        });
    }*/

}
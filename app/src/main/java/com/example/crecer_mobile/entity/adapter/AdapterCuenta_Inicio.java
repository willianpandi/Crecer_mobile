package com.example.crecer_mobile.entity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.Cuenta_inicio;

import java.util.ArrayList;

public class AdapterCuenta_Inicio extends RecyclerView.Adapter<AdapterCuenta_Inicio.ViewHolderCuenta_Inicio> {
    ArrayList<Cuenta_inicio> ListaCuenta_inicio;
    public AdapterCuenta_Inicio(ArrayList<Cuenta_inicio> listaCuenta_inicio){
        this.ListaCuenta_inicio = listaCuenta_inicio;
    }

    @NonNull
    @Override
    public AdapterCuenta_Inicio.ViewHolderCuenta_Inicio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuentas_inicio, null, false);
        return new AdapterCuenta_Inicio.ViewHolderCuenta_Inicio(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCuenta_Inicio.ViewHolderCuenta_Inicio holder, int position) {
        holder.dni.setText(String.valueOf(ListaCuenta_inicio.get(position).getN_cuenta()));
        holder.cuenta.setText(String.valueOf(ListaCuenta_inicio.get(position).getId()));
        holder.nombre.setText(ListaCuenta_inicio.get(position).getNombre());
        holder.email.setText(ListaCuenta_inicio.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return ListaCuenta_inicio.size();
    }

    public class ViewHolderCuenta_Inicio extends RecyclerView.ViewHolder{
        TextView dni, cuenta, nombre, email;
        public ViewHolderCuenta_Inicio(@NonNull View itemView) {
            super(itemView);
            dni = (TextView) itemView.findViewById(R.id.h_dni);
            cuenta = (TextView) itemView.findViewById(R.id.h_cuenta);
            nombre = (TextView) itemView.findViewById(R.id.h_nombre);
            email = (TextView) itemView.findViewById(R.id.h_email);
        }
    }
}

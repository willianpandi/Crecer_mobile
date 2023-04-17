package com.example.crecer_mobile.entity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.Cuenta;
import com.example.crecer_mobile.entity.Detalle;

import java.util.ArrayList;

public class AdapterDetalles extends RecyclerView.Adapter<AdapterDetalles.ViewHolderDetalles> {

    ArrayList<Detalle> ListaDetalles;
    public AdapterDetalles(ArrayList<Detalle> listaDetalles){
        this.ListaDetalles = listaDetalles;
    }
    @NonNull
    @Override
    public AdapterDetalles.ViewHolderDetalles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalles,null,false);
        return new AdapterDetalles.ViewHolderDetalles(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDetalles.ViewHolderDetalles holder, int position) {
        holder.cuenta.setText(String.valueOf(ListaDetalles.get(position).getCuenta()));
        holder.cedula.setText(String.valueOf(ListaDetalles.get(position).getCedula()));
        holder.saldo.setText(String.valueOf(ListaDetalles.get(position).getSaldos()));


    }

    @Override
    public int getItemCount() {
        return ListaDetalles.size();
    }

    public class ViewHolderDetalles extends RecyclerView.ViewHolder {
        TextView cuenta, cedula, saldo;
        public ViewHolderDetalles(@NonNull View itemView) {
            super(itemView);
            cuenta = (TextView) itemView.findViewById(R.id.textView_cuenta);
            cedula = (TextView) itemView.findViewById(R.id.texView_cedula);
            saldo = (TextView) itemView.findViewById(R.id.textView_saldo);
        }
    }
}

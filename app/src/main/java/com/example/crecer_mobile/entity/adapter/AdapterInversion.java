package com.example.crecer_mobile.entity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.Inversion;

import java.util.ArrayList;

public class AdapterInversion extends RecyclerView.Adapter<AdapterInversion.ViewHolderInversion> {
    ArrayList<Inversion> ListaInversion;
    public AdapterInversion(ArrayList<Inversion> listaInversion){
        this.ListaInversion = listaInversion;
    }
    @NonNull
    @Override
    public AdapterInversion.ViewHolderInversion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inversion, null, false);
        return new AdapterInversion.ViewHolderInversion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInversion.ViewHolderInversion holder, int position) {
        holder.cuenta_in.setText(String.valueOf(ListaInversion.get(position).getCuenta()));
        holder.cedula_in.setText(String.valueOf(ListaInversion.get(position).getCedula()));
        holder.fecha_in.setText(ListaInversion.get(position).getFecha());
        holder.deposito_in.setText(String.valueOf(ListaInversion.get(position).getDeposito()));
    }

    @Override
    public int getItemCount() {
        return ListaInversion.size();
    }

    public class ViewHolderInversion extends RecyclerView.ViewHolder{
        TextView cuenta_in, cedula_in, fecha_in, deposito_in;
        public ViewHolderInversion(@NonNull View itemView) {
            super(itemView);
            cuenta_in = (TextView) itemView.findViewById(R.id.textView_cuenta_in);
            cedula_in = (TextView) itemView.findViewById(R.id.textView_cedula_in);
            fecha_in = (TextView) itemView.findViewById(R.id.textView_fecha_in);
            deposito_in = (TextView) itemView.findViewById(R.id.textView_deposito_in);
        }
    }
}

package com.example.crecer_mobile.entity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.Ahorro;
import com.example.crecer_mobile.entity.Detalle;

import java.util.ArrayList;

public class AdapterAhorros extends RecyclerView.Adapter<AdapterAhorros.ViewHolderAhorros> {
    ArrayList<Ahorro> ListaAhorrros;
    public AdapterAhorros(ArrayList<Ahorro> listaAhorrros){
        this.ListaAhorrros = listaAhorrros;
    }

    @NonNull
    @Override
    public AdapterAhorros.ViewHolderAhorros onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ahorro, null, false);
        return new AdapterAhorros.ViewHolderAhorros(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAhorros.ViewHolderAhorros holder, int position) {
        holder.cuenta_ahorro.setText(String.valueOf(ListaAhorrros.get(position).getCuenta()));
        holder.detalle_ahorro.setText(ListaAhorrros.get(position).getDetalle());
        holder.deposito_ahorro.setText(String.valueOf(ListaAhorrros.get(position).getDeposito()));
        holder.fecha_ahorro.setText(ListaAhorrros.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return ListaAhorrros.size();
    }

    public class ViewHolderAhorros extends RecyclerView.ViewHolder {
        TextView cuenta_ahorro, detalle_ahorro, fecha_ahorro, deposito_ahorro;
        public ViewHolderAhorros(@NonNull View itemView) {
            super(itemView);

            cuenta_ahorro = (TextView) itemView.findViewById(R.id.texView_cuenta_ahorro);
            detalle_ahorro = (TextView) itemView.findViewById(R.id.textView_detalle);
            fecha_ahorro = (TextView) itemView.findViewById(R.id.textView_fecha_ahorro);
            deposito_ahorro = (TextView) itemView.findViewById(R.id.textView_deposito_ahorro);
        }
    }
}

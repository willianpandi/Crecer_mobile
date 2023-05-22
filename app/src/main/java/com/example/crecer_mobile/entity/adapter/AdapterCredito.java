package com.example.crecer_mobile.entity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.Credito;

import java.util.ArrayList;


public class AdapterCredito extends RecyclerView.Adapter<AdapterCredito.ViewHolderCredito>{
    ArrayList<Credito> ListaCredito;

    public AdapterCredito(ArrayList<Credito> listaCredito)
    {
        this.ListaCredito = listaCredito;
    }

    @NonNull
    @Override
    public AdapterCredito.ViewHolderCredito onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credito, null,false);
        return new ViewHolderCredito(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCredito.ViewHolderCredito holder, int position) {
        holder.cuenta_credito.setText(String.valueOf(ListaCredito.get(position).getCuenta()));
        holder.cedula_credito.setText(String.valueOf(ListaCredito.get(position).getCedula()));
        holder.fecha_credito.setText(ListaCredito.get(position).getFecha());
        holder.deposito_credito.setText(String.valueOf(ListaCredito.get(position).getDeposito()));
    }

    @Override
    public int getItemCount() {
        return ListaCredito.size();
    }

    public class ViewHolderCredito extends RecyclerView.ViewHolder{
        TextView cuenta_credito, cedula_credito, fecha_credito, deposito_credito;
        public ViewHolderCredito(@NonNull View itemView) {
            super(itemView);
            cuenta_credito = (TextView) itemView.findViewById(R.id.texView_cuenta_credito);
            cedula_credito = (TextView) itemView.findViewById(R.id.textView_cedula_credito);
            fecha_credito= (TextView) itemView.findViewById(R.id.textView_fecha_credito);
            deposito_credito = (TextView) itemView.findViewById(R.id.textView_deposito_credito);
        }
    }
}

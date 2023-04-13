package com.example.crecer_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.entity.Cuenta;

import java.util.ArrayList;

public class AdapterCuentas extends RecyclerView.Adapter<AdapterCuentas.ViewHolderCuentas> {

    ArrayList<Cuenta> ListaCuentas;
    public AdapterCuentas(ArrayList<Cuenta> listaCuentas)
    {
        this.ListaCuentas = listaCuentas;
    }

    @NonNull
    @Override
    public AdapterCuentas.ViewHolderCuentas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuentas,null,false);
        return new ViewHolderCuentas(view);
    }

    //Aqui se debe de asignar la interfaz grafica y el buscador
    @Override
    public void onBindViewHolder(@NonNull AdapterCuentas.ViewHolderCuentas holder, int position) {
        holder.cuenta.setText(String.valueOf(ListaCuentas.get(position).getId()));
        holder.nombre.setText(ListaCuentas.get(position).getNombre());
        holder.saldo.setText(String.valueOf(ListaCuentas.get(position).getSaldo()));

    }

    @Override
    public int getItemCount() {
        return ListaCuentas.size();
    }

    public class ViewHolderCuentas extends RecyclerView.ViewHolder {

        //variables locales
        TextView cuenta, nombre, saldo;
        Button btndetalle;
        public ViewHolderCuentas(@NonNull View itemView) {
            super(itemView);
            cuenta = (TextView) itemView.findViewById(R.id.n_cuenta);
            nombre = (TextView) itemView.findViewById(R.id.n_nombre);
            saldo = (TextView) itemView.findViewById(R.id.n_saldo);

            btndetalle = (Button) itemView.findViewById(R.id.btnDetalle);

            btndetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(cuenta.getContext(), "Saldo: "+saldo.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}

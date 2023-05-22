package com.example.crecer_mobile.entity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.MainActivity3;
import com.example.crecer_mobile.activity.ui.configuracion.ConfiguracionFragment;
import com.example.crecer_mobile.activity.ui.consultas.ConsultasFragment;
import com.example.crecer_mobile.entity.Cuenta;

import java.util.ArrayList;

public class AdapterCuentas extends RecyclerView.Adapter<AdapterCuentas.ViewHolderCuentas> {

    ArrayList<Cuenta> ListaCuentas;

    public AdapterCuentas(ArrayList<Cuenta> listaCuentas) {
        this.ListaCuentas = listaCuentas;
    }

    @NonNull
    @Override
    public AdapterCuentas.ViewHolderCuentas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuentas, null, false);
        return new ViewHolderCuentas(view);
    }

    //Aqui se debe de asignar la interfaz grafica y el buscador
    @Override
    public void onBindViewHolder(@NonNull AdapterCuentas.ViewHolderCuentas holder, int position) {
        holder.dni.setText(String.valueOf(ListaCuentas.get(position).getN_cuenta()));
        holder.cuenta.setText(String.valueOf(ListaCuentas.get(position).getId()));
        holder.nombre.setText(ListaCuentas.get(position).getNombre());
        holder.saldo.setText(String.valueOf(ListaCuentas.get(position).getCorreo()));

        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return ListaCuentas.size();
    }

    public class ViewHolderCuentas extends RecyclerView.ViewHolder implements View.OnClickListener {

        //variables locales
        TextView dni, cuenta, nombre, saldo;
        Button btndetalle;
        Context context;

        public ViewHolderCuentas(@NonNull View itemView) {
            super(itemView);
            //contexto
            context = itemView.getContext();
            //grafico y logico
            dni = (TextView) itemView.findViewById(R.id.n_dni);
            cuenta = (TextView) itemView.findViewById(R.id.n_cuenta);
            nombre = (TextView) itemView.findViewById(R.id.n_nombre);
            saldo = (TextView) itemView.findViewById(R.id.n_saldo);
            btndetalle = (Button) itemView.findViewById(R.id.btnDetalle);
        }

        void setOnClickListeners() {
            btndetalle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDetalle:
                    Intent intent = new Intent(context, MainActivity3.class);
                    intent.putExtra("cuenta", cuenta.getText());
                    intent.putExtra("nombre", nombre.getText());
                    intent.putExtra("cedula", dni.getText());
                    intent.putExtra("saldo", saldo.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }
}

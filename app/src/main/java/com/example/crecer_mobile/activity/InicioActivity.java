package com.example.crecer_mobile.activity;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.crecer_mobile.R;
import com.example.crecer_mobile.activity.ui.inicio.InicioFragment;
import com.example.crecer_mobile.databinding.ActivityInicioBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class InicioActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityInicioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarInicio.toolbar);
        binding.appBarInicio.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Soy el botón flotante", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_consultas, R.id.nav_configuracion, R.id.nav_ahorro, R.id.nav_creditos, R.id.nav_inversiones)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cerrarSesion:
                this.logout();
                break;
            case R.id.cerrarApp:
                this.exit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exit() {
        MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(InicioActivity.this);
        alerta.setTitle("Salir de la aplicación")
                .setIcon(R.drawable.ic_error)
                .setMessage("¿Estas seguro que deseas salir de la aplicación de Crecer Móvil?")
                .setCancelable(false)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        alerta.show();

    }

    private void logout() {
        MaterialAlertDialogBuilder alerta = new MaterialAlertDialogBuilder(InicioActivity.this);
        alerta.setTitle("Cerrando sesión")
                .setIcon(R.drawable.ic_error)
                .setMessage("¿Estas seguro que deseas cerrar la sesión?")
                .setCancelable(false)
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(InicioActivity.this, MainActivity2.class));
                        Toast.makeText(getApplicationContext(), "Se ha cerrado la sesión correctamente", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        alerta.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//cerrar la aplicacion desde el inicio de la aplicacion dando doble click
    /*
    private static final int Intervalo = 2000;
    private  long tiempoPrimerClik;

    @Override
    public void onBackPressed() {
        if (tiempoPrimerClik + Intervalo > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Vuelva a presionar para salir de la aplicación", Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClik = System.currentTimeMillis();
    }*/
    /*
private static long presionado;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount()>0)
            super.onBackPressed();
        else {
            if (presionado + 1000 > System.currentTimeMillis())
                super.onBackPressed();
            else
                Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
            presionado = System.currentTimeMillis();
        }
    }*/
}
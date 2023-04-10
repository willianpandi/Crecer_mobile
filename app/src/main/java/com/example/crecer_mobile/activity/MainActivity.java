package com.example.crecer_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crecer_mobile.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //funcion para quitar Action Bar
        getSupportActionBar().hide();


        //Evento para el Timer
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                //Intent splash = new Intent(MainActivity.this, MainActivity2.class );
                //startActivity(splash);
                finish();
            }
        }, 1000);
    }
}
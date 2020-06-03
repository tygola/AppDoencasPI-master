package com.example.appdoencaspi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirJanela();
            }
        },5000);
    }

    public void abrirJanela() {
        Intent intent = new Intent(Splash_Activity.this,Login_Activity.class);
        startActivity(intent);
    }
}

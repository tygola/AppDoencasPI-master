package com.example.appdoencaspi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(Login_Activity.this,Cadastro_Activity.class);
        startActivity(intent);
    }

    public void abrirAplicacao(View view) {
        Intent intent = new Intent(Login_Activity.this,MainActivity.class);
        startActivity(intent);
    }

    public void fechar(View view) {
        finish();
    }
}

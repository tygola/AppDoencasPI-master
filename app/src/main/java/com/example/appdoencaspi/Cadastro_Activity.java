package com.example.appdoencaspi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.view.View.GONE;

public class Cadastro_Activity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText txtLogin,txtEmail,txtSenha,txtConfirma;
    Button btnCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);

        txtLogin = findViewById(R.id.editTextCLogin);
        txtEmail = findViewById(R.id.editTextCEmail);
        txtSenha = findViewById(R.id.editTextCSenha);
        txtConfirma = findViewById(R.id.editTextCConfirma);

        btnCadastro = findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login,email,senha,confirma;

                login = txtLogin.getText().toString();
                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();
                confirma = txtConfirma.getText().toString();

                if (login.equals("")||email.equals("")||senha.equals("")||confirma.equals("")){
                    Toast.makeText(getApplicationContext(),"Favor inserir os valores!!!",Toast.LENGTH_LONG).show();
                }else{
                    if (senha.equals(confirma)){
//                        Boolean checarLogin = validarLogin(login);
//                        Boolean chegarEmail = validarEmail(email);

                        cadastrar(login,email,senha);

//                        if (checarLogin == true && chegarEmail == true){
//
//                        }else{
//                            Toast.makeText(getApplicationContext(),"Login ou Email já existem!!!",Toast.LENGTH_LONG).show();
//                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Senhas não conferem!!!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void cadastrar(String login, String email, String senha) {
        HashMap<String, String> params = new HashMap<>();
        params.put("nomeLogin", login);
        params.put("email", email);
        params.put("senha", senha);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_LOGIN, params, CODE_GET_REQUEST);
        request.execute();

    }

//    private Boolean validarEmail(String email) {
//        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_LOGIN,null,CODE_GET_REQUEST);
//        request.execute();
//
//    }

//    private Boolean validarLogin(String login) {
//
//
//    }



    public void voltarLogin(View view) {
        Intent intent = new Intent(Cadastro_Activity.this,Login_Activity.class);
        startActivity(intent);
    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);

            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);


            return null;
        }
    }

}

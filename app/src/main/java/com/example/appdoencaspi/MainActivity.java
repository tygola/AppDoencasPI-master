package com.example.appdoencaspi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText editTextDoencaID, editTextNome, editTextSintomas, editTextPrevencao;
    ProgressBar progressBar;
    ListView listView;
    Button buttonAddUpdate;
    TextView textViewNome;

    List<Doenca> doencaList;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDoencaID = findViewById(R.id.editTextDoencaID);
        editTextNome = findViewById(R.id.editTextNome);
        editTextSintomas = findViewById(R.id.editTextSintomas);
        editTextPrevencao = findViewById(R.id.editTextPrevencao);
        textViewNome = findViewById(R.id.doencaNome);

        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listViewDoencas);
        buttonAddUpdate = findViewById(R.id.btnAddUpdate);

        doencaList = new ArrayList<>();

        buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating) {
                    updateDoenca();
                } else {
                    createDoenca();
                }
            }


        });




        readDoenca();

    }

    private void refreshDoencaList(JSONArray doencas) throws JSONException {
        doencaList.clear();

        for (int i = 0; i < doencas.length(); i++) {
            JSONObject obj = doencas.getJSONObject(i);
            doencaList.add(new Doenca(
                    obj.getInt("id"),
                    obj.getString("nome"),
                    obj.getString("sintomas"),
                    obj.getString("prevencao")
            ));
        }

        DoencaAdapter adapter = new DoencaAdapter(doencaList);
        listView.setAdapter(adapter);


    }

    private void readDoenca() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_DOENCAS, null, CODE_GET_REQUEST);
        request.execute();

    }

    private void createDoenca() {

        String nome = editTextNome.getText().toString();
        String sintomas = editTextSintomas.getText().toString();
        String prevencao = editTextPrevencao.getText().toString();

        if (TextUtils.isEmpty(nome)) {
            editTextNome.setError("Por Favor entre com o nome");
            editTextNome.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(sintomas)) {
            editTextNome.setError("Por Favor entre com os sintomas");
            editTextNome.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(prevencao)) {
            editTextNome.setError("Por Favor entre com as prevenções");
            editTextNome.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("nome", nome);
        params.put("sintomas", sintomas);
        params.put("prevencao", prevencao);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_DOENCAS, params, CODE_GET_REQUEST);
        request.execute();

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
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshDoencaList(object.getJSONArray("doencas"));
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

    private void updateDoenca() {

        String nome = editTextNome.getText().toString().trim();
        String sintomas = editTextSintomas.getText().toString().trim();
        String prevencao = editTextPrevencao.getText().toString().trim();

        if (TextUtils.isEmpty(nome)) {
            editTextNome.setError("Por Favor entre com um nome");
            editTextNome.requestFocus();
        }
        if (TextUtils.isEmpty(sintomas)) {
            editTextNome.setError("Por Favor entre com um Sintoma");
            editTextNome.requestFocus();
        }
        if (TextUtils.isEmpty(prevencao)) {
            editTextNome.setError("Por Favor entre com uma Prevenção");
            editTextNome.requestFocus();
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("nome", nome);
        params.put("sintomas", sintomas);
        params.put("prevencao", prevencao);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_UPDATE_DOENCAS, params, CODE_GET_REQUEST);
        request.execute();

        buttonAddUpdate.setText("Alterado");

        editTextNome.setText("");
        editTextSintomas.setText("");
        editTextPrevencao.setText("");

        isUpdating = false;



    }

    class DoencaAdapter extends ArrayAdapter<Doenca> {
        List<Doenca> doencaList;

        public DoencaAdapter(List<Doenca> doencaList) {
            super(MainActivity.this, R.layout.layout_lista_doencas, doencaList);
            this.doencaList = doencaList;
        }

//        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_lista_doencas, null, true);

            TextView textViewNome = listViewItem.findViewById(R.id.textViewNome);
            TextView textViewUpdate = listViewItem.findViewById(R.id.textViewUpdate);
            TextView textViewDelete = listViewItem.findViewById(R.id.textViewDelete);

            final Doenca doenca = doencaList.get(position);

            textViewNome.setText(doenca.getNome());

            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isUpdating = true;
                    editTextDoencaID.setText(String.valueOf(doenca.getId()));
                    editTextNome.setText(doenca.getNome());
                    editTextSintomas.setText(doenca.getSintomas());
                    editTextPrevencao.setText(doenca.getPrevencao());
                }
            });

            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Apagar " + doenca.getNome())
                            .setMessage("Tem Certeza que deseja excluir?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteDoenca(doenca.getId());
                                }
                            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                            .setIcon(android.R.drawable.ic_dialog_alert).show();

                }
            });

            return listViewItem;
        }
    }

    private void deleteDoenca(int id) {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_DELETE_DOENCAS + id, null, CODE_GET_REQUEST);
        request.execute();
    }


}

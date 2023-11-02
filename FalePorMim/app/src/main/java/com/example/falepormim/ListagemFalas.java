package com.example.falepormim;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.falepormim.FalasDAO;

import java.util.ArrayList;
import java.util.List;

public class ListagemFalas extends Activity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private FalasDAO falasDAO;
    private Button btnBack; // Botão "Voltar"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_falas);

        listView = findViewById(R.id.listView);
        falasDAO = new FalasDAO(this); // Inicialize a classe FalasDAO

        // Obtenha a lista de falas do banco de dados
        List<String> falas = new ArrayList<>();
        Cursor cursor = falasDAO.obterTodasAsFalas();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String texto = cursor.getString(cursor.getColumnIndex("text"));
                falas.add(texto);
            }
            cursor.close();
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_item_falas, falas);

        listView.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finalize a atividade e volta para a atividade anterior
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        falasDAO.close(); // Feche o banco de dados ao encerrar a atividade
        super.onDestroy();
    }
}

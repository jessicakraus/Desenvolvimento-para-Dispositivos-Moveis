package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainResultado extends AppCompatActivity {

    TextView tvNome;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNome = findViewById(R.id.textViewNome);
        tvResultado = findViewById(R.id.textViewResultado);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nome = extras.getString("nome");
            String imc = extras.getString("imc");
            String categoria = extras.getString("categoria");

            tvNome.setText("Olá, " + nome +"!");
            tvResultado.setText("Seu IMC é " + imc + ". Categoria: " + categoria);
        }
    }
}
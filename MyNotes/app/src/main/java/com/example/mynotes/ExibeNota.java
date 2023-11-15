package com.example.mynotes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.controller.NotasController;
import com.example.mynotes.modelo.Nota;

public class ExibeNota  extends AppCompatActivity {

    TextView textViewTitulo;
    TextView textViewDescricao;
    NotasController notasController;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibenota);

        int idNota = getIntent().getExtras().getInt("id");

        this.textViewTitulo = findViewById(R.id.textViewTitulo);
        this.textViewDescricao = findViewById(R.id.textViewDescricao);

        this.notasController = new NotasController(getBaseContext());
        this.nota = notasController.getNotas().get(idNota);

        this.textViewTitulo.setText(this.nota.getTitulo());
        this.textViewDescricao.setText(this.nota.getTexto());
    }
}
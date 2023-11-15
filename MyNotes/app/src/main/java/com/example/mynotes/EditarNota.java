package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.controller.NotasController;
import com.example.mynotes.modelo.Nota;

public class EditarNota extends AppCompatActivity {

    EditText tituloEdit;
    EditText textoEdit;
    int idNota;
    NotasController notasController;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nota);

        idNota = getIntent().getExtras().getInt("id");

        tituloEdit = findViewById(R.id.tituloEdit);
        textoEdit = findViewById(R.id.textoEdit);

        notasController = new NotasController(getBaseContext());
        nota = notasController.getNotas().get(idNota);

        tituloEdit.setText(nota.getTitulo());
        textoEdit.setText(nota.getTexto());
    }

    public void salvarEdicao(View view) {
        String novoTitulo = tituloEdit.getText().toString();
        String novoTexto = textoEdit.getText().toString();

        nota.setTitulo(novoTitulo);
        nota.setTexto(novoTexto);

        notasController.salvarNota(nota);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
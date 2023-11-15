package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.controller.NotasController;
import com.example.mynotes.modelo.Nota;

public class AddNota extends AppCompatActivity {

    EditText texto;
    EditText titulo;

    private NotasController notasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nota);

        this.titulo = findViewById(R.id.titulo);
        this.texto = findViewById(R.id.texto);

        this.notasController = new NotasController(AddNota.this);
    }

    public void adicionaNota(View view) {
        if (!this.titulo.equals("") || !this.texto.equals("")) {
            // Passando 0 como id, pois será gerado automaticamente pelo banco de dados
            Nota nota = new Nota(0, this.titulo.getText().toString(), this.texto.getText().toString());
            this.notasController.salvarNota(nota);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Não é possível criar a nota!", Toast.LENGTH_SHORT).show();
        }
    }
}

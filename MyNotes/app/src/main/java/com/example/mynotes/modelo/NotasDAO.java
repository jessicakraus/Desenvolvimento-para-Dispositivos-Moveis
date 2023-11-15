package com.example.mynotes.modelo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mynotes.modelo.Nota;

import java.util.ArrayList;

public class NotasDAO {

    SQLiteDatabase bd;

    public NotasDAO(Context context) {
        bd = context.openOrCreateDatabase("meubd", Context.MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas(" +
                "id integer primary key autoincrement," +
                "titulo varchar not null," +
                "texto varchar);");
    }

    public ArrayList<Nota> getNotas() {
        ArrayList<Nota> arraylistadeNotas = new ArrayList<>();

        Cursor cursor = bd.rawQuery("SELECT * FROM notas", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String texto = cursor.getString(cursor.getColumnIndex("texto"));

            arraylistadeNotas.add(new Nota(id, titulo, texto));
        }

        cursor.close();

        return arraylistadeNotas;
    }


    public void inserirNota(Nota nota) {
        try {
            bd.execSQL("INSERT INTO notas (titulo, texto) VALUES (?, ?)",
                    new Object[]{nota.getTitulo(), nota.getTexto()});
        } catch (Exception e) {
            // Lide com exceções, se necessário
        }
    }

    public void excluirNota(int id) {
        try {
            bd.delete("notas", "id = ?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
        }
    }

    @SuppressLint("Range")
    public int getIdNotaPorTitulo(String titulo) {
        int idNota = -1; // Valor padrão se o título não for encontrado

        // Consulta ao banco de dados para obter o ID da nota com base no título
        Cursor cursor = bd.rawQuery("SELECT id FROM notas WHERE titulo = ?", new String[]{titulo});

        if (cursor.moveToFirst()) {
            idNota = cursor.getInt(cursor.getColumnIndex("id"));
        }

        cursor.close();
        return idNota;
    }
}
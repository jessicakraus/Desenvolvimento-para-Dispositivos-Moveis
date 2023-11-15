package com.example.mynotes.modelo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Nota {

    int id;
    String titulo;
    String texto;

    // Construtor com o novo parâmetro 'id'
    public Nota(int id, String titulo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
    }

    // Método para obter todas as notas do banco de dados
    public static ArrayList<Nota> getNotas(SQLiteDatabase bd) {
        ArrayList<Nota> arraylistadeNotas = new ArrayList<>();

        try (Cursor cursor = bd.rawQuery("SELECT * FROM notas", null)) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                @SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("texto"));

                arraylistadeNotas.add(new Nota(id, titulo, texto));
            }
        }

        return arraylistadeNotas;
    }

    public String getTitulo() {

        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTexto() {

        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
package com.example.mynotes.controller;

import android.content.Context;

import com.example.mynotes.modelo.Nota;
import com.example.mynotes.modelo.NotasDAO;

import java.util.ArrayList;

public class NotasController {

    Context mContext;
    NotasDAO notasdao;

    public NotasController(Context context) {
        this.mContext = context;
        this.notasdao = new NotasDAO(mContext);
    }

    public ArrayList<Nota> getNotas() {
        return this.notasdao.getNotas();
    }


    public ArrayList<String> getTitulosNotas() {

        ArrayList<String> arrayAdapterResultado = new ArrayList<String>();
        for (Nota n : this.notasdao.getNotas())
            arrayAdapterResultado.add(n.getTitulo());

        return arrayAdapterResultado;
    }

    public void salvarNota(Nota nota){
        this.notasdao.inserirNota(nota);
    }

    public void excluirNota(int id) {this.notasdao.excluirNota(id);}

    public int getIdNotaPorTitulo(String titulo) {
        return notasdao.getIdNotaPorTitulo(titulo);
    }

}
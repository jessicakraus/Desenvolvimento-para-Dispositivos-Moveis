package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//4) Construa uma aplicação que faça o cálculo do IMC, recebendo os dados informados em
//uma tela pelo usuário e calculando na segunda Activity.
public class MainActivity extends AppCompatActivity {

    EditText tvNome;
    EditText tvPeso;
    EditText tvAltura;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNome = findViewById(R.id.editTextNome);
        tvPeso = findViewById(R.id.editTextPeso);
        tvAltura = findViewById(R.id.editTextAltura);
    }

    public void calculoImc(View v) {
        String tvnome = tvNome.getText().toString();
        String tvpeso = tvPeso.getText().toString();
        String tvaltura = tvAltura.getText().toString();

        if (tvnome.equals("") || tvpeso.equals("") || tvaltura.equals("")) {
            Toast.makeText(getApplicationContext(), "Informe os dados corretamente", Toast.LENGTH_SHORT).show();
        } else  {
            float peso = Float.parseFloat(tvpeso);
            float altura = Float.parseFloat(tvaltura) / 100; // Convertendo altura para metros

            float calcImc = peso / (altura * altura);
            String imcCalc = String.format("%.1f", calcImc);

            // Determinar a categoria do IMC
            String categoria;
            if (calcImc < 18.5) {
                categoria = "Abaixo do peso";
            } else if (calcImc < 25) {
                categoria = "Peso ideal (parabéns)";
            } else if (calcImc < 30) {
                categoria = "Levemente acima do peso";
            } else if (calcImc < 35) {
                categoria = "Obesidade grau I";
            } else if (calcImc < 40) {
                categoria = "Obesidade grau II (severa)";
            } else {
                categoria = "Obesidade grau III (mórbida)";
            }

            // Enviar os dados para a segunda Activity
            Intent intent = new Intent(this, MainResultado.class);
            intent.putExtra("nome", tvnome);
            intent.putExtra("imc", imcCalc);
            intent.putExtra("categoria", categoria);
            startActivity(intent);
        }
    }
}
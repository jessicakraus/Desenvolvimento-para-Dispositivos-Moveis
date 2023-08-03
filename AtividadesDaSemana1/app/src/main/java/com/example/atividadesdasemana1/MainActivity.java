package com.example.atividadesdasemana1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Exercício 2
    Button buttonClick;
    int count = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Exercício 2 - Count Click
        buttonClick = findViewById(R.id.buttonClick);
        buttonClick.setOnClickListener(v -> count());

        // Exercício 3 - Sort
        editTextMin = findViewById(R.id.editTextMin);
        editTextMax = findViewById(R.id.editTextMax);
        textViewResult = findViewById(R.id.textViewResult);
    }

    @SuppressLint("SetTextI18n")
    public void count(){
        count++;
        buttonClick.setText(Integer.toString(count));
    }

    //Exercício 3
    private EditText editTextMin;
    private EditText editTextMax;
    private TextView textViewResult;

    public void drawRandomNumber(View view) {
        String minString = editTextMin.getText().toString();
        String maxString = editTextMax.getText().toString();

        if (!minString.isEmpty() && !maxString.isEmpty()) {
            int min = Integer.parseInt(minString);
            int max = Integer.parseInt(maxString);

            if (min <= max) {
                Random random = new Random();
                int randomNumber = random.nextInt(max - min + 1) + min;
                textViewResult.setText("Número sorteado: " + randomNumber);
            } else {
                textViewResult.setText("Intervalo inválido");
            }
        } else {
            textViewResult.setText("Preencha ambos os campos");
        }
    }

}
